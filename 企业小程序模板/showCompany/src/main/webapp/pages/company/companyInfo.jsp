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
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>企业信息</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 企业管理 <span class="c-gray en">&gt;</span>
		企业信息

		<!-- <div class="switch" data-animated="false"
			style="float: right; margin-right: 50px;">
			<input type="checkbox" checked />
		</div> -->
	</nav>
	<article class="page-container">
		<p class="col-xs-12 col-sm-12">填写企业信息，让客户快速找到您</p>
		<form class="form form-horizontal" id="form-article-add">
			<div class="row cl">
			<input type="hidden" id="companyId" value="${company.companyId }">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>海报图： <span class="stip">建议上传尺寸规格为750*430px的图片，仅只支持jpg、jpeg、png格式</span></label>
				<div class="formControls col-xs-12 col-sm-12">
					<ul class=" hb_t">
						<!--已经添加了图片的-->
						<s:iterator value="posters">
						<li class="appendImg">
							<img name="posterImg" src="${posterUrl}" class="tp">
							<p class="showp">
								<img src="img/pc_img/zjt.png" class="zjt"> <img
									src="img/pc_img/dele.png" class="dele"> <img
									src="img/pc_img/rjt.png" class="yjt">
							</p>
						</li>
						</s:iterator>


						<!--这个是还没有添加图片的-->
						<li id="addPoster">
							<img src="img/pc_img/tp.png" class="tp"> 
							<input id="posterUrl" name="image"  type="file" class="file" onchange="uploadPoster()">
						</li>
					</ul>

				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>企业LOGO： <span class="stip">建议上传尺寸规格为正方形的图片，仅只支持jpg、jpeg、png格式</span></label>
				<div class="formControls col-xs-12 col-sm-12">
					<ul class="tp_ul">
						<li class="appendImg">
							<s:if test="company.comLogo != null">
								<img id="companyLogoUrl"  src="${company.comLogo }" class="tp"> 
							</s:if>
							<s:else>
								<img id="companyLogoUrl"  src="img/pc_img/tp.png" class="tp"> 
							</s:else>
							<input id="uploadLogo"  name="image" type="file" class="file" onchange="uploadComLogo()">
						</li>
					</ul>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>企业名称：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<input id="comName" type="text" class="input-text" value="${company.comName }"
						placeholder="请输入企业名称">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>企业地址：</label>
				<div class="formControls col-xs-12 col-sm-12 mt-10">
					<input id="comAddr" type="text" class="input-text" value="${company.comAddr }"
						placeholder="请输入详细地址信息，如道路、门牌号、创业园等">
				</div>

			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>联系电话：</label>
				<s:if test="comPhones.size() == 0">
					<div class="formControls col-xs-12 col-sm-12  phone_li mt-10">
					<input name="phone" type="text" class="input-text"
						placeholder="请输入常用联系电话"> <img name="phoneimg"
						src="img/pc_img/ad-sx.png" class="add_phone">
				</div>
				</s:if>
				<s:iterator value="comPhones">
				<div class="formControls col-xs-12 col-sm-12  phone_li mt-10">
					<input name="phone" type="text" class="input-text" value="${comPhone }"
						placeholder="请输入常用联系电话"> <img name="phoneimg"
						src="img/pc_img/del_sx.png" class="del_phone">
				</div>
				</s:iterator>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>企业简介：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<textarea id="comProfile" name="abstract" class="textarea" maxlength="240"
						placeholder="说点什么..." datatype="*10-100">${company.comProfile }</textarea>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
					
					<button class="btn btn-primary radius ml-30" type="button" onclick="update()">
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
	$(function() {
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
		 $(".add_phone").click(function(ev) {
			$(this).parent(".phone_li").after('<div class="formControls col-xs-12 col-sm-12  phdele_li mt-10" ><input name="phone" type="text" class="input-text" value="" placeholder="请输入常用联系电话" ><img name="phoneimg" src="img/pc_img/del_sx.png" class="del_phone"  ></div>');
			var oEvent = ev || event;
			oEvent.cancelBubble = true;
            oEvent.stopPropagation();
		 })
		//删除联系电话

		$("div").delegate(".del_phone", "click", function() {
			$(this).parent(".phdele_li").remove();
		});
		
		//改联系电话的图标
		changeP();
		$('.phone_li').on('click', 'img.add_phone', function(event) {
			$(this).parent(".phone_li").next().after('<div class="formControls col-xs-12 col-sm-12  phdele_li mt-10" ><input name="phone" type="text" class="input-text" value="" placeholder="请输入常用联系电话" ><img name="phoneimg" src="img/pc_img/del_sx.png" class="del_phone"  ></div>');
		})
	});
	function changeP(){
		$("img[name='phoneimg']")[0].src = "img/pc_img/ad-sx.png";
		$("img[name='phoneimg']")[0].setAttribute("class","add_phone");
	}
	
	function uploadComLogo(){
		$.ajaxFileUpload({
			type : "post",
			url : "<%=basePath%>company/uploadComLogo.do",
			dataType : 'json',
			fileElementId : 'uploadLogo',
			success : function(data) {
				$("#companyLogoUrl").attr("src",data.picUrl);
			},
			error:function (){
				alert("图片上传失败");
			}
			
		});
	}
	function uploadPoster(){
		if($(".hb_t .appendImg").length >= 5){
			alert("图片数量已达上限");
			return ;
		}
		$.ajaxFileUpload({
			type : "post",
			url : "<%=basePath%>company/uploadComPoster.do",
			dataType : 'json',
			fileElementId : 'posterUrl',
			success : function(data) {
				if(data.code==0){
				var tmp = "";
				tmp = '<li class="appendImg">'
					  +'<img name="posterImg" src="'+data.picUrl+'" class="tp">'
					  + '<p class="showp"><img src="img/pc_img/zjt.png" class="zjt"> <img src="img/pc_img/dele.png" class="dele"> <img src="img/pc_img/rjt.png" class="yjt"> </p> </li>'
				$("#addPoster").before(tmp);
				}else if(data.code == -1){
					alert("图片数量已达上限");
				}else{
					alert("error");
				}
			},
			error:function (){
				alert("图片上传失败");
			}
			
		});
	}
	
	function resetData(){//重置数据
		
	}
	function update(){
		var posters = "";
		for(var i=0;i<$("img[name='posterImg']").length;i++){
			posters = posters + $("img[name='posterImg']").get(i).src + ",";
		}
		posters = posters.substring(0,posters.length-1);
		var phones = "";
		for(var i=0;i<$("input[name='phone']").length;i++){
			phones = phones + $("input[name='phone']").get(i).value+",";
		} 
		phones = phones.substring(0,phones.length-1);
		$.ajax({
			url:"<%=basePath%>company/updateComInfo.do",
			type:"post",
			dataType:"json",
			data:{
				"str":posters,
				"str2":phones,
				"company.companyId":$("#companyId").val(),
				"company.comLogo":$("#companyLogoUrl").attr("src"),
				"company.comName":$("#comName").val(),
				"company.comAddr":$("#comAddr").val(),
				"company.comProfile":$("#comProfile").val(),
				
			},
			success:function (data){
				alert("修改已生效");
			},
			error:function (){
				alert("保存失败");
			}
		}); 
	}
</script>
</body>
</html>
