<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="zh">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Admin ZSLB Console</title>

    <!-- Bootstrap core CSS -->

    <link href="/resource/css/bootstrap.min.css" rel="stylesheet">

    <link href="/resource/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="/resource/css/animate.min.css" rel="stylesheet">

    <!-- Custom styling plus plugins -->
    <link href="/resource/css/custom.css" rel="stylesheet">
    <link href="/resource/css/icheck/flat/green.css" rel="stylesheet">
    <!-- select2 -->
    <link href="/resource/css/select/select2.min.css" rel="stylesheet">
    <!-- switchery -->
    <link rel="stylesheet" href="/resource/css/switchery/switchery.min.css" />

    <script src="/resource/js/jquery.min.js"></script>

    <!--[if lt IE 9]>
    <script src="../assets/js/ie8-responsive-file-warning.js"></script>
    <![endif]-->

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>


<body class="nav-md">

<div class="container body">


    <div class="main_container">

        <!-- left menu -->
        <jsp:include page="../common/left.jsp"></jsp:include>

        <!-- /left menu -->

        <!-- top navigation -->
        <jsp:include page="../common/topnav.jsp"></jsp:include>
        <!-- /top navigation -->

        <!-- page content -->
        <div class="right_col" role="main">
            <div class="">

                <div class="page-title">
                    <div class="title_left">
                        <h3>配置管理</h3>
                    </div>
                    <%--<div class="title_right">--%>
                        <%--<div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">--%>
                            <%--<div class="input-group">--%>
                                <%--<input type="text" class="form-control" placeholder="Search for...">--%>
                                <%--<span class="input-group-btn">--%>
                            <%--<button class="btn btn-default" type="button">Go!</button>--%>
                        <%--</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                </div>
                <div class="clearfix"></div>




                <div class="x_panel">
                    <div class="x_title">
                        <h2>配置信息 <small>请填写下列信息！</small></h2>
                        <%--<ul class="nav navbar-right panel_toolbox">--%>
                            <%--<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>--%>
                            <%--</li>--%>
                            <%--<li class="dropdown">--%>
                                <%--<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>--%>
                                <%--<ul class="dropdown-menu" role="menu">--%>
                                    <%--<li><a href="#">Settings 1</a>--%>
                                    <%--</li>--%>
                                    <%--<li><a href="#">Settings 2</a>--%>
                                    <%--</li>--%>
                                <%--</ul>--%>
                            <%--</li>--%>
                            <%--<li><a class="close-link"><i class="fa fa-close"></i></a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <br />
                        <form id="node-form" data-parsley-validate class="form-horizontal form-label-left">

                        <h4>节点信息</h4>
                        <p class="font-gray-dark">
                            Using the grid system you can control the position of the labels. The form example below has the <b>col-md-10</b> which sets the width to 10/12 and <b>center-margin</b> which centers it.
                        </p>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="node">节点名称<span class="required">*</span>
                                </label>
                                <div class="col-md-4">
                                    <input type="text" name="node" id="node" required="required" class="form-control col-md-7 col-xs-7">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="address">ip地址 <span class="required">*</span>
                                </label>
                                <div class="col-md-7">
                                    <input type="text" name="address" id="address" required="required" class="form-control col-md-7 col-xs-7">
                                </div>
                            </div>


                        <h4>服务信息</h4>
                        <p class="font-gray-dark">
                            For making labels vertical you have two options, either use the apropiate grid <b>.col-</b> class or wrap the form in the <b>form-vertical</b> class.
                        </p>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="serviceName">服务名称<span class="required">*</span>
                                </label>
                                <div class="col-md-7">
                                    <input type="text"  name="serviceName" id="serviceName" required="required" class="form-control col-md-7 col-xs-7">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="serviceAddress">ip地址 <span class="required">*</span>
                                </label>
                                <div class="col-md-7">
                                    <input type="text"  name="serviceAddress" id="serviceAddress" required="required" class="form-control col-md-7 col-xs-7">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3" for="servicePort">端口 <span class="required">*</span>
                                </label>
                                <div class="col-md-7">
                                    <input type="text" name="servicePort" id="servicePort" required="required" class="form-control col-md-3 col-xs-5">
                                </div>
                            </div>

                        <div class="ln_solid"></div>
                        <div class="form-group">
                            <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-5">
                                <button type="submit" class="btn btn-primary">取消</button>
                                <button type="button" class="btn btn-success" onclick="saveNode()">提交</button>
                            </div>
                        </div>
                        </form>

                    </div>

                </div>
            </div>
            <!-- /page content -->

            <!-- footer content -->
            <jsp:include page="../common/footer.jsp"></jsp:include>
            <!-- /footer content -->

        </div>

    </div>
</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
</div>

<script src="/resource/js/bootstrap.min.js"></script>


<!-- bootstrap progress js -->
<script src="/resource/js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="/resource/js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script src="/resource/js/icheck/icheck.min.js"></script>

<script src="/resource/js/custom.js"></script>

<script type="text/javascript">
    $(function () {
        var url ='<c:url value="/setting/"/>';
        $('#sidebar-menu a[href="' + url + '"]').parent('li').addClass('current-page');
        $('#sidebar-menu a').filter(function () {
            return this.href == url;
        }).parent('li').addClass('current-page').parent('ul').slideDown().parent().addClass('active');
    });

    function saveNode() {

        $.ajax({
            type: "post",
            url: '<c:url value="/setting/save"/>',
            data: $("#node-form").serialize(),
            dataType: "json",
            success: function (data) {
                if (data.success){
                    window.location.href = '<c:url value="/setting/"/>';

                }else {

                }
            }
        })

    }
</script>



</body>

</html>
