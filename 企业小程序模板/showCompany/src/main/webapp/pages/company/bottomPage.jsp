<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script src="js/ajaxfileupload.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<!--/meta 作为公共模版分离出去-->

<title>联系我们</title>
</head>
<script type="text/javascript">
function save(){
	var phones = "";
	for(var i=0;i<$(".phonediv input[name='phone']").length;i++){
		phones = phones + $(".phonediv input[name='phone']").get(i).value+",";
	} 
	phones = phones.substring(0,phones.length-1);
	var qqs = "";
	for(var i=0;i<$(".qqdiv input[name='phone']").length;i++){
		qqs = qqs + $(".qqdiv input[name='phone']").get(i).value+",";
	} 
	qqs = qqs.substring(0,qqs.length-1);
	var weixins = "";
	for(var i=0;i<$(".weixindiv input[name='phone']").length;i++){
		weixins = weixins + $(".weixindiv input[name='phone']").get(i).value+",";
	} 
	weixins = weixins.substring(0,weixins.length-1);
	$.ajax({
		url:"<%=basePath%>company/updateCompanyInfo.do",
		type:"post",
		dataType:"json",
		data:{
			"str":phones,
			"str2":weixins,
			"str3":qqs,
			"company.companyId":$("#companyId").val(),
			"company.comName":$("#comName").val(),
			"company.comContact":$("#comContact").val(),
			"company.comContactLogo":$("#contactPic").attr("src"),
			"company.comAddr":$("#comAddr").val(),
			"company.comFax":$("#comFax").val(),
			"company.comEmail":$("#comEmail").val(),
			"company.comQRcode":$("#comQRcode").attr("src"),
		},
		success:function (data){
			alert("修改已生效");
			window.location.reload();
		},
		error:function (){
			alert("保存失败");
		}
	}); 
	
}
function uploadPic(){
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/uploadLogoPic.do",
		dataType : 'json',
		fileElementId : 'uploadContact',
		success : function(data) {
			$("#contactPic").attr("src",data.picUrl);
		},
		error:function (){
			alert("图片上传失败");
		}
		
	});
}
function uploadQRPic(){
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/uploadQRcode.do",
		dataType : 'json',
		fileElementId : 'uploadQRcode',
		success : function(data) {
			$("#comQRcode").attr("src",data.picUrl);
		},
		error:function (){
			alert("图片上传失败");
		}
		
	});
}

</script>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 企业管理 <span class="c-gray en">&gt;</span>
		联系我们

		<div class="switch" data-animated="false"
			style="float: right; margin-right: 50px;">
			<input type="checkbox" checked />
		</div>
	</nav>
	<article class="page-container">
		<p class="col-xs-12 col-sm-12">正确填写，方便用户联系我们唷。</p>
		<form class="form form-horizontal" id="form-article-add">
			<input id="companyId" type="hidden" value="${company.companyId }">
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>联系人照片： <span class="stip">建议上传尺寸规格为正方形的图片，仅只支持jpg、jpeg、png格式</span></label>
				<div class="formControls col-xs-12 col-sm-12">
					<ul class="tp_ul">
						<li class="appendImg">
						<s:if test="company.comContactLogo != null">
							<img id="contactPic" src="${company.comContactLogo }" class="tp"> 
						</s:if>
						<s:else>
							<img id="contactPic" src="img/pc_img/tp.png" class="tp"> 
						 </s:else>
							
							<input id="uploadContact" name="image"  onchange="uploadPic()" type="file" class="file"></li>
					</ul>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>联系人：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="comContact" type="text" class="input-text" value="${company.comContact }" placeholder="请输入联系人">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>公司名字：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="comName" type="text" class="input-text" value="${company.comName }"
						placeholder="请输入公司名字">
				</div>
			</div>
			<div class="row cl phonediv">
				<label class="form-label col-xs-12 col-sm-12">联系电话：</label>
				<s:if test="comPhones.size() == 0">
					<div class="formControls col-xs-12 col-sm-12  phone_li ">
					<input name="phone" type="text" class="input-text"
						placeholder="请输入常用联系电话"> <img name="phoneimg"
						src="img/pc_img/ad-sx.png" class="add_phone">
				</div>
				</s:if>
				<s:iterator value="comPhones"> 
					<div class="formControls col-xs-12 col-sm-12 phdele_li phone_li mt-10">
						<input name="phone" type="text" class="input-text" value="${comPhone }"
							placeholder="请输入常用联系电话"> <img name="phoneimg"
							src="img/pc_img/del_sx.png" class="del_phone">
					</div>
				</s:iterator>
				<!-- <div class="formControls col-xs-12 col-sm-12  phone_li ">
						<input name="phone" type="text" class="input-text" value=""
							placeholder="请输入联系电话"> <img
							src="img/pc_img/ad-sx.png" class="add_phone"> -->
				</div>
			</div>
			<div class="row cl weixindiv">
				<label class="form-label col-xs-12 col-sm-12">微信：</label>
				<s:if test="weixins.size() == 0">
					<div class="formControls col-xs-12 col-sm-12  phone_li">
					<input name="phone" type="text" class="input-text"
						placeholder="请输入微信号"> <img name="wximg"
						src="img/pc_img/ad-sx.png" class="add_phone">
				</div>
				</s:if>
				
				<s:iterator value="weixins">
					<div class="formControls col-xs-12 col-sm-12  phdele_li phone_li mt-10">
						<input name="phone" type="text" class="input-text" value="${weixin }"
							placeholder="请输入微信号"> <img name="wximg"
							src="img/pc_img/del_sx.png" class="del_phone">
					</div>
				</s:iterator>
				<!-- <div class="formControls col-xs-12 col-sm-12  phone_li">
					<input name="weixin" type="text" class="input-text" value=""
						placeholder="请输入微信号"> <img
						src="img/pc_img/ad-sx.png" class="add_phone">
				</div> -->
			</div>
			<div class="row cl qqdiv" >
				<label class="form-label col-xs-12 col-sm-12">QQ：</label>
				<s:if test="comQQs.size() == 0">
					<div class="formControls col-xs-12 col-sm-12  phone_li">
					<input name="phone" type="text" class="input-text"
						placeholder="请输入正确的QQ号"> <img name="qqimg"
						src="img/pc_img/ad-sx.png" class="add_phone">
				</div>
				</s:if>
				<s:iterator value="comQQs">
					<div class="formControls col-xs-12 col-sm-12  phdele_li phone_li mt-10" >
						<input name="phone" type="text" class="input-text" value="${comQQ }"
							placeholder="请输入正确的QQ号"> <img name="qqimg"
							src="img/pc_img/del_sx.png" class="del_phone">
					</div>
				</s:iterator>
				<!-- <div class="formControls col-xs-12 col-sm-12  phone_li">
					<input name="qq" type="text" class="input-text" value=""
						placeholder="请输入正确的QQ号"> <img name="qqimg"
						src="img/pc_img/ad-sx.png" class="add_phone"> -->
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12">传真：</label>
				<div class="formControls col-xs-12 col-sm-12 ">
					<input id="comFax" type="text" class="input-text" value="${company.comFax }"
						placeholder="请输入正确的传真号码"> 
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12">邮箱：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="comEmail" type="text" class="input-text" value="${company.comEmail }"
						placeholder="请输入正确的邮箱号码"> 
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12">地址：</label>
				
				<div class="formControls col-xs-12 col-sm-12 mt-10">
					<input id="comAddr" type="text" class="input-text" value="${company.comAddr }"
						placeholder="请输入详细地址信息，如道路、门牌号、创业园等">
				</div>

			</div>

			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>二维码： <span class="stip">仅只支持jpg、jpeg、png格式</span></label>
				<div class="formControls col-xs-12 col-sm-12">
					<ul class="tp_ul">
						<li class="appendImg">
						<s:if test="company.comQRcode != null">
							<img id="comQRcode" src="${company.comQRcode }" class="tp">
						</s:if>
						<s:else>
							<img id="comQRcode" src="img/pc_img/tp.png" class="tp">
						 </s:else>
						 <input id="uploadQRcode" name="image" onchange="uploadQRPic()" type="file" class="file"></li>
					</ul>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2 mt-40">
					<!-- <button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					 --><button class="btn btn-primary radius ml-30" type="button" onclick="save()">
						<i class="Hui-iconfont">&#xe632;</i> 保存修改
					</button>

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



	<!--省市区插件-->
	<script src="static/city/distpicker.data.js"></script>
	<script src="static/city/distpicker.js"></script>
	<script src="static/city/main.js"></script>




	<script type="text/javascript">
