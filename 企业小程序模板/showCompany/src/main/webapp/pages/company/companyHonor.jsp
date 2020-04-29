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

<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>荣誉资质</title>
</head>
<script type="text/javascript">

function uploadPic(){
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/uploadHonorsImage.do",
		dataType : 'json',
		fileElementId : 'uploadHonorPic',
		success : function(data) {
			var tmp = "";
			tmp = '<li class="appendImg"><img name="img" src="'+data.picUrl+'" class="tp">'
				  +'<p class="showp"><img src="img/pc_img/zjt.png" class="zjt"> <img src="img/pc_img/dele.png" class="dele"> <img src="img/pc_img/rjt.png" class="yjt"></p></li>'
			$("#addImg").before(tmp);	
		},
		error:function (){
			alert("图片上传失败");
		}
		
	});
}
function updateHonorImg(){
	var tmp = "";
	for(var i=0;i<$("img[name='img']").length;i++){
		tmp = tmp + $("img[name='img']")[i].getAttribute("src")+",";
	}
	tmp = tmp.substring(0,tmp.length-1);
	$.ajax({
		url:"<%=basePath%>company/updateHonorsImage.do",
		type:"post",
		dataType:"json",
		data:{
			"str":tmp
		},
		success:function(){
			alert("修改成功");
			window.location.reload();
		},
		error:function (){
			alert("修改失败");
		}
	});
}
function onoff(){
	$.ajax({
		url:"<%=basePath%>company/honorsImageOnoff.do",
		type:"post",
		dataType:"json",
		success:function (data){
			console.log("onoff success");
		},
		error:function (){
			console.log("onoff error");
		}
	});
}
</script>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 企业管理 <span class="c-gray en">&gt;</span>
		荣誉资质

		<div class="switch" data-animated="false"
			style="float: right; margin-right: 50px;">
			<s:if test="on_off == 1">
	    	<input onchange="onoff()" type="checkbox" checked />
	    </s:if>
	    <s:else>
	    	<input onchange="onoff()" type="checkbox" />
	    </s:else>
		</div>
	</nav>
	<article class="page-container">
		<p class="blue">我们是一个专业的团队，快把我们的证书亮出来!</p>
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12 "><span
					class="c-red">*</span>证书上传： <span class="stip">建议上传尺寸规格为290*400的图片，否则将被截取，仅只支持jpg、jpeg、png格式</span></label>
				<div class="formControls col-xs-12 col-sm-12 ">
					<ul class=" hb_t">
						<!--已经添加了图片的-->
						<s:iterator value="honors">
						<li class="appendImg"><img name="img" src="${chPic }"
							class="tp">
							<p class="showp">
								<img src="img/pc_img/zjt.png" class="zjt"> <img
									src="img/pc_img/dele.png" class="dele"> <img
									src="img/pc_img/rjt.png" class="yjt">
							</p></li>
						</s:iterator>
						<!--这个是还没有添加图片的-->
						<li id="addImg">
						<img src="img/pc_img/tp.png" class="tp"> 
						<input id="uploadHonorPic" name="image" onchange="uploadPic()" type="file" class="file">
						</li>
					</ul>

				</div>
			</div>

			<div class="row cl" style="margin-top: 50px;">
				<div class="col-xs-8 col-sm-9 ">
					<!-- <button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					 --><button class="btn btn-primary radius ml-30" type="button" onclick="updateHonorImg()">
						<i class="Hui-iconfont">&#xe632;</i> 保存修改
					</button>

				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript">
		$(function() {
			//添加图片模块hover后显示出删除模块
			$(".appendImg ").hover(function() {
				console.log("111")
				$(this).children('.showp').stop().fadeToggle()
			})
			//点击垃圾桶删除li
			$(".hb_t .dele").click(function() {
				$(this).parents(".appendImg").remove();
			})
			//右箭头更换位置
			$(".hb_t .yjt").click(function() {
				if ($(this).parents(".appendImg").next().hasClass("appendImg")) {
					$(this).parents(".appendImg").next().after($(this).parents(".appendImg"));
				}
			})
			//左箭头更换位置
			$(".hb_t .zjt").click(function() {
				console.log("aaa")
				if ($(this).parents(".appendImg").prev().hasClass("appendImg")) {
					$(this).parents(".appendImg").prev().before($(this).parents(".appendImg"));
				}
			})
	
		});
	</script>
</body>
</html>