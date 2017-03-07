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

                <!--  content -->
                <div class="">

                    <div class="page-title">
                        <div class="title_left">
                            <h3>
                                配置管理
                                <small style="color: red">
                                    <c:if test="${not empty message}">${message}</c:if><%--Some examples to get you started--%>
                                </small>
                            </h3>
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

                    <div class="row">

                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <div class="x_panel">
                                <div class="x_title">
                                    <h2>列表信息 <small></small></h2>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li>
                                            <button type="button" class="btn btn-primary" onclick="addnode()">添加</button>

                                        </li>

                                    </ul>
                                    <div class="clearfix"></div>
                                </div>

                                <div class="x_content">

                                    <%--<p>Add class <code>bulk_action</code> to table for bulk actions options on row select</p>--%>

                                    <table class="table table-striped responsive-utilities jambo_table ">
                                        <thead>
                                        <tr class="headings">

                                            <th class="column-title">名称 </th>
                                            <th class="column-title">地址 </th>
                                            <th class="column-title no-link last"><span class="nobr">操作</span>
                                            </th>
                                        </tr>
                                        </thead>

                                        <tbody>
                                        <c:forEach items="${lists}" var="list">

                                        <tr class="odd pointer">

                                            <td class=" ">${list.Node}</td>
                                            <td class=" ">${list.Address}</td>
                                            <td class=" last"><a href="#" onclick="deletenode('${list.Node}')" ><span class="glyphicon glyphicon-trash"></span> </a>
                                            </td>
                                        </tr>
                                        <%--<tr class="even pointer">--%>
                                            <%--<td class=" ">121000040</td>--%>
                                            <%--<td class=" ">May 24, 2014 11:47:56 PM </td>--%>
                                            <%--<td class=" last"><a href="#">View</a>--%>
                                            <%--</td>--%>
                                        <%--</tr>--%>
                                        </c:forEach>
                                        </tbody>

                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- /content -->


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

    <script type="text/javascript">
        function deletenode(node) {
            $.ajax({
                type: "post",
                url: '<c:url value="/setting/delete"/>',
                data: {"node": node},
                dataType: "json",
                success: function (data) {
                    if (data.success)
                        window.location.href = '<c:url value="/setting/"/>';
                }
            })
        }
        function addnode() {
            window.location.href = '<c:url value="/setting/add"/>';
        }
    </script>


    <!-- /datepicker -->
    <!-- /footer content -->
</body>

</html>
