<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page import="ge.freeuni.quizwebsite.manager.AccountManager" %>
<%@ page import="ge.freeuni.quizwebsite.manager.dao.AccountManagerDAO" %>
<html>
	<head>
		<link rel="stylesheet" type="text/css" href="rules.css"/>
		<title> home </title>
	</head>

	<body>

	<%
		Account account = (Account) session.getAttribute("account");
		String A_FNAME = (String)account.getFirstName();
		String A_LNAME = (String)account.getLastName();
		String A_MAIL = (String)account.getEmail();
		String a_name = (String)account.getHashedPassword();
		Integer a_id = (Integer) account.getId();
	%>

		<div id="user-image">
			<img src="default.png" href="changeProfPic" style="width:180px;height:180px;position:relative; left:10px; top:10px;">
			</br>
			<div class="tn" style="text-align:center; position:relative; top:15px;"> <%=A_FNAME%> <%=A_LNAME%> </div>
			<div class="tn" style="text-align:center; font-weight:200; position:relative; top:25px;"><a href="index.jsp"> Sign out </a></div>
		</div>
		<!--user's activity-->
		<div id="activity">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Activity </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="activity.jsp">Show All </a></div>
		</div>
		<!--user's quizes -->
		<div id="mq">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Quizes </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="userQuizes.jsp">Show All </a> </div>
		</div>
		<!--user's achievements-->
		<div id="achievements">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Achievements </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="achievements.jsp">Show All </a></div>
		</div>

		<form id="sbar" action="/searchInfo" method="post"><%--search bar--%>
			<input type="text" name="searchData" placeholder="Search" id = "search"/>
			<button class="button srch" onclick="document.getElementById('sbar').submit();"> Search! </button>
		</form>

		<div id="poprec"> <%--popular and  buttons--%>
			<button class="button pr" onclick="sortPopular()"> Popular </button>
			<button class="button pr" onclick="sortRecent()"> Recent </button>
			<button class="button pr" onclick="location.href='newQuiz.jsp'"> Create New </button>
		</div>

		<div id="qlist"><%--list of quizez--%>

		</div>

		<div id="Announcements">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Announcements </div>
		</div>

		<div id="mess"><%--messages and friend requests--%>
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Messages & Friend Requests </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"><a href="messages.jsp">Show All </a> </div>
		</div>

		<div id="fAct">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Friends' Activities </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"> <a href="fActivities.jsp">Show All </a> </div>
		</div>

		<div id="quiz-list" style="position:absolute; top:120px; left:20%;">
			<!--
				searched quizzes and users will appear here
				as well as 5 most popular or more recent quizzes
			-->
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
				<h1 class="bold-text"> Name: <%=session.getAttribute("gela")%></h1>
				<h1 class="bold-text"> Description: <%=session.getAttribute("gela")%></h1>
				<h1 class="bold-text" style="margin-bottom: 20px;"> <a href="index.jsp"> take quiz </a></h1>
			<%}%>
		</div>

		<script type="text/javascript">
			function sortPopular(){

			}

			function sortRecent(){

			}

			function something(){
				document.getElementById("quiz-list").innerHTML = document.getElementById("actual-quizzes").innerHTML;
			}

			function moreSomething(){
				document.getElementById("quiz-list").innerHTML = document.getElementById("actual-quizzes").innerHTML;
			}

		</script>
	</body>
	
</html>