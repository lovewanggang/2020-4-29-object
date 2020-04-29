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
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script src="js/ajaxfileupload.js"></script>
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>添加子分类</title>
</head>
<script type="text/javascript">

function uploadProductClassPic(){
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/uploadClassPic.do",
		dataType : 'json',
		fileElementId : 'uploadPic',
		success : function(data) {
			$("#pclassPic").attr("src",data.picUrl);
		},
		error:function (){
			alert("图片上传失败");
		}
		
	});
}
function getQueryString(id) { 
	var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r != null) return unescape(r[2]); return null; 
}

	function add(){
		var id=getQueryString("id");
		$.ajax({
			url:"<%=basePath%>company/addProductClass.do",
			type:"post",
			dataType:"json",
			data:{
				"productClass.pcName":$("#name").val(),
				"productClass.pcClassPic":$("#pclassPic").attr("src"),
				"id":id
			},
			success:function(data){
				alert("添加成功");
			},
			error:function (){
				alert("添加失败");
			}
		});
	}
</script>
<body>

	<article class="page-container">
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12 "><span
					class="c-red">*</span>介绍图/广告图： <span class="stip">建议上传尺寸规格为长方形的图片，仅只支持jpg、jpeg、png格式</span></label>
				<div class="formControls col-xs-12 col-sm-12 ">
					<ul class="ggt_ul">
						<li class="appendImg"><img id="pclassPic" src="img/pc_img/tp.png"
							class="tp"> <input id="uploadPic" name="image" onchange="uploadProductClassPic()" type="file" class="file"></li>
					</ul>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12">子分类名称：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="name" type="text" class="input-text" value=""
						placeholder="建议不超过8个字">
				</div>
			</div>

			<div class="row cl">
				<div class="col-xs-12 col-sm-12 ">
					<!-- <button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					 --><button class="btn btn-primary radius ml-30" type="button" onclick="add()">
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

</body>
</html>