$(function(){
	
	//联系电话的添加
	/* $(".add_phone").click(function(){
		$(this).parent(".phone_li").after('<div class="formControls col-xs-12 col-sm-12  phdele_li mt-10" ><input name="phone" type="text" class="input-text" value="" placeholder="请输入联系电话" ><img src="img/pc_img/del_sx.png" class="del_phone"  ></div>');
		var oEvent = ev || event;
		oEvent.cancelBubble = true;
        oEvent.stopPropagation();
	})
	$(".add_qq").click(function(){
		$(this).parent(".phone_li").after('<div class="formControls col-xs-12 col-sm-12  phdele_li mt-10" ><input name="qq" type="text" class="input-text" value="" placeholder="请输入微信号" ><img src="img/pc_img/del_sx.png" class="del_phone"  ></div>');
		var oEvent = ev || event;
		oEvent.cancelBubble = true;
        oEvent.stopPropagation();
	})
	$(".add_weixin").click(function(){
		$(this).parent(".phone_li").after('<div class="formControls col-xs-12 col-sm-12  phdele_li mt-10" ><input name="weixin" type="text" class="input-text" value="" placeholder="请输入联系电话" ><img src="img/pc_img/del_sx.png" class="del_phone"  ></div>');
		var oEvent = ev || event;
		oEvent.cancelBubble = true;
        oEvent.stopPropagation();
	}) */
	//删除联系电话
	$("div").delegate(".del_phone","click",function(){
		console.log("执行删除");
	  $(this).parent(".phdele_li").remove();
	});
	
	changeP();//改联系电话的图标
	$('.phone_li').on('click', 'img.add_phone', function(event) {
		$(this).parent(".phone_li").parent().append('<div class="formControls col-xs-12 col-sm-12  phdele_li mt-10" ><input name="phone" type="text" class="input-text" value="" placeholder="" ><img name="phoneimg" src="img/pc_img/del_sx.png" class="del_phone"  ></div>');
	})
});
function changeP(){
	$("img[name='phoneimg']")[0].src = "img/pc_img/ad-sx.png";
	$("img[name='phoneimg']")[0].setAttribute("class","add_phone");
	$("img[name='wximg']")[0].src = "img/pc_img/ad-sx.png";
	$("img[name='wximg']")[0].setAttribute("class","add_phone");
	$("img[name='qqimg']")[0].src = "img/pc_img/ad-sx.png";
	$("img[name='qqimg']")[0].setAttribute("class","add_phone");
}
</script>
</body>
</html>
