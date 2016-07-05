<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.freeuni.quizwebsite.model.Achievement" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.*" %>
<html>

<head>
    <%
        AccountManagerDAO accManager = (AccountManagerDAO) session.getServletContext().getAttribute(
                AccountManagerDAO.ATTRIBUTE_NAME);
        FriendManagerDAO friendManager = (FriendManagerDAO) session.getServletContext().getAttribute(FriendManagerDAO.ATTRIBUTE_NAME);

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
        <div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="activity.jsp?account=<%=account.getUsername()%>">Show All </a></div>
    </div>
    <!--
        quizzes created by this user
    -->
    <div id="mq">
        <div class="tb" style="text-align:center; position:relative; top:15px;"> Their Quizes </div>
        <%
            for(int i=0; i<createdQuizzes.size(); i++){ %>
                <h1 class="bold-text"> Created Quiz: <%=createdQuizzes.get(i).getName()%></h1>
                <h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
            <%}
        %>
        <div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="userQuizes.jsp?account=<%=account.getUsername()%>">Show All </a> </div>
    </div>
    <!--
        achievements unclocked by this user
    -->
    <div id="achievements">
        <div class="tb" style="text-align:center; position:relative; top:15px;"> Their Achievements </div>
        <%
            for(int i=0; i<achievements.size(); i++){ %>
                <h1 class="bold-text"> Achievements: <%=achievements.get(i).getAchievementType()%></h1>
                <h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
            <%}
        %>
        <div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="achievements.jsp?account=<%=account.getUsername()%>">Show All </a></div>
    </div>
    <%--
        search bar
    --%>
    <form id="sbar" action="/searchInfo" method="post">
        <input type="text" name="searchData" placeholder="Search" id = "search"/>

        <button class="button srch" onclick="document.getElementById('sbar').submit();"> Search! </button>
    </form>
    <!--
        message, add friend and back buttons
        message promts user to write a message to the user whose account is being visited
        add friends sends him/her a friend request
        back button takes user to his own homepage
    -->
    <div id="messages" style="position:absolute; top:85px; left:16%; min-width: 5000px;"> <%--popular and  buttons--%>
        <%  boolean friend = false;
            List friends = friendManager.getFriends(account);
            Account acc = (Account) session.getAttribute("account");
            for (int i=0; i < friends.size(); i ++){
                if (friends.get(i).toString().equals(acc.toString())){
                    friend = true;
                }
            }
            if (!friend){%>
        <button class="button pr" onclick="location.href='/addFriend'"> Add Friend </button></br>
        <% } else {%>
        <form id="message_form" action="sendMessage" method="post">
            <input type="text" name="messageText" placeholder="message" style="position: relative; left: 27px;"/> </br>
            <button class="button pr"> Message </button>
            </br>
        </form>
        <% }%>
        <button class="button pr" onclick="location.href='homePage.jsp'"> Back </button></br>
    </div>

</body>

</html>