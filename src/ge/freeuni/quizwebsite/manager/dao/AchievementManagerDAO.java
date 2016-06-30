package ge.freeuni.quizwebsite.manager.dao;

import ge.freeuni.quizwebsite.manager.AchievementManager;
import ge.freeuni.quizwebsite.manager.QuizManager;
import ge.freeuni.quizwebsite.manager.StatsManager;
import ge.freeuni.quizwebsite.manager.dao.db.DbContract;
import ge.freeuni.quizwebsite.model.Account;
import ge.freeuni.quizwebsite.model.Achievement;
import ge.freeuni.quizwebsite.model.AchievementType;
import ge.freeuni.quizwebsite.model.Quiz;
import ge.freeuni.quizwebsite.util.MapUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

/**
 * Created by Saba on 19-06-2016.
 */
public class AchievementManagerDAO extends AbstractManagerDAO implements AchievementManager {

    public static final String ATTRIBUTE_NAME = "achievement_manager";

    private QuizManager quizManager;
    private StatsManager statsManager;
    private Map<AchievementType, String> achievementTypes;

    public AchievementManagerDAO(DataSource dataSource, QuizManager quizManager, StatsManager statsManager) {
        super(dataSource);
        this.quizManager = quizManager;
        this.statsManager = statsManager;

        //
        achievementTypes = new HashMap<>();
        achievementTypes.put(AchievementType.AMATEUR_AUTHOR, "Amateur Author");
        achievementTypes.put(AchievementType.PROLIFIC_AUTHOR, "Prolific Author");
        achievementTypes.put(AchievementType.PRODIGIOUS_AUTHOR, "Prodigious Author");
        achievementTypes.put(AchievementType.QUIZ_MACHINE, "Quiz Machine");
        achievementTypes.put(AchievementType.I_AM_THE_GREATEST, "I am the Greatest");
    }

    @Override
    public void unlockAchievement(Account account, Achievement achievement) {
        Integer typeId = achievementTypeToId(achievement.getAchievementType());

        if (achievementUnlocked(account, typeId)) {
            return;
        }

        try (Connection con = dataSource.getConnection()) {

            String query = "INSERT INTO " + DbContract.Achievement.TABLE_NAME + " (" +
                    DbContract.Achievement.COLUMN_NAME_ACCOUNT_ID + ", " +
                    DbContract.Achievement.COLUMN_NAME_TYPE_ID + ", " +
                    DbContract.Achievement.COLUMN_NAME_UNLOCK_DATE + ") VALUES (?, ?, ?);";
            PreparedStatement statement = con.prepareStatement(query);

            statement.setInt(1, account.getId());
            statement.setInt(2, typeId);
            Timestamp dateUnlocked = achievement.getDateUnlocked();
            statement.setTimestamp(3, dateUnlocked != null ? dateUnlocked : getCurrentTimestamp());
            statement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void checkForAchievements(Account account) {
        checkForAchievements(account, null);
    }

    @Override
    public void checkForAchievements(Account account, Quiz quiz) {
        List<Quiz> quizzes = quizManager.getCreatedQuizzes(account, 10);
        Timestamp currentTime = getCurrentTimestamp();

        if (quizzes != null && quizzes.size() >= 10) {
            Achievement prodigiousAuthor = new Achievement(AchievementType.PRODIGIOUS_AUTHOR, currentTime);
            unlockAchievement(account, prodigiousAuthor);
        }

        if (quizzes != null && quizzes.size() >= 5) {
            Achievement prolificAuthor = new Achievement(AchievementType.PROLIFIC_AUTHOR, currentTime);
            unlockAchievement(account, prolificAuthor);
        }

        if (quizzes != null && quizzes.size() >= 1) {
            Achievement amateurAuthor = new Achievement(AchievementType.AMATEUR_AUTHOR, currentTime);
            unlockAchievement(account, amateurAuthor);
        }

        List<Quiz> takenQuizzes = quizManager.getTakenQuizzes(account, 10);

        if (takenQuizzes != null && takenQuizzes.size() >= 10) {
            Achievement quizMachine = new Achievement(AchievementType.QUIZ_MACHINE, currentTime);
            unlockAchievement(account, quizMachine);
        }

        if (quiz != null) {
            List<Account> highestPerformers = statsManager.getHighestPerformers(quiz, 1);
            if (highestPerformers == null || highestPerformers.size() == 0) {
                return;
            }

            Account highestPerformer = highestPerformers.get(0);

            if (highestPerformer.getUsername().equals(account.getUsername())) {
                Achievement greatest = new Achievement(AchievementType.I_AM_THE_GREATEST, currentTime);
                unlockAchievement(account, greatest);
            }
        }
    }

    @Override
    public List<Achievement> getAchievements(Account account) {
        List<Achievement> achievements = new ArrayList<>();
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT * FROM " + DbContract.Achievement.TABLE_NAME + " WHERE " +
                    DbContract.Achievement.COLUMN_NAME_ACCOUNT_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Integer typeId = rs.getInt(DbContract.Achievement.COLUMN_NAME_TYPE_ID);
                Timestamp unlockDate = rs.getTimestamp(DbContract.Achievement.COLUMN_NAME_UNLOCK_DATE);
                AchievementType type = achievementIdToType(typeId);
                Achievement achievement = new Achievement(type, unlockDate);
                achievements.add(achievement);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return achievements;
    }

    private Integer achievementTypeToId(AchievementType type) {
        Integer id = null;
        try {
            Connection con = dataSource.getConnection();
            String achievementTypeStr = achievementTypes.get(type);
            String query = "SELECT * FROM " + DbContract.AchievementType.TABLE_NAME + " WHERE "
                    + DbContract.AchievementType.COLUMN_NAME_ACHIEVEMENT + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, achievementTypeStr);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                id = rs.getInt(DbContract.AchievementType.COLUMN_NAME_TYPE_ID);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return id;
    }

    private AchievementType achievementIdToType(Integer typeId) {
        AchievementType type = null;
        try {
            Connection con = dataSource.getConnection();
            String query = "SELECT * FROM " + DbContract.AchievementType.TABLE_NAME + " WHERE "
                    + DbContract.AchievementType.COLUMN_NAME_TYPE_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, typeId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String achievementName = rs.getString(DbContract.AchievementType.COLUMN_NAME_ACHIEVEMENT);
                type = MapUtils.getKeyByValue(achievementTypes, achievementName);
            }
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return type;
    }

    private boolean achievementUnlocked(Account account, Integer typeId) {
        boolean unlocked = false;
        try (Connection con = dataSource.getConnection()) {
            String query = "SELECT * FROM " + DbContract.Achievement.TABLE_NAME + " WHERE " +
                    DbContract.Achievement.COLUMN_NAME_ACCOUNT_ID + " = ? AND " +
                    DbContract.Achievement.COLUMN_NAME_TYPE_ID + " = ?;";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1, account.getId());
            statement.setInt(2, typeId);

            ResultSet result = statement.executeQuery();
            unlocked = result.next();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return unlocked;
    }

}
