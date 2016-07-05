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

			//how many quizzes and achievements are going to
			//appear on user's homepage
			int count = 4;

			/*
				list of quizzes the user has created, has taken and the
				achievements he has unlocked
			 */
			List<Quiz> createdQuizzes = qManager.getCreatedQuizzes(account, count);
			List<Quiz> takenQuizzes = qManager.getTakenQuizzes(account, count);
			List<Achievement> achievements = achManager.getAchievements(account);

			StatsManagerDAO statsManager = (StatsManagerDAO) session.getServletContext().getAttribute(
					StatsManagerDAO.ATTRIBUTE_NAME);

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
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="activity.jsp?account=<%=account.getUsername()%>">Show All </a></div>
		</div>

		<!--
			quizzes created by the user
		-->
		<div id="mq">
			<div class="tb" style="text-align:center; position:relative; top:15px; margin-bottom: 30px;"> Your Quizes </div>
			<%
				for(int i=0; i<createdQuizzes.size(); i++){ %>
					<a href="/TakeQuiz?quiz=<%createdQuizzes.get(i);%>" class="bold-text"> Created Quiz: <%=createdQuizzes.get(i).getName()%></a> </br>
			<%	}
			%>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="userQuizes.jsp?account=<%=account.getUsername()%>">Show All </a> </div>
		</div>

		<!--
			user's achievements
		-->
		<div id="achievements">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Achievements </div>
			<%
				for(int i=0; i<achievements.size(); i++){ %>
					<h1 class="bold-text"> Achievements: <%=achievements.get(i).getAchievementType()%></h1>
					<h1 class="bold-text" style="margin-bottom: 10px"> link </h1>
			<%	}
			%>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="achievements.jsp?account=<%=account.getUsername()%>">Show All </a></div>
		</div>

		<!--
			search bar
		-->
		<form id="sbar" action="/searchInfo" method="post">
			<input type="text" name="searchData" placeholder="Search" id = "search"/>

			<button class="button srch" onclick="document.getElementById('sbar').submit();"> Search! </button>
		</form>


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
						<a onclick="<%session.setAttribute("user_account", sender);%>" href="otherUserPage.jsp" ><%=senderFirstName%> <%=senderLastName%></a> sent you a message: </br>
						<%=mText%>
						at <%=sqlTime%>
				</p>
				<%}
			%>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="messages.jsp?account=<%=account.getUsername()%>">Show All </a> </div>
		</div>

		<%--
			Popular and Recent buttons
			Popular displays a certain number of most popular quizzes
			Recent displays a certain number of most recent quizzes
		--%>
		<div id="poprec">
			<button class="button pr" onclick="sortPopular()"> Popular </button>
			<button class="button pr" onclick="sortRecent()"> Recent </button>
			<button class="button pr" onclick="location.href='newQuiz.jsp'"> Create New </button>
		</div>

		<!--popular or recent quizzes are displayed here-->
		<div id="quiz-list" style="position:absolute; top:200px; left:40%;">

		</div>

		<div id="pop"  style="visibility: hidden">
			<%
				List<Quiz> popularQuizzes = statsManager.getPopularQuizzes(count);
				System.out.println("pop: " + popularQuizzes);
				if(popularQuizzes != null){
					for(int i=0; i<popularQuizzes.size(); i++){ %>
						Quiz Name: <%=popularQuizzes.get(i).getName()%> </br>
						Quiz Description: <%=popularQuizzes.get(i).getDescription()%> </br>
						<button class="button sub" style="margin-bottom: 33px;" onclick="location.href='/TakeQuiz?quiz=<%=popularQuizzes.get(i)%>'"> take quiz </button></br>
			<%		}
			%>	<button class="button sub" > show all (popular) </button><%
				}
		%>
		</div>

		<div id="rec"  style="visibility: hidden">
			<%
				List<Quiz> recentQuizzes = qManager.getRecentlyCreatedQuizzes(count*2);
				System.out.println(recentQuizzes);
				if(recentQuizzes != null){
					for(int i=0; i<recentQuizzes.size(); i=i+2){ %>
						Quiz Name: <%=recentQuizzes.get(i).getName()%> </br>
						Quiz Description: <%=recentQuizzes.get(i).getDescription()%> </br>
						<button class="button sub" style="margin-bottom: 33px;" onclick="location.href='/TakeQuiz?quiz=<%=recentQuizzes.get(i).getName()%>'"> take quiz </button></br>
				<%	}
				%><button class="button sub" > show all (recent) </button><%
				}
			%>
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
			<form action = "/makeAnnouncement" method = "Post">

				<input type="text" name="announcementText" placeholder="announcement"/> </br>
				<button class="button pr"> Make Announcement </button>
			</form>
			<%} else{
				List<Announcement> announcements = announcementManager.getAnnouncements(10);
				for(int i=0; i<announcements.size(); i++){
			%>
				<p class="tn">
					<%=announcements.get(i).getText()%> </br>
					<%=announcements.get(i).getAuthor().getUsername()%> </br>
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
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"> <a href="fActivities.jsp?account=<%=account.getUsername()%>">Show All </a> </div>
		</div>

		<script type="text/javascript">
			function sortPopular(){
				document.getElementById("quiz-list").innerHTML = document.getElementById("pop").innerHTML;
			}

			function sortRecent(){
				document.getElementById("quiz-list").innerHTML = document.getElementById("rec").innerHTML;
			}

		</script>
	</body>
	
</html>
