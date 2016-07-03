<%@ page import="java.util.Objects" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<link rel="stylesheet" type="text/css" href="rules.css"/>
		<title> registration </title>
	</head>

	<body>
		<!-- yes, "bob" is a legitimate id -->
		<form id="bob" action="/Registration" method="post">
			<h2 id="bold-text2"> Fill Out The Registration Form</h2>
			
			<div class="tb"> Username: </div> <input type="text" name="user" placeholder="Username" id = "un" oninput="checkName()"/>
			<div class ="tb" id="post-un"> </div> </br></br>

			<div class="tb"> First Name: </div> <input type="text" name="firstName" placeholder="First Name" id = "fn" oninput="checkName()"/>
			<div class ="tb" id="post-firstName"> </div> </br></br>

			<div class="tb"> Last Name: </div> <input type="text" name="lastName" placeholder="Last Name" id = "ln" oninput="checkName()"/>
			<div class ="tb" id="post-lastName"> </div> </br></br>
			
			<div class="tb"> Password: </div> <input type="password" name="password" placeholder="password" id = "pass" oninput="checkPass()"/> 
			<div class ="tb" id="post-pass"> </div> </br></br>
			
			<div class="tb"> Password confirmation: </div> <input type="password" name="password_confirm" placeholder="confirm password" id = "pConfirm" oninput="checkConfirm()"/>
			<div class="tb" id="post-confirm"> </div> </br></br>
			<div class="tb"> E-mail: </div> <input type="text" name="e_mail" placeholder="E-mail" id = "mail" oninput="checkMail()"/>
			<div class ="tb" id="post-mail"> </div> </br></br>

			<div class="tb"> Profile picture (optional): </div> <input type="file" name="e-mail"/> </br></br>
			<button class="button sub" onclick="document.getElementById('bob').submit();"> Submit </button>

			<%
				String n = null;
				String k = (String) session.getAttribute("created");
				if((!Objects.equals(k, n)) && k.equals("false")){%>
					<h3 style="position:absolute; top: 110px; left: 300px;"> Incorrect Username or Password</h3>
				<%}
				%>

		</form>	
	
		<button class="button return" onclick="location.href='index.jsp'"> return </button>
	
		<script type="text/javascript">
			/*
				checks if the username contains illegal characters
				or is empty
			*/
			function checkName(){
				var name = document.getElementById("un").value;
				var bob = /^[0-9a-zA-Z]+$/;
				if(!bob.test(name)) {
					document.getElementById("post-un").innerHTML = "Username can't be empty and can contain only letters, numbers and underscores!";
				} else {
					document.getElementById("post-un").innerHTML = "";
				}
			}

			/*
				checks whether or not entered password and
				"password confirmation" match
			*/
			function checkConfirm(){
				if(document.getElementById('pass').value != document.getElementById('pConfirm').value){
					document.getElementById("post-confirm").innerHTML = "Passwords don't match";
				} else {	
					document.getElementById("post-confirm").innerHTML = "";
				}
			}

			/*
				this function checks if the password enter
				 a) is long enough
				 b) calculates the strength of the password, according to
				 	whether or not uppercase characters, lowercase characters
				 	and numbers are used
			 */
			function checkPass(){
				var pass = document.getElementById('pass').value;
				var strength = 0;
				
				var nums = /[0-9]/;
				var low = /[a-z]/;
				var upper = /[A-Z]/;
				if(nums.test(pass))
					strength++;
				if(low.test(pass))	
					strength++;
				if(upper.test(pass))
					strength++;
				if(pass.length < 6)
					strength = 0;
				var target = document.getElementById("post-pass");
				switch(strength) {
					case 0:
						target.innerHTML = "Password is too short (must contain at least 6 elements)";
						break;
					case 1:
						target.innerHTML = "Password Strength: Weak";
						break;
					case 2:
						target.innerHTML = "Password Strength: Medium";
						break;
					case 3:
						target.innerHTML = "Password Strength: Strong";
						break;
				}
			}

			//checks if mail contains the symbol "@"
			//asks user to enter a valid mail if it doesn't
			function checkMail(){
				var name = document.getElementById("mail").value;
				var bob = /@/;
				if(!bob.test(name)) {
					document.getElementById("post-mail").innerHTML = "please enter a valid e-mail";
				} else {
					document.getElementById("post-mail").innerHTML = "";
				}
			}
		</script>
	
	</body>
	
</html>