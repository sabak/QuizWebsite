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
                    var question = {
                        type:"1",
                        q:document.getElementById("q1"),
                        a:document.getElementById("a1")
                    }
                    questions.push(question);
                } else if(i==2){//fill in the blank
                    var question = {
                        type:"2",
                        q:document.getElementById("q2"),
                        a:document.getElementById("a2")
                    }
                    questions.push(question);
                } else if(i==3){//multiple choice
                    var question = {
                        type:"3",
                        q:document.getElementById("q3"),
                        a:document.getElementById("a3"),
                        o1:document.getElementById("o31"),//o31 stands for option 1
                        o2:document.getElementById("o32"),
                        o3:document.getElementById("o33")
                    }
                    questions.push(question);
                } else if(i==4){//image
                    var question = {
                        type:"4",
                        q:document.getElementById("q4"),
                        a:document.getElementById("a4"),
                        url:document.getElementById("url")
                    }
                    questions.push(question);
                }
            }


            function customSubmit(){
                document.getElementById("quiz-form").questions.value = questions;
                <%
                    session.setAttribute("questions", "'+ questions +'");
                %>;
                document.getElementById("quiz-form").submit();
            }
        </script>
    </head>

    <body>
        <form id="quiz-form" action="/QuizCreation" method="post">
            <!-- before submitting, the value of this input becomes the list of questions -->
            <input type=hidden name="questions" value="nuthing"/>
            <div id="q-form">
                <div class="tb" style="text-align:center; font-size:150%; position:absolute; top:20px; left:10%"> New Quiz </div>
                <div class="tb" style="text-align:center; position:absolute; top:30px; left:10%"> please fill this form last </div>
                <input type="text" name="quiz-name" placeholder="Enter Quiz Name" id = "qName" style="position:absolute; left:10%; top:70px;"/>

                <input type="text" name="quiz-description" placeholder="Enter Quiz Description" id = "qDesc" style="position:absolute; left:10%; top:115px;"/>

                <div style="position:absolute; left: 10%; top: 155px;">
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

        <div id="newQuestion" style="position: absolute; left: 65%; top: 20px;">
            <!-- this is where question forms are "inserted"-->
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
                <button class="button sub" onclick="addQuestion(2)"> add </button>
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
    </body>
</html>