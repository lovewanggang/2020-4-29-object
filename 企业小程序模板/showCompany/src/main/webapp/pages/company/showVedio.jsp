<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
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
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script src="js/ajaxfileupload.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>合作伙伴</title>
</head>
<script type="text/javascript">

function uploadVedio(){
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/uploadVedio.do",
		dataType : 'json',
		fileElementId : 'vedio',
		success : function(data) {
			$("#showVedio").attr("src",data.vedioUrl);
		},
		error:function (){
			alert("图片上传失败");
		}
			
	});
}
function saveOrUpdate(id){
	var url = "";
	if(id != undefined && id != null && id != ""){
		url = "<%=basePath%>company/updateVedio.do";
	}else{
		url = "<%=basePath%>company/addVedio.do";
	}
	$.ajax({
		url:url,
		type:"post",
		dataType:"json",
		data:{
			"vedio.companyShowVedioId":id,
			"vedio.vedioTitle":$("#vedioTitle").val(),
			"vedio.vedioUrl":$("#showVedio").attr("src")
		},
		success:function (data){
			alert("已保存");
			window.location.reload();
		},
		error:function (){
			alert("保存失败");
		}
	});
}
function onoff(){
	$.ajax({
		url:"<%=basePath%>company/vedioOnoff.do",
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
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 企业管理 <span class="c-gray en">&gt;</span> 视频展示
	
	<div class="switch" data-animated="false"  style="float: right; margin-right: 50px;">
	    <s:if test="on_off == 1">
	    	<input onchange="onoff()" type="checkbox" checked />
	    </s:if>
	    <s:else>
	    	<input onchange="onoff()" type="checkbox" />
	    </s:else>
	</div>
	</nav>
<article class="page-container">
	<p class="blue">展示产品视频能更加真是了解企业业务或产品，提高服务质量!</p>
	<form class="form form-horizontal" id="form-article-add">
		<div class="row cl">
			<label class="form-label col-xs-12 col-sm-8 "><span class="c-red">*</span>视频标题：</label>
			<div class="formControls col-xs-12 col-sm-8 " >
				<input id="vedioTitle" type="text" class="input-text" value="${vedio.vedioTitle }" maxlength="18" placeholder="请输入视频标题，不超过18个字" >
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-12 col-sm-8 "><span class="c-red">*</span>上传视频：<span class="stip">建议上传的视频不要太大。</span></label>
			<div class="formControls col-xs-12 col-sm-8 " >
				<!--<button class="pink_btn">上传视频</button>-->
				<video id="showVedio" src="${vedio.vedioUrl }" controls="controls" style="max-width:400px">
				您的浏览器不支持video
				</video>
				<s:if test="vedio.vedioUrl != null">
					<div class="upload_video " >
						<button class="pink_btn">更换视频</button>
						<input id="vedio" name="image" type="file" class="file" onchange="uploadVedio()">
					</div>
				</s:if>
				<s:else>
					<div class="upload_video " >
						<button class="pink_btn">上传视频</button>
						<input id="vedio" name="image" type="file" class="file" onchange="uploadVedio()">
					</div>
				</s:else>
			</div>
		</div>

		<div class="row cl" style="margin-top: 50px;">
			<div class="col-xs-8 col-sm-9 ">
				<!-- <button  class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button> -->
				<button  class="btn btn-primary radius ml-30" type="button" onclick="saveOrUpdate(${vedio.companyShowVedioId})"><i class="Hui-iconfont" >&#xe632;</i> 保存</button>
				
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 

</body>
</html>