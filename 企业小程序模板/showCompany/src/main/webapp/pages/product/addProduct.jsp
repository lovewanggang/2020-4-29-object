<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

<title>添加产品信息</title>
</head>
<script type="text/javascript">
var hotCount = 0;
$(function (){
	getData();

});

//获取分类信息
function getData(){
	$.ajax({
			url:"<%=basePath%>company/queryClassInfo.do",
			type:"post",
			dataType:"json",
			async:false,
			success:function(data){
				hotCount = data.count;
			for(var i=0;i<data.bigClasses.length;i++){
				$("#bigClass").append('<option value="'+data.bigClasses[i].productBigClassId+'">' + data.bigClasses[i].bigClassName + '</option>');
			}
			},
			error:function (){
				alert("获取分类数据失败");
			}
		});
}
	function uploadProductPic(){
		if($(".appendImg").length >= 5){
			alert("图片数量已达上限");
			return;
		}
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/uploadProductPic.do",
		dataType : 'json',
		fileElementId : 'uploadPic',
		success : function(data) {
		var tmp = "";
			tmp = '<li class="appendImg"><img src="'+data.picUrl+'" class="tp proImg">'
				  + '<p class="showp"><img src="img/pc_img/zjt.png" class="zjt"> <img src="img/pc_img/dele.png" class="dele"> <img src="img/pc_img/rjt.png" class="yjt"> </p></li>'			
									
			$("#uploadLi").before(tmp);
		},
		error:function (){
			alert("图片上传失败");
		}
		
	});
}
</script>

