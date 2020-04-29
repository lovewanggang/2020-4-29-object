<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<!--/meta 作为公共模版分离出去-->

<title>账户详情</title>
</head>
<script type="text/javascript">
$(function() {
	getData();
})
function getQueryString(id) { 
		var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	}
	
	function getData(){
	var id = getQueryString("id");
		$.ajax({
			url:"<%=basePath%>manage/findOneComAccount.do",
			type:"post",
			dataType:"json",
			async:false,
			data:{
				"idStr":id
			},
			success:function(data){
			$('#owner').html(data.account.owner);
			$('#bank').html(data.account.bank);
			$('#bankAccount').html(data.account.bankAccount);
			if(data.account.business_license != null){
				$("#BL").attr("src",data.account.business_license);
				}else{
				$("#BL").attr("src","img/pc_img/tp.png");
				}
			$('#jy').html(data.account.scope_of_business);
			},
			error:function (data){
				alert("获取数据失败");
			}
		});
	
	}
</script>
<body>

	<article class="page-container">
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">账户人：</label>
				<div class="formControls col-xs-8 col-sm-9 span_text">
					<span id="owner"></span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">开户银行：</label>
				<div class="formControls col-xs-8 col-sm-9 span_text">
					<span id="bank"></span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">银行账号：</label>
				<div class="formControls col-xs-8 col-sm-9 span_text">
					<span id="bankAccount"></span>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">营业执照：</label>
				<div class="formControls col-xs-8 col-sm-9 span_text">
					<a target="_blank" href="img/pc_img/demo.png"><img id="BL"
						src="img/pc_img/demo.png" style="height: 100px; widows: auto;"></a>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">经营范围：</label>
				<div class="formControls col-xs-8 col-sm-9 span_text">
					<span id="jy"></span>
				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->

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
