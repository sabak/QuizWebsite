package ge.freeuni.quizwebsite.model;

import java.sql.Timestamp;

/**
 * Created by Saba on 6/14/2016.
 */
public class Achievement {

    private AchievementType achievementType;
    private Timestamp dateUnlocked;

    public Achievement(AchievementType achievementType, Timestamp unlockDate) {
        this.achievementType = achievementType;
        this.dateUnlocked = unlockDate;
    }

    public AchievementType getAchievementType() {
        return achievementType;
    }

    public Timestamp getDateUnlocked() {
        return dateUnlocked;
    }

    @Override
    public String toString() {
        return "{ Achievement: " + achievementType
                + ", Achievement Unlocked At: " + dateUnlocked + " }";
    }

}
