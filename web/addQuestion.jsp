<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 7/3/2016
  Time: 6:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Question</title>

        <script type="text/javascript">
            function a(i){
                if(i==1){
                    document.getElementById("newQuestion").innerHTML = document.getElementById("Question-Response").innerHTML;
                } else if(i==2){
                    document.getElementById("newQuestion").innerHTML = document.getElementById("Fill in the Blanks").innerHTML;
                } else if(i==3){
                    document.getElementById("newQuestion").innerHTML = document.getElementById("Multiple Choice").innerHTML;
                } else if(i==4){
                    document.getElementById("newQuestion").innerHTML = document.getElementById("Picture-Response").innerHTML;
                }
            }
        </script>
    </head>

    <body>

        <!-- buttons with question types-->
        <div id="q-types">
            <button class="button pr" onclick="a(1)"> Question-Response </button>
            <button class="button pr" onclick="a(2)"> Fill in the Blanks </button>
            <button class="button pr" onclick="a(3)"> Multiple Choice </button>
            <button class="button pr" onclick="a(4)"> Picture-Response </button>

            <button class="button sub" id="submit" onclick="" style="margin-left: 25px;"> Submit </button>
            <button class="button pr" onclick="location.href='homePage.jsp'"> Cancel Quiz Creation </button>
        </div>

        <div id="newQuestion" style="position: absolute; left: 65%; top: 20px;">
            <!-- this is where question forms are "inserted"-->
        </div>

        <form id="Question-Response" action="/QuizCreation" method="post" style="visibility: hidden">
            <h1> Question-Response </h1>
            <input type=hidden name="type" value="1"/>
            </br>
            <input type="text" name="question" id="q1" placeholder="Question"/>
            </br>
            seperate multiple correct answers with semicolons
            <input type="text" name="answer" id="a1" placeholder="answer(s)"/>
            </br>
            <button class="button sub" onclick="document.getElementById('Question-Response').submit();"> add </button>
            </br>

        </form>

        <form id="Fill in the Blanks" action="/QuizCreation" method="post" style="visibility: hidden">
            <h1> Fill in the Blanks </h1>
            </br>
            <input type=hidden name="type" value="2"/>
            <input type="text" name="question" id="q2" placeholder="Question"/>
            </br>
            <input type="text" name="answer" id="a2" placeholder="answer(s)"/>
            </br>
            <button class="button sub" onclick="document.getElementById('Fill in the Blanks').submit();"> add </button>
            </br>
        </form>

        <form id="Multiple Choice" action="/QuizCreation" method="post" style="visibility: hidden">
            <h1> Multiple Choice </h1>
            </br>
            <input type=hidden name="type" value="3"/>
            <input type="text" name="question" id="q3" placeholder="Question"/>
            </br>
            <input type="text" name="answer1" id="a3" placeholder="Correct Answer"/>
            </br>
            <input type="text" name="answer2" id="o31" placeholder="option"/>
            </br>
            <input type="text" name="answer3" id="o32" placeholder="option"/>
            </br>
            <input type="text" name="answer4" id="o33" placeholder="option"/>
            </br>
            <button class="button sub" onclick="document.getElementById('Multiple Choice').submit();"> add </button>
            </br>

        </form>

        <form id="Picture-Response" action="/QuizCreation" method="post" style="visibility: hidden">
            <h1> Picture-Response </h1>
            </br>
            <input type=hidden name="type" value="4"/>
            <input type="text" name="question" id="q4" placeholder="image URL"/>
            </br>
            <input type="text" name="answer" id="a4" placeholder="answer"/>
            </br>
            <button class="button sub" onclick="document.getElementById('Picture-Response').submit();"> add </button>
            </br>
        </form>
    </body>
</html>