<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 产品管理 <span class="c-gray en">&gt;</span>
		添加产品信息

		<div class="switch" data-animated="false"
			style="float: right; margin-right: 50px;">
			<input type="checkbox" checked />
		</div>
	</nav>
	<article class="page-container">
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>上传产品图片： <span class="stip">建议上传正方形产品图，仅只支持jpg、jpeg、png格式</span></label>
				<div class="formControls col-xs-12 col-sm-12">
					<ul class=" hb_t">
						<!--已经添加了图片的-->
						<!-- <li class="appendImg"><img src="img/pc_img/dd.png"
							class="tp">
							<p class="showp">
								<img src="img/pc_img/zjt.png" class="zjt"> <img
									src="img/pc_img/dele.png" class="dele"> <img
									src="img/pc_img/rjt.png" class="yjt">
							</p></li> -->
						<!--这个是还没有添加图片的-->
						<li id="uploadLi"><img src="img/pc_img/tp.png" class="tp">
							<input id="uploadPic" name="image" onchange="uploadProductPic()"
							type="file" class="file"></li>
					</ul>

				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12">产品描述：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="productDetail" type="text" class="input-text" value=""
						placeholder="请输入产品描述">
				</div>
			</div>
			
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>产品类别：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<select id="bigClass" class="select_input"
						onchange="changelb(this)">
						<option selected="selected" disabled="disabled">请选择大类</option>
					</select> <select id="class" class="select_input">
						<option selected="selected" disabled="disabled">请选择小类</option>
					</select>
					<div class="row cl">
						<label class="form-label col-xs-12 col-sm-12">产品参数：</label>
						<div class="formControls col-xs-12 col-sm-12">
							<ul class="product_cs">
								<!-- <li><span>品牌</span><input type="text" class="input-text"
									placeholder="请认真填写参数信息，为空则不显示在前台"></li>
								<li><span>型号</span><input type="text" class="input-text"
									placeholder="请认真填写参数信息，为空则不显示在前台"></li>
								<li><span>颜色分类</span><input type="text" class="input-text"
									placeholder="请认真填写参数信息，为空则不显示在前台"></li>
								<li><span>材质</span><input type="text" class="input-text"
									placeholder="请认真填写参数信息，为空则不显示在前台"></li> -->
							</ul>

						</div>
					</div>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12">产品编号： <span
					class="stip">若不填写则自动生成编号</span></label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="pNum" type="text" class="input-text" value=""
						placeholder="请输入产品编号">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>发布状态：</label>
				<div class="formControls col-xs-12 col-sm-12 skin-minimal">
					<div class="radio-box">
						<input name="sex" type="radio" id="sex-1" value="1" checked> <label
							for="sex-1">立即发布</label>
					</div>
					<div class="radio-box">
						<input type="radio" id="sex-2" name="sex" value="0"> <label
							for="sex-2">待发布</label>
					</div>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label  col-xs-12 col-sm-12"><span
					class="c-red">*</span>添加分类：</label>
				<div class="formControls col-xs-12 col-sm-12 skin-minimal">
					<!-- <div class="check-box">
						<input type="checkbox" id="checkbox-1" checked="checked">
						<label for="checkbox-1">添加到新品分类</label>
					</div> -->
					<div class="check-box">
						<input type="checkbox" id="checkbox-2">
						<label for="checkbox-2">添加到人气分类</label>
					</div>

				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>图文详情：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<script id="editor" type="text/plain"
						style="width:100%;height:400px;"></script>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
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
	<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>

	<script type="text/javascript">
		
			var ue = UE.getEditor('editor');
			$(function() {
				$('.skin-minimal input').iCheck({
					checkboxClass: 'icheckbox-blue',
					radioClass: 'iradio-blue',
					increaseArea: '20%'
				});
				//添加图片模块hover后显示出删除模块
				$('.hb_t').on('mouseenter mouseleave', 'li.appendImg', function(event) {
						if(event.type == 'mouseenter') {
							console.log("aaa")
							$(this).children('.showp').stop().fadeIn()
						} else {
							console.log("bbb")
							$(this).children('.showp').stop().fadeOut()
						}
					})
		$('.hb_t').on('click', 'img.yjt', function(event) {
						if($(this).parents(".appendImg").next().hasClass("appendImg")) {
							$(this).parents(".appendImg").next().after($(this).parents(".appendImg"));
						}
					})
				$('.hb_t').on('click', 'img.zjt', function(event) {
						if($(this).parents(".appendImg").prev().hasClass("appendImg")) {
							$(this).parents(".appendImg").prev().before($(this).parents(".appendImg"));
						}
					})
					$('.hb_t').on('click', 'img.dele', function(event) {
						$(this).parents(".appendImg").remove();
					})
					//联系电话的添加
				$(".add_phone").click(function() {
						$(this).parent(".phone_li").after('<div class="formControls col-xs-12 col-sm-12  phdele_li mt-10" ><input type="text" class="input-text" value="" placeholder="请输入常用联系电话" ><img src="img/pc_img/del_sx.png" class="del_phone"  ></div>');
					})
					//删除联系电话

				$("div").delegate(".del_phone", "click", function() {
					$(this).parent(".phdele_li").remove();
				});

				//	分类选择框改变
				//$(".cate-change").change(function(){
				//	console.log("ds")
				//	$(this).siblings(".cl").children(".product_cs").html("")
				//})
			});
			//	分类选择框改变更换参数
			function changelb(obj) {
				$.ajax({
					url:"<%=basePath%>company/findOneBigClass.do",
					type:"post",
					dataType:"json",
					async:false,
					data:{
						"id":$("#bigClass").val()
					},
					success:function(data){
						var tmp = "";
						$("#class").html("");
						for(var i=0;i<data.bigClass.productclasses.length;i++){
							$("#class").append('<option value="'+data.bigClass.productclasses[i].productClassId+'">' + data.bigClass.productclasses[i].pcName + '</option>');
						}
						for(var i=0;i<data.bigClass.productParamer.length;i++){
							tmp = tmp + '<li><span>'+data.bigClass.productParamer[i].paramerName+'</span><input dataP="'+data.bigClass.productParamer[i].productId+'" type="text" class="input-text paramValue" placeholder="请认真填写参数信息，为空则不显示在前台"></li>'
						}
						$(obj).siblings(".cl").children(".formControls").children(".product_cs").html(tmp);
					},
					error:function (){
						alert("获取分类数据失败");
					}
				});
				
			}
			function add(){
				if($("#bigClass").val() == null || $("#class").val() == null){
					alert("请先选择产品的分类");
					return;
				}
				
				var pic1 = "";
				var pic2 = "";
				var pic3 = "";
				var pic4 = "";
				var pic5 = "";
				if($(".proImg")[0] != undefined){
					pic1 = $(".proImg")[0].getAttribute("src")
				}
				if($(".proImg")[1] != undefined){
					pic2 = $(".proImg")[1].getAttribute("src")
				}
				if($(".proImg")[2] != undefined){
					pic3 = $(".proImg")[2].getAttribute("src")
				}
				if($(".proImg")[3] != undefined){
					pic4 = $(".proImg")[3].getAttribute("src")
				}
				if($(".proImg")[4] != undefined){
					pic5 = $(".proImg")[4].getAttribute("src")
				}
				
				var param1 = "";
				for(var i=0;i<$(".paramValue").length;i++){
					param1 = param1 + $(".paramValue")[i].getAttribute("dataP") +",";
				}
				param1 = param1.substring(0,param1.length-1);
				var param2 = "";
				for(var i=0;i<$(".paramValue").length;i++){
					param2 = param2 + $(".paramValue")[i].value +",";
				}
				param2 = param2.substring(0,param2.length-1);
				
				if($("#checkbox-2").is(":checked")){
					if(hotCount == 3){
						alert("人气商品数量已达上限，请先到产品列表删除人气商品后再添加。");
						return ;
					}
					var hot = 1;
				}else{
					var hot = 0;
				}
					
				
				$.ajax({
					url:"<%=basePath%>company/addProduct.do",
					type:"post",
					dataType:"json",
					async:false,
					data:{
						"product.productPic1":pic1,
						"product.productPic2":pic2,
						"product.productPic3":pic3,
						"product.productPic4":pic4,
						"product.productPic5":pic5,
						"product.productDetail":$("#productDetail").val(),
						"id":$("#bigClass").val(),
						"id2":$("#class").val(),
						"str":param1,
						"str2":param2,
						"product.productNum":$("#pNum").val(),
						"product.hot":hot,
						"product.showProduct":ue.getContent(),
						"product.status":$('input:radio[name="sex"]:checked').val()
					},
					success:function(data){
						alert("添加成功");
						var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
						parent.layer.close(index);  // 关闭layer
					},
					error:function (){
							alert("添加失败");
						}
					});
			}
		</script>
</body>

</html>
