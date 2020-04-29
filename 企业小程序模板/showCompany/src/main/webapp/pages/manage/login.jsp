<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<title>登录</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/my.css">
</head>
<script type="text/javascript">
function login(){
	$.ajax({
		url:"<%=basePath%>manage/login.do",
		type:"post",
		dataType:"json",
		data:{
			"str":$("#name").val(),
			"str2":$("#password").val()
		},
		success:function (data){
			if(data.code == 0){
				window.location.href = "<%=basePath%>manage/toWelcome.do";
			}else if(data.code == 1){
				alert("帐号或密码错误");
			}else{
				alert("error");
			}
		},
		error:function (){
			alert("error");
		}
	});
}
</script>
<body class="login_bg">
	<div class="text-center">
		<p class="login_name">小程序客户管理</p>
		<div class="box_login">
			<form>
				<ul>
					<li class="input_li"><input id="name" type="text" placeholder="帐号" /> <img
						src="img/pc_img/lock.png" class="lock"></li>
					<li class="input_li"><input id="password" type="password" placeholder="密码" />
						<img src="img/pc_img/user.png" class="lock"></li>
					<li class="nn_li"><input type="button" value="登录" onclick="login()" /></li>
					<!-- <li class="autologin"><label><input id="check" type="checkbox">下次自动登录</label>
					</li> -->
				</ul>
			</form>
		</div>
	</div>
</body>
</html>
