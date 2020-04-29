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

<title>我们提供</title>
</head>
<body>
	<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 企业管理 <span class="c-gray en">&gt;</span> 我们提供
	
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
	<p class="blue">简单扼要的描述企业主要业务类别，让客户快速了解贵公司</p>
	<form class="form form-horizontal" id="form-article-add">
		<div class="row cl">
			<label class="form-label col-xs-12 col-sm-12 "><span class="c-red">*</span>我们提供橱窗展示列表： <span class="stip">(仅三个橱窗窗口)</span></label>
			<div class="formControls col-xs-12 col-sm-12 ">
				<ul class=" hb_t">
					<!--已经添加了图片的-->
					<s:if test="business.showPic1 != null">
					<li class="appendImg">
						<img name="showPic" src="${business.showPic1 }" class="tp">
						<p class="showp">
							<img src="img/pc_img/zjt.png" class="zjt">
							<img src="img/pc_img/dele.png" class="dele">
							<img src="img/pc_img/rjt.png" class="yjt">
						</p>
					</li>
					</s:if>
					<s:if test="business.showPic2 != null">
					<li class="appendImg">
						<img name="showPic" src="${business.showPic2 }" class="tp">
						<p class="showp">
							<img src="img/pc_img/zjt.png" class="zjt">
							<img src="img/pc_img/dele.png" class="dele">
							<img src="img/pc_img/rjt.png" class="yjt">
						</p>
					</li>
					</s:if>
					<s:if test="business.showPic3 != null">
					<li class="appendImg">
						<img name="showPic" src="${business.showPic3 }" class="tp">
						<p class="showp">
							<img src="img/pc_img/zjt.png" class="zjt">
							<img src="img/pc_img/dele.png" class="dele">
							<img src="img/pc_img/rjt.png" class="yjt">
						</p>
					</li>
					</s:if>
					<!--这个是还没有添加图片的，超过三个橱窗就消失-->
					<li id="uploaShowPic" >
						<img src="img/pc_img/tp.png" class="tp">
						<input id="uploadPic" name="image" type="file" class="file" onchange="uploadShowPic(this)">
					</li>
				</ul>
				
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-12 col-sm-12 ">业务类别： <span class="stip">(最多输入4个字,最多4个业务类别)</span></label>
			<div class="formControls col-xs-12 col-sm-12  phone_li" >
				<s:if test="business.business1 != null">
					<div class="add_newa">
						<input name="business" type="text" maxlength="4"  class="input-texta" value="${business.business1 }" placeholder="业务类别"  >
						<img src="img/pc_img/ad-sx.png" class="add_phone" onclick="addLB(this)">
					</div>
				</s:if>
				<s:else>
					<div class="add_newa">
						<input name="business" type="text" maxlength="4"  class="input-texta" placeholder="业务类别"  >
						<img src="img/pc_img/ad-sx.png" class="add_phone" onclick="addLB(this)">
					</div>
				</s:else>
				<s:if test="business.business2 != null">
				<div class="add_newa">
					<input name="business" type="text" maxlength="4"  class="input-texta" value="${business.business2 }" placeholder="业务类别"  >
					<img src="img/pc_img/del_sx.png" class="del_phone">
				</div>
				</s:if>
				<s:if test="business.business3 != null">
				<div class="add_newa">
					<input name="business" type="text" maxlength="4"  class="input-texta" value="${business.business3 }" placeholder="业务类别"  >
					<img src="img/pc_img/del_sx.png" class="del_phone">
				</div>
				</s:if>
				<s:if test="business.business4 != null">
				<div class="add_newa">
					<input name="business" type="text" maxlength="4"  class="input-texta" value="${business.business4 }" placeholder="业务类别"  >
					<img src="img/pc_img/del_sx.png" class="del_phone">
				</div>
				</s:if>
				
			</div>
		</div>
		
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<!-- <button  class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button> -->
				<button  class="btn btn-primary radius ml-30" type="button" onclick="saveOrupdate(${business.companyBusinessId })"><i class="Hui-iconfont">&#xe632;</i> 保存修改</button>
				
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->



