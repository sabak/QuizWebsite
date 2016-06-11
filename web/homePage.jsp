<html>
	<head>
		<link rel="stylesheet" type="text/css" href="rules.css"/>
		<title> home </title>
	</head>

	<body>
		<div id="user-image">
			<img src="default.png"  style="width:180px;height:200px;position:relative; left:10px; top:10px;">
			</br>
			<div class="tn" style="text-align:center; position:relative; top:15px;"> Username </div>
		</div>

		<div id="activity">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Activity </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"> Show All </div>
		</div>

		<div id="mq">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Quizes </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"> Show All </div>
		</div>

		<div id="achievements">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Your Achievements </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"> Show All </div>
		</div>

		<form id="sbar" action="" method="post"><%--search bar--%>
			<input type="text" name="password" placeholder="Search" id = "search"/>
			<button class="button srch" onclick="document.getElementById('sbar').submit();"> Search! </button>
		</form>

		<div id="pr"> <%--popular and recent--%>

		</div>

		<div id="qlist"><%--list of quizez--%>

		</div>

		<div id="Announcements">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Announcements </div>
		</div>

		<div id="mess"><%--messages and friend requests--%>
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Messages & Friend Requests </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"> Show All </div>
		</div>

		<div id="fAct">
			<div class="tb" style="text-align:center; position:relative; top:15px;"> Friends' Activities </div>
			<div class="tb" style="text-align:center; position:absolute; left:34%; bottom:5px;"> Show All </div>
		</div>
	</body>\
	
</html>