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
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script src="js/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>添加公司信息</title>
</head>
<script type="text/javascript">
	function chuangjian(){
		if(!($("#appid").val())){
			alert("APPID不能为空");
			return;
		}
		if(!($("#appsecret").val())){
			alert("APPSECRET不能为空");
			return;
		}
		
		
		$.ajax({
			url:"<%=basePath%>manage/register.do",
			type:"post",
			dataType:"json",
			data:{
				"str":$("#appid").val(),
				"str2":$("#appsecret").val()
			},
			success:function(data){
			if(data.code == -1){
				alert("改APPID已经注册，你可以返回管理列表修改该公司的信息。");
			}else{
				$("#accountId").val(data.account.companyAccountId);
				$("#accountName").val(data.account.loginAccount);
				$("#password").val(data.account.loginPassword);
			}
			},
			error:function (data){
				alert("注册失败");
			}
		});
	}
	
	function uploadBLPic(){
		$.ajaxFileUpload({
			type : "post",
			url : "<%=basePath%>manage/upload_business_license.do",
			dataType : 'json',
			fileElementId : 'uploadPic',
			success : function(data) {
				$("#BLpic").attr("src",data.picUrl);	
			},
			error:function (){
				alert("图片上传失败");
			}
			
		});
	}
	
	function update(){
		$.ajax({
			url:"<%=basePath%>manage/updateComAccount.do",
			type:"post",
			dataType:"json",
			data:{
				"account.companyAccountId":$("#accountId").val(),
				"account.companyName":$("#companyName").val(),
				"account.owner":$("#owner").val(),
				"account.phoneNum":$("#phone").val(),
				"account.taocan": $("input[name=sex]:checked").val(),
				"account.bank":$("#bank").val(),
				"account.bankAccount":$("#bankAccount").val(),
				"account.scope_of_business":$("#scope_of_business").val(),
				"account.business_license":$("#BLpic").attr("src"),
				"registerDate":$("#datemax").val(),
			},
			success:function(data){
				alert("保存成功");
			},
			error:function (data){
				alert("保存失败");
			}
		});
	
	}
</script>
<body>
	<!--<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 客户管理后台 <span class="c-gray en">&gt;</span> 添加公司信息
	
	<div class="switch" data-animated="false"  style="float: right; margin-right: 50px;">
	    <input type="checkbox" checked />
	</div>
	</nav>-->
	<article class="page-container">
		<form class="form form-horizontal">
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">APPID：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="appid" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">APPSECRET：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="appsecret" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			<div class="row cl">
				<div
					class="col-xs-8 col-sm-9 col-sm-offset-3 col-xs-offset-4  mt-50">
					<input class="btn btn-primary radius mr-15" onclick="chuangjian()" type="button"
						value="&nbsp;&nbsp;注册&nbsp;&nbsp;">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">账户ID：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="accountId" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">账户名：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="accountName" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">账户密码：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="password" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			<br/>
			<hr style="height:1px;border:none;border-top:1px solid #555555;" />
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">公司名称：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="companyName" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">账户人：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="owner" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">联系电话：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="phone" type="text" class="input-text" value=""
						placeholder="请输入常用联系电话">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">套餐：</label>
				<div class="formControls col-xs-8 col-sm-9 skin-minimal">
					<div class="radio-box">
						<input name="sex" type="radio" id="sex-1" checked value="1"> <label
							for="sex-1">套餐一</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="sex-2" name="sex" value="2"> <label
							for="sex-2">套餐二</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="sex-3" name="sex" value="3"> <label
							for="sex-3">套餐三</label>
					</div>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">开户银行：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="bank" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">银行账号：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="bankAccount" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">经营范围：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<input id="scope_of_business" type="text" class="input-text" value="" placeholder="">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">营业执照：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<ul class="ggt_ul">
						<li class="appendImg"><img id="BLpic" src="img/pc_img/tp.png"
							class="tp"> <input id="uploadPic" name="image" onchange="uploadBLPic()" type="file" class="file"></li>
					</ul>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">加入时间：</label>
				<div class="formControls col-xs-8 col-sm-9">
					<!--<input type="text" class="input-text" value="" placeholder="">-->
					<input type="text" onfocus="WdatePicker({maxDate:'%y-%M-%d' })"
						id="datemax" class="input-text Wdate" style="width:200px;">
				</div>
			</div>
			<!-- <div class="row cl">
				<label class="form-label col-xs-4 col-sm-3">缴费状态：</label>
				<div class="formControls col-xs-8 col-sm-9">
					-50天
					<button class="pink_btn" type="button" onclick="txjf(this)">提醒缴费</button>
					<button class="dj_btn" type="button" onclick="dj(this)">冻结</button>
					<button class="green_btn" type="button">已缴费</button>
				</div>
			</div> -->
			<div class="row cl">
				<div
					class="col-xs-8 col-sm-9 col-sm-offset-3 col-xs-offset-4  mt-50">
					<input class="btn btn-primary radius mr-15" type="button" onclick="update()"
						value="&nbsp;&nbsp;保存&nbsp;&nbsp;">
				</div>
			</div>

		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>

	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer /作为公共模版分离出去-->
	<script type="text/javascript"
		src="<%=basePath%>static/My97DatePicker/4.8/WdatePicker.js"></script>




	<script type="text/javascript">
	//提醒缴费
	function txjf(obj) {
		layer.msg('提醒成功');
		$(obj).after('<button class="orange_btn"  disabled="disabled" >已提醒缴费</button>');
		$(obj).remove();
	//	layer.msg('提醒失败')
	}
	/*冻结账户*/
	function dj(obj) {
		layer.confirm('确认要冻结该用户吗？', function(index) {
			$.ajax({
				type : 'POST',
				url : '',
				dataType : 'json',
				success : function(data) {
					$(obj).parents("tr").remove();
					layer.msg('已冻结!', {
						icon : 1,
						time : 1000
					});
				},
				error : function(data) {
					console.log(data.msg);
				},
			});
		});
	}
	$(function() {
		$('.skin-minimal input').iCheck({
			checkboxClass : 'icheckbox-blue',
			radioClass : 'iradio-blue',
			increaseArea : '20%'
		});
		//联系电话的添加
		$(".add_phone").click(function() {
			$(this).parent(".phone_li").after('<div class="formControls col-xs-8 col-sm-9 phdele_li mt-10 col-sm-offset-3" ><input type="text" class="input-text" value="" placeholder="请输入常用联系电话" ><img src="img/pc_img/del_sx.png" class="del_phone"  ></div>');
		})
		//删除联系电话

		$("div").delegate(".del_phone", "click", function() {
			$(this).parent(".phdele_li").remove();
		});
	});
</script>
</body>
</html>
