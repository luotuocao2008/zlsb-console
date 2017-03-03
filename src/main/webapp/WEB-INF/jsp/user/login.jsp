<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="zh">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin Consul </title>

    <!-- Bootstrap core CSS -->

    <link href="resource/css/bootstrap.min.css" rel="stylesheet">

    <link href="resource/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="resource/css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="resource/css/custom.css" rel="stylesheet">
    <link href="resource/css/icheck/flat/green.css" rel="stylesheet">


    <script src="<c:url value="resource/js/jquery.min.js"/>"></script>
    <script src="<c:url value="resource/module/bootstrap/js/bootstrap.min.js"/>"></script>
    <script src="<c:url value="resource/module/jquery-cookie/jquery.cookie.js"/>"></script>

    <%--<!--[if lt IE 9]>--%>
        <%--<script src="../assets/js/ie8-responsive-file-warning.js"></script>--%>
        <%--<![endif]-->--%>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

</head>

<body style="background:#F7F7F7;">
    
    <div class="">
        <a class="hiddenanchor" id="toregister"></a>
        <a class="hiddenanchor" id="tologin"></a>

        <div id="wrapper">
            <div id="login" class="animate form">

                <section class="login_content">
                      <form id="ajaxForm" action="" method="post">

                        <h1>Login Form</h1>
                        <div>
                            <input type="text" class="form-control" placeholder="Username" name="username" required="" />
                        </div>
                        <div>
                            <input type="password" class="form-control" placeholder="Password" name="password" required="" />
                        </div>
                        <div>
                            <a class="btn btn-default submit" type="button" onclick="login()">Log in</a>
                            <a class="reset_pass" href="#">Lost your password?</a>
                        </div>
                        <div class="clearfix"></div>
                        <div class="separator">

                            <p class="change_link">New to site?
                                <a href="#toregister" class="to_register"> Create Account </a>
                            </p>
                            <div class="clearfix"></div>
                            <br />
                            <div>
                                <h1><i class="fa fa-paw" style="font-size: 26px;"></i> Gentelella Alela!</h1>

                                <p>©2015 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                            </div>
                        </div>
                    </form>
                    <!-- form -->
                </section>
                <!-- content -->
            </div>
            <div id="register" class="animate form">
                <section class="login_content">
                    <form>
                        <h1>Create Account</h1>
                        <div>
                            <input type="text" class="form-control" placeholder="Username" required="" />
                        </div>
                        <div>
                            <input type="email" class="form-control" placeholder="Email" required="" />
                        </div>
                        <div>
                            <input type="password" class="form-control" placeholder="Password" required="" />
                        </div>
                        <div>
                            <a class="btn btn-default submit" href="index.html">Submit</a>
                        </div>
                        <div class="clearfix"></div>
                        <div class="separator">

                            <p class="change_link">Already a member ?
                                <a href="#tologin" class="to_register"> Log in </a>
                            </p>
                            <div class="clearfix"></div>
                            <br />
                            <div>
                                <h1><i class="fa fa-paw" style="font-size: 26px;"></i> Gentelella Alela!</h1>

                                <p>©2015 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                            </div>
                        </div>
                    </form>
                    <!-- form -->
                </section>
                <!-- content -->
            </div>
        </div>
    </div>

</body>

<script type="text/javascript">
    $(function () {
        var rem = $.cookie("rememberPassword");
        if (rem != undefined) {
            $("input[name='username']").val($.cookie("username"));
            $("input[name='password']").val($.cookie("password"));
            $("input[name='rememberPassword']").attr("checked", "checked");
        }
    });
    function login() {
        var params;
        if (params == undefined)
            params = $("#ajaxForm").serialize();

        $.ajax({
            type: "post",
            url: '<c:url value="/u/valid"/>',
            data: params,
            dataType: "json",
            success: function (data) {
                $.removeCookie("username");
                $.removeCookie("password");
                if ($("input[name='rememberPassword']").is(":checked")) {
                    $.cookie("rememberPassword", true);
                    $.cookie("username", $("input[name='username']").val(), {expires: 30});
                    $.cookie("password", $("input[name='password']").val(), {expires: 30});
                } else {
                    $.removeCookie("rememberPassword");
                }
                window.location.href = '<c:url value="/setting/"/>';
            }
        })


    }
</script>
</html>
