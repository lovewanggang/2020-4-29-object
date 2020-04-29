<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath%>">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<!--/meta 作为公共模版分离出去-->

<title>修改密码</title>
</head>
<body>

	<article class="page-container">
		<form class="form form-horizontal" id="form-article-add">

			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-3">旧密码：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input id="oldP" type="password" class="input-text" value=""
						placeholder="建议不超过8个字">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-3">新密码：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input id="newP" type="password" class="input-text" value=""
						placeholder="建议不超过8个字">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-3 col-sm-3">再次输入：</label>
				<div class="formControls col-xs-8 col-sm-8">
					<input id="newPA" type="password" class="input-text" value=""
						placeholder="建议不超过8个字">
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-8 col-sm-8  col-offset-3 mt-20">
					<!-- <button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					 --><button class="btn btn-primary radius ml-30" type="button" onclick="edit()">
						<i class="Hui-iconfont">&#xe632;</i> 修改
					</button>

				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript">
		function edit(){
			if($("#newP").val() == $("#newPA").val()){
				var param = "",
				param = $("#oldP").val()+","+$("#newP").val();
				
				$.ajax({
					url:"<%=basePath%>company/updateAccountPassword.do",
					type:"post",
					dataType:"json",
					data:{
						"str":param
					},
					success:function (data){
						if(data.code == 0){
							alert("修改成功");
						}else if(data.code == -1){
							alert("旧密码错误");
						}else{
							alert("发生未知错误");
						}
					},
					error:function (){
						alert("修改失败");
					}
				});
			}else{
				alert("两次输入的新密码不统一");
			}
				
		}
	
	</script>
</body>
</html>
