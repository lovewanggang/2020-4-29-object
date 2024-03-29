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
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<!--滑动的插件样式-->




<link rel="stylesheet" href="lib/range/css/ion.rangeSlider.css" />

<link rel="stylesheet"
	href="lib/range/css/ion.rangeSlider.skinFlat.css" id="styleSrc" />
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>


<title>加入我们</title>
</head>
<body>
	<article class="page-container">
		<p class="blue">招兵买马，增加团队实力！</p>
		<form class="form form-horizontal" id="form-article-add">

			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>职位名称：</label>
				<div class="formControls col-xs-12 col-sm-12 ">
					<input id="cjiName" type="text" class="input-text" value=""
						placeholder="请输入职位名称，如业务经理等">
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>工作地址：</label>
				<!-- <div class="formControls col-xs-12 col-sm-12">
					<div id="distpicker5">
						<div class="form-group">
							<select class="form-control input-text" id="province10"></select>
						</div>
						<div class="form-group">
							<select class="form-control input-text" id="city10"></select>
						</div>
						<div class="form-group">
							<select class="form-control input-text" id="district10"></select>
						</div>
					</div>
				</div> -->
				<div class="formControls col-xs-12 col-sm-12 mt-10">
					<input id="cjiAddr" type="text" class="input-text" value=""
						placeholder="请输入详细地址信息，如道路、门牌号、创业园等">
				</div>

			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>薪资范围：</label>
				<div class="formControls col-xs-12 col-sm-12  phone_li">
					<div
						style="position:relative; padding:20px 0px;margin:0 auto;font-size:12px;">
						<input type="text" id="range_1" />
					</div>
				</div>
			</div>

			<!-- <div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>发布时长：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<ul class="time_li">
						<li><span class="ss">30天</span></li>
						<li><span class="">不限</span></li>
						<li><span class=" nn">自定义</span>
							<div class="inpu_zdy">
								<input type="number" style="width: 180px;"
									placeholder="请输入小于等于30的数字">天
							</div></li>
					</ul>

				</div>
			</div> -->
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>工作年限：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<select id="cjiExp" class="select_input">
						<option>无</option>
						<option>1-3年</option>
						<option>3-5年</option>
						<option>5-10年</option> 
						<option>10年以上</option> 
						
						</option>
					</select>
				</div>
			</div>
			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>学历要求：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<select id="cjiEdu" class="select_input">
						<option>无</option>
						<option>高中</option>
						<option>大专</option>
						<option>本科</option> 
						<option>硕士</option> 
						<option>博士</option> 
					</select> 及以上
				</div>
			</div>

			<div class="row cl">
				<label class="form-label col-xs-12 col-sm-12"><span
					class="c-red">*</span>职位描述：</label>
				<div class="formControls col-xs-12 col-sm-12">
					<script id="editor" type="text/plain"
						style="width:100%;height:400px;"></script>
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-12 col-sm-12  text-c mt-40">
					<!-- <button onClick="removeIframe();" class="btn btn-default radius"
						type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button> -->
					<button onClick="add()"
						class="btn btn-primary radius ml-30" type="button">
						<i class="Hui-iconfont">&#xe632;</i> 保存
					</button>

				</div>
			</div>
		</form>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer /作为公共模版分离出去-->
	
	<script type="text/javascript" src="lib/range/js/ion.rangeSlider.js"></script>


	<!--省市区插件-->
	<script src="static/city/distpicker.data.js"></script>
	<script src="static/city/distpicker.js"></script>
	<script src="static/city/main.js"></script>




	<script type="text/javascript">
	var ue = UE.getEditor('editor');
		$(function() {
			//选择发布时间
			$(".time_li li").click(function() {
				console.log('33')
				if ($(this).children("span").hasClass("nn")) { //如果是点击自定义
					$(".time_li li span").removeClass("ss");
					$(this).children("span").addClass("ss");
					$(this).children(".inpu_zdy").show();
				} else {
					console.log('111')
					$(".time_li li span").removeClass("ss");
					$(this).children("span").addClass("ss");
					$('.inpu_zdy').hide();
				}
			})
		});
		function add(){
			if($("#cjiName").val() <= 0){
				alert("职位名称不能为空");
				return ;
			}else if($("#cjiAddr").val() <= 0){
				alert("工作地址不能为空");
				return ;
			}
			
			$.ajax({
				url:"<%=basePath%>company/addJob.do",
				type:"post",
				dataType:"json",
				data:{
					"job.cjiName":$("#cjiName").val(),
					"job.cjiAddr":$("#cjiAddr").val(),
					"job.cjiSalary":$("#range_1").val().replace(';','-'),
					"job.cjiEdu":$("#cjiEdu").val(),
					"job.cjiExp":$("#cjiExp").val(),
					"job.cjiDetail":ue.getContent()
				},
				success:function(data){
					alert("添加成功");
					var index = parent.layer.getFrameIndex(window.name);
					parent.layer.close(index);
				},
				error:function (){
					alert("添加失败");
				}
			});
			
		}
	</script>

	<script type="text/javascript">
	
		$(document).ready(function() {
	
	
			setTimeout(function (){
				$("#range_1").ionRangeSlider({
					min : 0,
		
					max : 20000,
		
					from : 0,
		
					to : 2000,
		
					type : 'double', //设置类型
		
					step : 500,
		
					prefix : "", //设置数值前缀
		
					postfix : "元", //设置数值后缀
		
					prettify : true,
		
					hasGrid : true
				});
			},100);
			
	
		});
	</script>

</body>
</html>
