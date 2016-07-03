<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.AchievementManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Achievement" %>
<html>

<head>
    <%
        QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
                QuizManagerDAO.ATTRIBUTE_NAME);
        AchievementManagerDAO achManager = (AchievementManagerDAO) session.getServletContext().getAttribute(
                AchievementManagerDAO.ATTRIBUTE_NAME);
    /*
        list of variables:
        details of the user (the one whose page we're visiting)
     */
        Account account = (Account) session.getAttribute("user_account");
        String A_FNAME = (String)account.getFirstName();
        String A_LNAME = (String)account.getLastName();
        String A_MAIL = (String)account.getEmail();
        String a_name = (String)account.getHashedPassword();
        Integer a_id = (Integer)account.getId();

        //how many quizzes and achievements are going to
        //appear on user's page
        int count = 3;

    /*
        list of quizzes the user has created, has taken and the
        achievements he has unlocked
     */
        List<Quiz> createdQuizzes = qManager.getCreatedQuizzes(account, count);
        List<Quiz> takenQuizzes = qManager.getTakenQuizzes(account, count);
        List<Achievement> achievements = achManager.getAchievements(account);
    %>

    <link rel="stylesheet" type="text/css" href="rules.css"/>
    <title> <%=A_FNAME%> <%=A_LNAME%> </title>
</head>

<body>
    <!--
        user's image and username appear here
    -->
    <div id="user-image">
        <img src="default.png" href="changeProfPic" style="width:180px;height:180px;position:relative; left:10px; top:10px;">
        </br>
        <div class="tn" style="text-align:center; position:relative; top:15px;"> <%=A_FNAME%> <%=A_LNAME%> </div>
    </div>

    <!--
        user's activity
        with link to complete activity
    -->
    <div id="activity">
        <div class="tb" style="text-align:center; position:relative; top:15px;"> Their Activity </div>
        <%
            for(int i=0; i<takenQuizzes.size(); i++){ %>
                <h1 class="bold-text"> Took Quiz: <%=takenQuizzes.get(i).getName()%></h1>
                <h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
            <%}
        %>
        <div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="activity.jsp">Show All </a></div>
    </div>

    <!--
        quizzes created by this user
    -->
    <div id="mq">
        <div class="tb" style="text-align:center; position:relative; top:15px;"> Their Quizes </div>
        <%
            for(int i=0; i<takenQuizzes.size(); i++){ %>
                <h1 class="bold-text"> Created Quiz: <%=createdQuizzes.get(i).getName()%></h1>
                <h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
            <%}
        %>
        <div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="userQuizes.jsp">Show All </a> </div>
    </div>

    <!--
        achievements unclocked by this user
    -->
    <div id="achievements">
        <div class="tb" style="text-align:center; position:relative; top:15px;"> Their Achievements </div>
        <%
            for(int i=0; i<takenQuizzes.size(); i++){ %>
                <h1 class="bold-text"> Achievements: <%=achievements.get(i).getAchievementType()%></h1>
                <h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
            <%}
        %>
        <div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="achievements.jsp">Show All </a></div>
    </div>

    <%--
        search bar
    --%>
    <form id="sbar" action="" method="post">
        <input type="text" name="searchData" placeholder="Search" id = "search"/>
        <button class="button srch" onclick="document.getElementById('sbar').submit();"> Search! </button>
    </form>

    <!--
        message, add friend and back buttons
        message promts user to write a message to the user whose account is being visited
        add friends sends him/her a friend request
        back button takes user to his own homepage
    -->
    <div id="messages" style="position:absolute; top:85px; left:18%;"> <%--popular and  buttons--%>
        <button class="button pr" onclick=""> Message </button>
        <button class="button pr" onclick="form.action='addFriend';"> Add Friend </button>
        <button class="button pr" onclick="location.href='homePage.jsp'"> Back </button>
    </div>

</body>

</html>