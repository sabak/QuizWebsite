<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="rules.css"/>

    <title> Main Page </title>
</head>

<body>
<div id="inputs">
    <form id="inputs_form" action="/Login" method="post">
        <div id="title">
            <h1>Login</h1>
        </div>
        <input type="text" name="user" placeholder="username"/>
        </br>
        <input type="password" name="password" placeholder="password"/>
        </br>
        <button class="button sub" onclick="document.getElementById('inputs_form').submit();"> Submit</button>
        </br>
        <%
            String lUser =  (String)session.getAttribute("user");
            if(lUser == null){
                //do nothing
            } else if(lUser == "password"){ %>
        <h1 id="bold-text2">invalid password</h1>
        <%  } else if(lUser == "username"){ %>
        <h1 id="bold-text2">invalid username</h1>
        <%    }
        %>
    </form>

    <div id="bs">                                           <!--link to password recovery-->
        <button class="button pass" onclick="location.href='prec.html'">Password Recovery</button>
        <br/>
        <button class="button pass" onclick="location.href='registration.jsp'"> Registration</button>
    </div>
</div>
</body>
</html>