<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Quiz Creator</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>
    </head>

    <body>
        <form id="quiz-form" action="/QuizCreation" method="post">
            <!-- before submitting, the value of this input becomes the list of questions -->
            <input type=hidden name="type" value="0"/>
            <div id="q-form">
                <div class="tb" style="text-align:center; font-size:150%; position:absolute; top:20px; left:10%"> New Quiz </div>
                <input type="text" name="quiz-name" placeholder="Enter Quiz Name" id = "qName" style="position:relative; left:10%; top:70px;"/>

                <input type="text" name="quiz-description" placeholder="Enter Quiz Description" id = "qDesc" style="position:relative; left:10%; top:115px;"/>

                <div style="position:relative; top: 155px; margin-top:20px; left:-10%">
                    <input type="checkbox" name="cBox" value="random"> Is randomised
                    <input type="checkbox" name="cBox" value="isImmediate"> Is immediately corrected
                    <input type="checkbox" name="cBox" value="sPage"> Is on single page
                </div>
            </div>

            <button class="button sub" style="position:relative; top:400px; left:10px;" onclick="document.getElementById('quiz-form').submit();"> Submit</button>
            <button class="button pr" style="position:relative; top:460px; left:-275px;" onclick="location.href='homePage.jsp'"> Cancel Quiz Creation </button>
        </form>
    </body>
</html>