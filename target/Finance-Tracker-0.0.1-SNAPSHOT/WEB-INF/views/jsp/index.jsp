<!DOCTYPE html>
<html>
<head>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ page session="false" %>
    <meta charset="UTF-8">
    <title>Sign-Up/Login Form</title>
    <link href='https://fonts.googleapis.com/css?family=Titillium+Web:400,300,600' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/5.0.0/normalize.min.css">
    <link rel="stylesheet" href="css/style.css">

</head>

<body>
<div class="form">

    <ul class="tab-group">
        <li class="tab active"><a href="#signup">Sign Up</a></li>
        <li class="tab"><a href="#login">Log In</a></li>
    </ul>

    <div class="tab-content">
        <div id="signup">
            <h1>Sign Up for Free</h1>
            <%--modelAttribute="signup"--%>
            <form:form commandName="user" action="signup" method="post">

                <div class="top-row">
                    <div class="field-wrap">
                        <label>
                            First Name<span class="req">*</span>
                        </label>
                        <form:input path="firstName" />
                    </div>

                    <div class="field-wrap">
                        <label>
                            Last Name<span class="req">*</span>
                        </label>
                        <form:input  path="lastName" />
                    </div>

                </div>
                <div class="field-wrap">
                    <label>
                        User Name<span class="req">*</span>
                    </label>
                    <form:input  path="username"/>
                </div>
                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <form:input type="email" path="email" />
                </div>

                <div class="field-wrap">
                    <label>
                        Set A Password<span class="req">*</span>
                    </label>
                    <form:input type="password" path="password" />
                </div>
                <!-- <div class="field-wrap"> -->
                <!-- <input type="file" name = "profilpic" /> -->
                <!-- </div> -->
                <button type="submit" class="button button-block"/>
                Get Started</button>

            </form:form>

        </div>

        <div id="login">
            <h1>Welcome Back!</h1>

            <form:form commandName="user" method="post" action="login">

                <div class="field-wrap">
                    <label>
                        Email Address<span class="req">*</span>
                    </label>
                    <form:input type="email" path="email" autocomplete="on"/>
                </div>
                <div class="field-wrap">
                    <label>
                        User Name<span class="req">*</span>
                    </label>
                    <form:input type="text" path="username" autocomplete="on"/>
                </div>
                <div class="field-wrap">
                    <label>
                        Password<span class="req">*</span>
                    </label>
                    <form:input type="password" path="password"/>
                </div>

                <p class="forgot"><a href="#">Forgot Password?</a></p>

                <button class="button button-block"/>
                Log In</button>

            </form:form>

        </div>

    </div><!-- tab-content -->

</div> <!-- /form -->
<script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

<script src="js/index.js"></script>

</body>
</html>
