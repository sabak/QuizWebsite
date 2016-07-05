<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.model.Achievement" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="ge.freeuni.quizwebsite.model.message.FriendRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.*" %>
<%@ page import="ge.freeuni.quizwebsite.model.message.TextMessage" %>
<%@ page import="ge.freeuni.quizwebsite.manager.AdminManager" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.db.DbContract" %>
<%@ page import="ge.freeuni.quizwebsite.model.Announcement" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 6/27/2016
  Time: 5:50 PM

   messages between this user and all the other ones appear here
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>

        <%
            AccountManagerDAO accManager = (AccountManagerDAO) session.getServletContext().getAttribute(
                    AccountManagerDAO.ATTRIBUTE_NAME);
			/*
        		list of variables:
        		details of the user (the one who's logged in)
    		 */
            Account account = (Account) accManager.getAccount((String) session.getAttribute("account_un"));
            FriendManagerDAO friendManager = (FriendManagerDAO) session.getServletContext().getAttribute(FriendManagerDAO.ATTRIBUTE_NAME);
            TextMessageManagerDAO messageManager = (TextMessageManagerDAO) session.getServletContext().getAttribute(
                    TextMessageManagerDAO.ATTRIBUTE_NAME);
        %>


        <title>Your Messages</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
    </head>

    <body>
        <div id="title">
            <h1>Your Messages</h1>
            <%
                if (friendManager.getFriendRequests(account) != null){
                    List<FriendRequest> friendRequests = friendManager.getFriendRequests(account);
                    for (int i = 0; i < friendRequests.size(); i++) {
                        FriendRequest req = friendRequests.get(i);
                        Account sender = req.getSender();
            %>
            <%=sender.getUsername()%> ( <%=sender.getFirstName()%> <%=sender.getLastName()%> ) </br>
            wants to be your friend!
            <form action="/FriendResponse" method = "Post">
                <button button class="button pr" style="width:50px" onclick="<% session.setAttribute("request",req);%>;"> accept </button> </br>
            </form>
            <form action="/DeclineResponse" method = "Post">
                <button button class="button pr" style="width:50px" onclick="<% session.setAttribute("request",req);%>;"> decline </button> </br>
            </form>
            <%
                    }
                }
            %>

            <%
                List msgs = messageManager.getReceivedMessages(account);
                for( int i=0; i < msgs.size(); i++){
                    TextMessage msg = (TextMessage) msgs.get(i);

                    String mText = msg.getText();
                    java.sql.Timestamp sqlTime = msg.getDateSent();
                    Account sender = msg.getSender();
                    String senderLastName = sender.getLastName();
                    String senderFirstName = sender.getFirstName();
            %>
            <p class="tn">
                <%=senderFirstName%> <%=senderLastName%> sent you a message: </br>
                <%=mText%>
                at <%=sqlTime%>
            </p>
            <%}
            %>

        </div>

        <button class="button pr" onclick="location.href='homePage.jsp'" style="position:absolute; bottom: +40px;"> Return </button>
    </body>
</html>
