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
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>
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

<title>添加团队风采</title>
</head>
<script type="text/javascript">
var ue = UE.getEditor('editor');
var id = getQueryString("id");
$(function(){
	getData(ue);
	
		ue.addListener('blur',function(editor){
			var text = "";
			text = ue.getContentTxt().substring(0,30);
			if(ue.getContentTxt().length > 30){
				text = text+"...";
			}
			$("#content").text(text);
			
		});
})
function getQueryString(id) { 
		var reg = new RegExp("(^|&)" + id + "=([^&]*)(&|$)", "i"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r != null) return unescape(r[2]); return null; 
	}

function getData(ue){
	
	$.ajax({
		url:"<%=basePath%>company/findOneComActivity.do",
		type:"post",
		dataType:"json",
		data:{
			"id":id
		},
		success:function(data){
			$("#activityTitlePic").attr("src",data.companyActivities.activityTitlePic);
			$("#activityTitle").val(data.companyActivities.activityTitle);
			$("#content").val(data.companyActivities.activityPluckText);
			ue.ready(function() {//编辑器初始化完成再赋值  
	            ue.setContent(data.companyActivities.activityContent);  //赋值给UEditor  
	        });
		},
		error:function (){
			alert("获取数据失败");
		}
	});
	
}

function uploadActivityTitlePicPic(){
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/uploadActivityPic.do",
		dataType : 'json',
		fileElementId : 'uploadPic',
		success : function(data) {
			$("#activityTitlePic").attr("src",data.picUrl);
		},
		error:function (){
			alert("图片上传失败");
		}
		
	});
}

 	function update(){
 		if($("#activityTitle").val() == null){
 			alert("文章标题不能为空");
 			return;
 		}
 		if(ue.getPlainTxt().length <= 1){
 			alert("文章描述不能为空");
 			return;
 		}
		$.ajax({
			url:"<%=basePath%>company/updateComActivity.do",
			type:"post",
			dataType:"json",
			data:{
				"companyActivities.companyActivitiesId":id,
				"companyActivities.activityTitlePic":$("#activityTitlePic").attr("src"),
				"companyActivities.activityTitle":$("#activityTitle").val(),
				"companyActivities.activityContent":ue.getContent(),
				"companyActivities.activityPluckText":$("#content").text(),
			},
			success:function(data){
				alert("成功修改");
				var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
				parent.layer.close(index);  // 关闭layer
			},
			error:function (){
				
			}
		});
	}
	
</script>
<body>
	<article class="page-container">
		<p class="blue">让大家知道我们是一个友爱、优秀的集体，我们朝气蓬勃，热爱生活：</p>
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>文章封面：</label>
				<div class="formControls col-xs-12 col-sm-12 ">
					<ul class="tp_ul">
						<li class="appendImg"><img id="activityTitlePic" src="img/pc_img/tp.png" class="tp">
							<input id="uploadPic" name="image" onchange="uploadActivityTitlePicPic()" type="file" class="file"></li>
					</ul>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>文章标题：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="activityTitle" type="text" class="input-text" value=""
						placeholder="请输入文章标题">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>文章描述：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<script id="editor" type="text/plain"
						style="width:100%;height:400px;"></script>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>文章摘要：</label>
				<div class="formControls col-xs-12 col-sm-12">
				<textarea name="content" id="content"></textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					<!-- <button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					 --><button class="btn btn-primary radius ml-30" type="button" onclick="update()">
						<i class="Hui-iconfont">&#xe632;</i> 保存修改
					</button>

				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	
</body>
</html>
