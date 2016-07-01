<%@ page import="ge.freeuni.quizwebsite.model.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Quiz Creator</title>
        <link rel="stylesheet" type="text/css" href="rules.css"/>

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

            var questions = [];

            function addQuestion(i){
                if(i==1){//question-response
                    var question;
                    question.setAttribute("type", i);
                    question.setAttribute("q", document.getElementById("q1"));
                    question.setAttribute("a", document.getElementById("a1"));
                    questions.add(question);
                } else if(i==2){//fill in the blank
                    var question;
                    question.setAttribute("type", i);
                    question.setAttribute("q", document.getElementById("q2"));
                    question.setAttribute("a", document.getElementById("a2"));
                    questions.add(question);
                } else if(i==3){//multiple choice
                    var question;
                    question.setAttribute("type", i);
                    question.setAttribute("q", document.getElementById("q3"));
                    question.setAttribute("a", document.getElementById("a3"));
                    question.setAttribute("o1", document.getElementById("o31"));//o1 stands for option 1
                    question.setAttribute("o2", document.getElementById("o32"));
                    question.setAttribute("o3", document.getElementById("o33"));
                    questions.add(question);
                } else if(i==4){//image
                    var question;
                    question.setAttribute("type", i);
                    question.setAttribute("q", document.getElementById("q4"));
                    question.setAttribute("url", document.getElementById("url"));
                    question.setAttribute("a", document.getElementById("a4"));
                    questions.add(question);
                }
            }


            function customSubmit(){
                document.getElementById("quiz-form").hiddenValue.value = questions;
                document.getElementById("quiz-form").submit();
            }
        </script>
    </head>

    <body>
        <form id="quiz-form" action="/QuizCreation" method="post">
            <input type=hidden name="questions"/> <!-- before submitting, the value of this input becomes the list of questions -->
            <div id="q-form">
                <div class="tb" style="text-align:center; font-size:150%; position:absolute; top:20px; left:10%"> New Quiz </div>
                <input type="text" name="quiz-name" placeholder="Enter Quiz Name" id = "qName" style="position:absolute; left:10%; top:50px;"/>

                <div style="position:absolute; left: 10%; top: 95px;">
                    Is randomised</br>
                    <input type="radio" name="rand" value="true"> yes </br>
                    <input type="radio" name="rand" value="false"> no </br>
                    Is immediately corrected</br>
                    <input type="radio" name="immCorrected" value="true"> yes </br>
                    <input type="radio" name="immCorrected" value="false"> no </br>
                    Is on single page</br>
                    <input type="radio" name="sPage" value="true"> yes </br>
                    <input type="radio" name="sPage" value="false"> no </br>
                </div>
            </div>
        </form>
        
        <!-- buttons with question types-->
        <div id="q-types">
            <button class="button pr" onclick="a(1)"> Question-Response </button>
            <button class="button pr" onclick="a(2)"> Fill in the Blanks </button>
            <button class="button pr" onclick="a(3)"> Multiple Choice </button>
            <button class="button pr" onclick="a(4)"> Picture-Response </button>

            <button class="button sub" id="submit" onclick="customSubmit()" style="margin-left: 25px;"> Submit </button>
            <button class="button pr" onclick="location.href='homePage.jsp'"> Cancel </button>
        </div>
        <!-- this is where question forms are "inserted"-->
        <div id="newQuestion" style="position: absolute; left: 65%; top: 20px;">
            
        </div>

        <div id="Question-Response" style="visibility: hidden">
            <h1> Question-Response </h1>
            </br>
            <form>
                <input type="text" name="question" id="q1" placeholder="Question"/>
                </br>
                seperate multiple correct answers with semicolons
                <input type="text" name="answer" id="a1" placeholder="answer(s)"/>
                </br>
                <button class="button sub" onclick="addQuestion(1)"> add </button>
                </br>
            </form>

        </div>

        <div id="Fill in the Blanks" style="visibility: hidden">
            <h1> Fill in the Blanks </h1>
            <form>
                <input type="text" name="question" id="q2" placeholder="Question"/>
                </br>
                <input type="text" name="answer" id="a2" placeholder="answer(s)"/>
                </br>
                <button class="button sub" onclick="addQuestion(3)"> add </button>
                </br>
            </form>
        </div>

        <div id="Multiple Choice" style="visibility: hidden">
            <h1> Multiple Choice </h1>
            <form>
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
                <button class="button sub" onclick="addQuestion(3)"> add </button>
                </br>
            </form>
        </div>
        <div id="Picture-Response" style="visibility: hidden">
            <h1> Picture-Response </h1>
            <form>
                <input type="text" name="question" id="q4" placeholder="Question"/>
                </br>
                <input type="text" name="url" id="url" placeholder="image url"/>
                </br>
                <input type="text" name="answer" id="a4" placeholder="answer"/>
                </br>
                <button class="button sub" onclick="addQuestion(4)"> add </button>
                </br>
            </form>
        </div>

        <form name="myForm">
            <input type=hidden name="hiddenValue"/>
            <input type="submit" value="Submit" name="buttonSubmit" onclick="customSubmit(10)"/>
        </form>

    </body>
</html>