<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>

<base href="<%=basePath%>">
<head>
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
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" href="static/tag_editor/jquery.tag-editor.css">

<title>添加分类</title>
</head>

<body>
	<article class="page-container">
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12">类别名称：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="bigClassName" type="text" class="input-text" value="" maxlength="8"
						placeholder="请输入类别名称，不超过8个字符">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>产品属性设置：<span class="stip">输入后按回车键或点击其他位置，可输入多个，但每个属性名称不能多于4个字</span></label>
				<div class="formControls col-xs-12 col-sm-12">
					<div style="margin:0 0 1.2em">
						<div style="margin:0 0 1.2em">
							<textarea id="demo6" placeholder="输入后按空格键，可输入多个"></textarea>
						</div>
					</div>
				</div>
			</div>
			<div class="row cl">
				<div class="formControls col-xs-12 col-sm-12 skin-minimal">
					<div class="check-box">
						<input type="checkbox" id="checkbox-2"> <label
							for="checkbox-2">是否默认隐藏(若勾选则不显示在小程序)</label>
					</div>
				</div>
			</div>
			<div class="row cl">
				<div class="formControls col-xs-12 col-sm-12 skin-minimal">
					<div class="check-box">
						<input type="checkbox" id="checkbox-1" checked="checked">
						<label for="checkbox-1">是否有默认子分类</label>
					</div>
				</div>
			</div>
			
			<div class="row cl  haszfl">
				<label class="form-label col-xs-12 col-sm-12">子分类名称：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="className" type="text" class="input-text" value=""
						placeholder="建议不超过8个字">
				</div>
			</div>
			
			<div class="row cl  haszfl" >
				<label class="form-label col-xs-12 col-sm-12 "><span
					class="c-red">*</span>子分类介绍图/广告图： <span class="stip">建议上传尺寸规格为长方形的图片，仅只支持jpg、jpeg、png格式</span></label>
				<div class="formControls col-xs-12 col-sm-12 ">
					<ul class="ggt_ul">
						<li class="appendImg"><img id="pclassPic" src="img/pc_img/tp.png"
							class="tp"> <input id="uploadPic" name="image" onchange="uploadProductClassPic()" type="file" class="file"></li>
					</ul>
				</div>
			</div>
			

			<div class="row cl  ">
				<div class="col-xs-12 col-sm-12  mt-50">
					<!-- <button class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
					 -->
					 <button class="btn btn-primary radius ml-30" type="button" onclick="save()">
						<i class="Hui-iconfont">&#xe632;</i> 保存修改
					</button>

				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script src="js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--多值输入插件-->
	<script src="http://www.jq22.com/jquery/jquery-ui-1.10.2.js"></script>
	<script src="static/tag_editor/jquery.tag-editor.js"></script>
	<script>
	
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
	function save(){
		if(!($("#bigClassName").val())){
			alert("类别名称不能为空");
			return;
		}
		if($("#checkbox-1").is(":checked")){
			if(!($("#className").val())){
				alert("请输入子分类名称");
				return;
			}
		}
		
		var param = "";
		for(var i=0;i<$(".tag-editor-tag").length;i++){
			param = param + $(".tag-editor-tag")[i].innerHTML+",";
		}
		param = param.substring(0,param.length-1);
		var status = 0;
		if($("#checkbox-2").is(":checked")){
			status = 1;
		}
		
		$.ajax({
			url:"<%=basePath%>company/addBigClass.do",
			type:"post",
			dataType:"json",
			data:{
				"bigClass.bigClassName":$("#bigClassName").val(),
				"bigClass.status":status,
				"str":param,
				"productClass.pcName":$("#className").val(),
				"productClass.pcClassPic":$("#pclassPic").attr("src")
			},
			success:function(data){
				alert("添加成功");
				var index = parent.layer.getFrameIndex(window.name);
				parent.layer.close(index);
			},
			error:function (){
				alert("获取数据失败");
			}
		});
	}
		$(function() {
			$('.skin-minimal input').iCheck({
				checkboxClass : 'icheckbox-blue',
				radioClass : 'iradio-blue',
				increaseArea : '20%'
			});
	
			function tag_classes(field, editor, tags) {
				$('li', editor).each(function() {});
	
			}
	
			$('#demo6').tagEditor({
				initialTags : [], //输入框的值
				onChange : tag_classes
			});
	
			tag_classes(null, $('#demo6').tagEditor('getTags')[0].editor); // or editor == $('#demo6').next()
			//判断有支分类
			$('#checkbox-1').on('ifChecked', function(event) {
				$(".haszfl").show();
				console.log("啊不是都")
			});
			//判断无支分类
			$('#checkbox-1').on('ifUnchecked', function(event) {
				console.log("没有选中啊");
				$(".haszfl").hide();
			});
		});
	</script>

</body>

</html>
