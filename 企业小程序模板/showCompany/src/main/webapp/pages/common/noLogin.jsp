<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />

	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>错误</title>
</head>
<script type="text/javascript">

function toLogin(){
	window.top.location="<%=basePath%>company/toLogin.do";
}
</script>
<body>
<section class="container-fluid page-404 minWP text-c">
	<p class="error-title">
		<!--图片路径可改为2,3-->
		<img src="img/pc_img/2.jpg"  style="height: 200px;">
		
	</p>
		<p class="error-description">登录超时，请重新登录</p>
	<p class="error-info">
		<a href="javascript:;" onclick="toLogin()" class="c-primary" style="text-decoration: underline; font-size: 20px;">去登录</a>
		
	</p>
</section>
</body>
</html>
