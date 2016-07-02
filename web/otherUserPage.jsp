<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.QuizManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.AchievementManagerDAO" %>
<%@ page import="ge.freeuni.quizwebsite.model.Achievement" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="rules.css"/>
    <title> visi gverdicaa </title>
</head>

<body>

<%
    QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
            QuizManagerDAO.ATTRIBUTE_NAME);


    Account account = (Account) session.getAttribute("user_account");
    String A_FNAME = (String)account.getFirstName();
    String A_LNAME = (String)account.getLastName();
    String A_MAIL = (String)account.getEmail();
    String a_name = (String)account.getHashedPassword();
    Integer a_id = (Integer)account.getId();

    int count = 3;

    List<Quiz> createdQuizzes = qManager.getCreatedQuizzes(account, count);
    List<Quiz> takenQuizzes = qManager.getTakenQuizzes(account, count);

    AchievementManagerDAO achManager = (AchievementManagerDAO) session.getServletContext().getAttribute(
            AchievementManagerDAO.ATTRIBUTE_NAME);

    List<Achievement> achievements = achManager.getAchievements(account);
%>

    <div id="user-image">
        <img src="default.png" href="changeProfPic" style="width:180px;height:180px;position:relative; left:10px; top:10px;">
        </br>
        <div class="tn" style="text-align:center; position:relative; top:15px;"> <%=A_FNAME%> <%=A_LNAME%></div>
    </div>

    <!--user's activity-->
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

    <!--user's quizes -->
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
    <!--user's achievements-->
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

    <form id="sbar" action="" method="post"><%--search bar--%>
        <input type="text" name="searchData" placeholder="Search" id = "search"/>
        <button class="button srch" onclick="document.getElementById('sbar').submit();"> Search! </button>
    </form>

    <div id="messages" style="position:absolute; top:85px; left:18%;"> <%--popular and  buttons--%>
        <button class="button pr" onclick=""> Message </button>
        <button class="button pr" onclick=""> Add Friend </button>
        <button class="button pr" onclick="location.href='homePage.jsp'"> Back </button>
    </div>

</body>

</html>