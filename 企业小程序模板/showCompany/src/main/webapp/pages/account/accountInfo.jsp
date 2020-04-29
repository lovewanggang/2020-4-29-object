<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<!--/meta 作为公共模版分离出去-->

<title>账户信息</title>
</head>

<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 账户管理 <span class="c-gray en">&gt;</span>
		账户信息

		<!-- <div class="switch" data-animated="false"
			style="float: right; margin-right: 50px;">
			<input type="checkbox" checked />
		</div> -->
	</nav>
	<article class="page-container">
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">账户名称：</label>
				<div class="formControls col-xs-4 col-sm-5">
				${account.loginAccount }
					<!-- <input type="text" class="input-text" value="" placeholder=""> -->
				</div>

			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">账户密码：</label>
				<div class="formControls col-xs-4 col-sm-5">
				${account.loginPassword }
				<!-- 	<input type="text" class="input-text" value="" placeholder=""> -->
				</div>
				<!-- <div class="formControls col-xs-4 col-sm-4">
					<a class="blu" href="javascript:void(0)"
						onclick="alter_pd('修改密码','修改密码.html','500','300')">修改密码</a>
				</div> -->
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">到期时间：</label>
				<!--<div class="formControls col-xs-4 col-sm-5 blu">
				距离缴费20天
			</div>-->
				<div class="formControls col-xs-4 col-sm-5 redd">
					<!-- <s:date name="validityDate" format="yyyy-MM-dd" /> -->
					<%-- <fmt:formatDate pattern="yyyy-MM-dd" value="${account.validityDate}" /> --%>
					2018-08-08</div>

			</div>
			<!-- <div class="row cl">
				<div
					class="col-xs-8 col-sm-9 col-sm-offset-3 col-xs-offset-4  mt-30">
					<input class="btn btn-primary radius mr-15" type="submit"
						value="&nbsp;&nbsp;保存&nbsp;&nbsp;"> <input
						class="btn btn-default radius" type="button"
						value="&nbsp;&nbsp;取消&nbsp;&nbsp;">
				</div>
			</div> -->

		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>

</body>

<script>
		/*修改密码*/
		function alter_pd(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>

</html>
