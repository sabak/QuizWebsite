<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz Creator</title>
    <link rel="stylesheet" type="text/css" href="rules.css"/>
</head>

<body>

    <form id="quiz-form" action="" method="post">
        <div id="q-form">
            <div class="tb" style="text-align:center; font-size:150%; position:absolute; top:20px; left:10%"> New Quiz </div>
            <input type="text" name="quiz-name" placeholder="Enter Quiz Name" id = "qName" style="position:absolute; left:10%; top:50px;"/>
        </div>
    </form>

    <div id="q-types">
        <button class="button pr" onclick="alert('c')"> Question-Response </button>
        <button class="button pr" onclick="alert('c')"> Fill in the Blanks </button>
        <button class="button pr" onclick="alert('c')"> Multiple Choice </button>
        <button class="button pr" onclick="alert('c')"> Picture-Response </button>

        <button class="button pr" id="registration_submit_button" onclick="alert('c')"> Submit </button>
        <button class="button pr" onclick="location.href='homePage.jsp'"> Cancel </button>
    </div>

</body>
</html>
