<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="rules.css"/>

    <title> Main Page </title>
</head>

<body>
    <div id="inputs">
        <!--
            user fills out this form if he wants to log in
        -->
        <form id="inputs_form" action="/Login" method="post">

            <h1 id="title">Login</h1>

            <input type="text" name="user" placeholder="username"/>
            </br>
            <input type="password" name="password" placeholder="password"/>
            </br>
            <button class="button sub" onclick="document.getElementById('inputs_form').submit();"> Submit </button>
            </br>


            <%
                //check if an account with such a username exists
                //and if it does, check if the passwords match
                String lUser =  (String)session.getAttribute("user");
                if(lUser == null){
                    //do nothing
                } else if(lUser == "password"){ %>
                    <h1 id="bold-text2">invalid password</h1>
            <%  } else if(lUser == "username"){ %>
                    <h1 id="bold-text2">invalid username</h1>
            <%   }
            %>
        </form>

        <div id="bs"><!-- bs stands for buttons -->
            <!--link to password recovery page-->
            <%--<button class="button pass" onclick="location.href='prec.html'">Password Recovery</button>--%>
            <br/>
            <!--link to registration page-->
            <button class="button pass" onclick="location.href='registration.jsp'"> Registration</button>
        </div>
    </div>
</body>
</html>