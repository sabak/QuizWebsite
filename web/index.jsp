<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="rules.css"/>

    <title> Main Page </title>
</head>

<body>
<div id="inputs">
    <form id="inputs-form" action="" method="post">
        <div id="title">
            <h1>Login</h1>
        </div>
        <input type="text" name="user" placeholder="username"/>
        </br>
        <input type="password" name="password" placeholder="password"/>
        </br>
        <button class="button sub" onclick="document.getElementById('inputs-form').submit();"> Submit</button>
        </br>
    </form>

    <div id="bs">
        <button class="button pass" onclick="location.href='prec.html'">Password Recovery</button>
        <br/>
        <button class="button pass" onclick="location.href='registration.html'"> Registration</button>
    </div>
</div>
</body>
</html>