<!--省市区插件-->
<script src="static/city/distpicker.data.js"></script>
  <script src="static/city/distpicker.js"></script>
  <script src="static/city/main.js"></script>




<script type="text/javascript">
function onoff(){
	$.ajax({
		url:"<%=basePath%>company/businessOnOff.do",
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
$(function(){
	//添加图片模块hover后显示出删除模块
	$(".appendImg ").hover(function(){
		console.log("111")
		$(this).children('.showp').stop().fadeToggle()
	})
	//点击垃圾桶删除li
	$(".hb_t .dele").click(function(){
		$(this).parents(".appendImg").remove();
	})
	//右箭头更换位置
	$(".hb_t .yjt").click(function(){
		if($(this).parents(".appendImg").next().hasClass("appendImg")){
			 $(this).parents(".appendImg").next().after($(this).parents(".appendImg"));
		}
	})
	//左箭头更换位置
	$(".hb_t .zjt").click(function(){
		console.log("aaa")
		if($(this).parents(".appendImg").prev().hasClass("appendImg")){
			 $(this).parents(".appendImg").prev().before($(this).parents(".appendImg"));
		}
	})
	//删除类别
	
	$("div").delegate(".del_phone","click",function(){
	  $(this).parent(".add_newa").remove();
	});
});
function addLB(obj){
	if($("input[name='business']").length >= 4){
		alert("业务类别最多只能有四个");
		return;
	}
	$(obj).parents(".phone_li").append('<div class="add_newa"><input name="business" type="text" class="input-texta" value="" placeholder="业务类别"  ><img src="img/pc_img/del_sx.png" class="del_phone"></div>');

}

function uploadShowPic(that){
	//检测目前有几张图
	if($("img[name='showPic']").length >= 3){
		alert("展示图片已达上限");
		return;
	}
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/uploadBusinessPic.do",
		dataType : 'json',
		fileElementId : 'uploadPic',
		success : function(data) {
			var tmp = "";
			tmp = '<li class="appendImg">'
				  +'<img name="showPic" src="'+data.picUrl+'" class="tp">'
				  +'<p class="showp"><img src="img/pc_img/zjt.png" class="zjt"> <img src="img/pc_img/dele.png" class="dele"> <img src="img/pc_img/rjt.png" class="yjt"> </p></li>'
			$("#uploaShowPic").before(tmp);
		},
		error:function (){
			alert("图片上传失败");
		}
		
	});
	
}

function saveOrupdate(id){
	var business1;
	var business2;
	var business3;
	var business4;
	var showPic1;
	var showPic2;
	var showPic3;
	if($("input[name='business']")[0] != undefined && $("input[name='business']")[0] != null){
		business1 = $("input[name='business']")[0].value;
	}
	if($("input[name='business']")[1] != undefined && $("input[name='business']")[1] != null){
		business2 = $("input[name='business']")[1].value;
	}
	if($("input[name='business']")[2] != undefined && $("input[name='business']")[2] != null){
		business3 = $("input[name='business']")[2].value;
	}
	if($("input[name='business']")[3] != undefined && $("input[name='business']")[3] != null){
		business4 = $("input[name='business']")[3].value;
	}
	if($("img[name='showPic']")[0] != undefined && $("img[name='showPic']")[0] != null){
		var showPic1 = $("img[name='showPic']")[0].getAttribute("src");
	}
	if($("img[name='showPic']")[1] != undefined && $("img[name='showPic']")[1] != null){
		var showPic2 = $("img[name='showPic']")[1].getAttribute("src");
	}
	if($("img[name='showPic']")[2] != undefined && $("img[name='showPic']")[2].getAttribute("src") != null){
		var showPic3 = $("img[name='showPic']")[2].getAttribute("src");
	}
	
	$.ajax({
		url:"<%=basePath%>company/saveOrUpdateBusiness.do",
		type:"post",
		dataType:"json",
		data:{
			"business.companyBusinessId":id,
			"business.business1":business1,
			"business.business2":business2,
			"business.business3":business3,
			"business.business4":business4,
			"business.showPic1":showPic1,
			"business.showPic2":showPic2,
			"business.showPic3":showPic3
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

</script>
</body>
</html>