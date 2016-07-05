<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.model.Achievement" %>
<%@ page import="ge.freeuni.quizwebsite.model.Quiz" %>
<%@ page import="ge.freeuni.quizwebsite.model.message.FriendRequest" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.*" %>
<%@ page import="ge.freeuni.quizwebsite.model.message.TextMessage" %>
<%@ page import="ge.freeuni.quizwebsite.manager.AdminManager" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.db.DbContract" %>
<%@ page import="ge.freeuni.quizwebsite.model.Announcement" %>
<html>
	<head>
		<%
			AccountManagerDAO accManager = (AccountManagerDAO) session.getServletContext().getAttribute(
					AccountManagerDAO.ATTRIBUTE_NAME);
			QuizManagerDAO qManager = (QuizManagerDAO) session.getServletContext().getAttribute(
					QuizManagerDAO.ATTRIBUTE_NAME);
			AchievementManagerDAO achManager = (AchievementManagerDAO) session.getServletContext().getAttribute(
					AchievementManagerDAO.ATTRIBUTE_NAME);
			FriendManagerDAO friendManager = (FriendManagerDAO) session.getServletContext().getAttribute(FriendManagerDAO.ATTRIBUTE_NAME);
			TextMessageManagerDAO messageManager = (TextMessageManagerDAO) session.getServletContext().getAttribute(
					TextMessageManagerDAO.ATTRIBUTE_NAME);
			AdminManagerDAO adminManager = (AdminManagerDAO) session.getServletContext().getAttribute(AdminManagerDAO.ATTRIBUTE_NAME);
			AnnouncementManagerDAO announcementManager = (AnnouncementManagerDAO) session.getServletContext().getAttribute(AnnouncementManagerDAO.ATTRIBUTE_NAME);
			/*
        		list of variables:
        		details of the user (the one who's logged in)
    		 */
			Account account = (Account) accManager.getAccount((String) session.getAttribute("account_un"));
			String A_FNAME = (String)account.getFirstName();
			String A_LNAME = (String)account.getLastName();
			String A_MAIL = (String)account.getEmail();
			String a_name = (String)account.getHashedPassword();
			Integer a_id = (Integer) account.getId();

			//how many quizzes and achievements are going to
			//appear on user's homepage
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
		<title> home </title>
	</head>

	<body>
		<!--
			user's image and username appear here
			as well as the sign out link
		-->
		<div id="user-image">
			<img src="default.png" href="changeProfPic" style="width:180px;height:180px;position:relative; left:10px; top:10px;">
			</br>
			<div class="tn" style="text-align:center; position:relative; top:15px;"> <%=A_FNAME%> <%=A_LNAME%> </div>
			<div class="tn" style="text-align:center; font-weight:200; position:relative; top:25px;"><a href="index.jsp"> Sign out </a></div>
		</div>

		<!--
			user's activity
		-->
		<div id="activity">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Activity </div>
			<%
				for(int i=0; i<takenQuizzes.size(); i++){ %>
					<h1 class="bold-text"> Took Quiz: <%=takenQuizzes.get(i).getName()%></h1>
					<h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
			<%	}
			%>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="activity.jsp">Show All </a></div>
		</div>

		<!--
			quizzes created by the user
		-->
		<div id="mq">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Quizes </div>
			<%
				for(int i=0; i<takenQuizzes.size(); i++){ %>
					<h1 class="bold-text"> Created Quiz: <%=createdQuizzes.get(i).getName()%></h1>
					<h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
			<%	}
			%>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="userQuizes.jsp">Show All </a> </div>
		</div>

		<!--
			user's achievements
		-->
		<div id="achievements">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Achievements </div>
			<%
				for(int i=0; i<takenQuizzes.size(); i++){ %>
					<h1 class="bold-text"> Achievements: <%=achievements.get(i).getAchievementType()%></h1>
					<h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
			<%	}
			%>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="achievements.jsp">Show All </a></div>
		</div>

		<!--
			search bar
		-->
		<form id="sbar" action="/searchInfo" method="post">
			<input type="text" name="searchData" placeholder="Search" id = "search"/>

			<button class="button srch" onclick="document.getElementById('sbar').submit();"> Search! </button>
		</form>

		<%--
			Popular and Recent buttons
			Popular displays a certain number of most popular quizzes
			Recent displays a certain number of most recent quizzes
		--%>
		<div id="mess">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Messages & Friend Requests </div>
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
				<button button class="button pr" onclick="<% session.setAttribute("request",req);%>;"> accept </button>
			</form>
			<form action="/DeclineResponse" method = "Post">
				<button button class="button pr" onclick="<% session.setAttribute("request",req);%>;"> decline </button>
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
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="messages.jsp">Show All </a> </div>
		</div>

		<%--
			list of quizez
			this is where the list of quizzes are displayed
			after the user clicks on either "Popular" or "Recent" button
		--%>
		<div id="poprec">
			<button class="button pr" onclick="sortPopular()"> Popular </button>
			<button class="button pr" onclick="sortRecent()"> Recent </button>
			<button class="button pr" onclick="location.href='newQuiz.jsp'"> Create New </button>
		</div>

		<div id="quiz-list" style="position:absolute; top:120px; left:20%;">

		</div>

		<!--
			recent messages from friends,
			quizz challenges
			and friend requests are going to appear here
			full list of those is accessed via the show all link
		-->
		<div id="Announcements">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Announcements </div>
			<% if(adminManager.isAdmin(account)){%>
				<input type="text" name="announcementText" placeholder="announcement" id = "search"/> </br>
				<button class="button pr" onclick="location.href='makeAnnouncement.jsp'"> Make Announcement </button>
			<%} else{
				List<Announcement> announcements = announcementManager.getAnnouncements(2);
				for(int i=0; i<announcements.size(); i++){
			%>
				<p class="tn">
					<%=announcements.get(i)%> </br>
				</p>
			<%
					}
				}
			%>
		</div>

		<!--
			friends' quiz taking activities are displayed here
		-->
		<div id="fAct">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Friends' Activities </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"> <a href="fActivities.jsp">Show All </a> </div>
		</div>

		<div id="searched">
			<%=session.getAttribute("racxa")%>
			<h1 class="bold-text" style="margin-bottom: 20px;"> <a href="index.jsp"> take quiz </a></h1>
		</div>

		<div id="actual-quizzes" style="visibility: hidden">
			<%
				int N_QUIZZES = 5; //number of quizzes to display
				for(int i=0; i<N_QUIZZES; i++){
			%>
				<h1 class="bold-text"> Name: <%=session.getAttribute("qName")%></h1>
				<h1 class="bold-text"> Description: <%=session.getAttribute("qDesc")%></h1>
				<h1 class="bold-text" style="margin-bottom: 20px;"> <a href="index.jsp"> take quiz </a></h1>
			<%}%>
		</div>

		<script type="text/javascript">
			function sortPopular(){

			}

			function sortRecent(){

			}

		</script>
	</body>
	
</html>