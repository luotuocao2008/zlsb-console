<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:include page="../common/header.jsp"></jsp:include>

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
                        <h3>
                            基本信息
                        </h3>
                    </div>

                    <div class="title_right">
                        <%--<div class="col-md-5 col-sm-5 col-xs-12 form-group pull-right top_search">--%>
                            <%--<div class="input-group">--%>
                                <%--<input type="text" class="form-control" placeholder="Search for...">--%>
                                <%--<span class="input-group-btn">--%>
                            <%--<button class="btn btn-default" type="button">Go!</button>--%>
                        <%--</span>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    </div>
                </div>
                <div class="clearfix"></div>

                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div class="x_panel">

                            <div class="x_content">
                                <form class="form-horizontal form-label-left" novalidate>

                                    <%--<p>For alternative validation library <code>parsleyJS</code> check out in the <a href="form.html">form page</a>--%>
                                    <%--</p>--%>
                                    <span class="section">Personal Info</span>

                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="name">Name <span class="required">*</span>
                                        </label>
                                        <div class="input-group col-md-4 col-sm-4 col-xs-12">
                                            <input id="name" class="form-control"  name="name" placeholder="both name(s) e.g Jon Doe"  type="text">
                                            <span class="input-group-btn"> <button class="btn btn-default" type="button">Go!</button> </span>
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="email" id="email" name="email" required="required" class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Confirm Email <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="email" id="email2" name="confirm_email" data-validate-linked="email" required="required" class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="number">Number <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="number" id="number" name="number" required="required" data-validate-minmax="10,100" class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="website">Website URL <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input type="url" id="website" name="website" required="required" placeholder="www.website.com" class="form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">Occupation <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input id="occupation" type="text" name="occupation" data-validate-length-range="5,20" class="optional form-control col-md-7 col-xs-12">
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label for="password" class="control-label col-md-3">Password</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input id="password" type="password" name="password" data-validate-length="6,8" class="form-control col-md-7 col-xs-12" required="required">
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label for="password2" class="control-label col-md-3 col-sm-3 col-xs-12">Repeat Password</label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <input id="password2" type="password" name="password2" data-validate-linked="password" class="form-control col-md-7 col-xs-12" required="required">
                                        </div>
                                    </div>
                                    <div class="item form-group">
                                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="textarea">Textarea <span class="required">*</span>
                                        </label>
                                        <div class="col-md-6 col-sm-6 col-xs-12">
                                            <textarea id="textarea" required="required" name="textarea" class="form-control col-md-7 col-xs-12"></textarea>
                                        </div>
                                    </div>
                                    <div class="ln_solid"></div>
                                    <div class="form-group">
                                        <div class="col-md-6 col-md-offset-3">
                                            <button type="submit" class="btn btn-primary">Cancel</button>
                                            <button id="send" type="submit" class="btn btn-success">Submit</button>
                                        </div>
                                    </div>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- footer content -->
            <jsp:include page="../common/footer.jsp"></jsp:include>
            <!-- /footer content -->

        </div>
        <!-- /page content -->
    </div>

</div>

<div id="custom_notifications" class="custom-notifications dsp_none">
    <ul class="list-unstyled notifications clearfix" data-tabbed_notifications="notif-group">
    </ul>
    <div class="clearfix"></div>
    <div id="notif-group" class="tabbed_notifications"></div>
</div>


<!-- bootstrap progress js -->
<script src="/resource/js/progressbar/bootstrap-progressbar.min.js"></script>
<script src="/resource/js/nicescroll/jquery.nicescroll.min.js"></script>
<!-- icheck -->
<script src="/resource/js/icheck/icheck.min.js"></script>

<script src="/resource/js/custom.js"></script>
<!-- form validation -->
<script src="/resource/js/validator/validator.js"></script>
<%--<script>--%>
    <%--// initialize the validator function--%>
    <%--validator.message['date'] = 'not a real date';--%>

    <%--// validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':--%>
    <%--$('form')--%>
        <%--.on('blur', 'input[required], input.optional, select.required', validator.checkField)--%>
        <%--.on('change', 'select.required', validator.checkField)--%>
        <%--.on('keypress', 'input[required][pattern]', validator.keypress);--%>

    <%--$('.multi.required')--%>
        <%--.on('keyup blur', 'input', function () {--%>
            <%--validator.checkField.apply($(this).siblings().last()[0]);--%>
        <%--});--%>

    <%--// bind the validation to the form submit event--%>
    <%--//$('#send').click('submit');//.prop('disabled', true);--%>

    <%--$('form').submit(function (e) {--%>
        <%--e.preventDefault();--%>
        <%--var submit = true;--%>
        <%--// evaluate the form using generic validaing--%>
        <%--if (!validator.checkAll($(this))) {--%>
            <%--submit = false;--%>
        <%--}--%>

        <%--if (submit)--%>
            <%--this.submit();--%>
        <%--return false;--%>
    <%--});--%>

    <%--/* FOR DEMO ONLY */--%>
    <%--$('#vfields').change(function () {--%>
        <%--$('form').toggleClass('mode2');--%>
    <%--}).prop('checked', false);--%>

    <%--$('#alerts').change(function () {--%>
        <%--validator.defaults.alerts = (this.checked) ? false : true;--%>
        <%--if (this.checked)--%>
            <%--$('form .alert').remove();--%>
    <%--}).prop('checked', false);--%>
<%--</script>--%>

</body>

</html>