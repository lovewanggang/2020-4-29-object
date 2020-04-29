<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<!--[if lt IE 9]>
		<script type="text/javascript" src="lib/html5shiv.js"></script>
		<script type="text/javascript" src="lib/respond.min.js"></script>
		<![endif]-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<title>类别管理</title>
</head>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 企业管理 <span class="c-gray en">&gt;</span>
		类别管理
		<!-- <div class="switch" data-animated="false"
			style="float: right; margin-right: 50px;">
			<input type="checkbox" checked />
		</div> -->
	</nav>
	<div class="page-container">
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="r"> <a href="javascript:;" title="添加类别"
				onclick="add_cate('添加分类','<%=basePath %>pages/product/addBigClass.jsp')"
				class="btn btn-success radius"><i class="Hui-iconfont">&#xe600;</i>
					添加类别</a>
		</div>
		<table class="table table-border table-bordered table-bg">
			<thead>

				<tr class="text-l">
					<th>类别名称</th>
					<th>参数</th>
					<th width="140" class="text-c">操作</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="bigClasses">
				<tr class="text-l">
					<td>${bigClassName }</td>
					<td><s:iterator value="productParamer">${paramerName }、</s:iterator></td>
					<td><a title="查看子分类" href="javascript:;"
						onclick="look_Subclass(this)" data-id="true" class="ml-5"
						style="text-decoration:none"><i class="Hui-iconfont">&#xe6d5;</i></a>
						<a title="添加支分类" href="javascript:;"
						onclick="Add_branch('添加子分类','<%=basePath %>pages/product/addProClass.jsp?id=${productBigClassId }','500','400')"
						class="ml-5" style="text-decoration:none"><i
							class="Hui-iconfont">&#xe600;</i></a> <a title="编辑"
						href="javascript:;" onclick="edit_cate('修改分类','<%=basePath%>pages/product/editBigClass.jsp?id=${productBigClassId }')"
						class="ml-5" style="text-decoration:none"><i
							class="Hui-iconfont">&#xe6df;</i></a> 
							<s:if test="status == 1">
								<a title="已显示" href="javascript:;" onclick="hide_cate(this,${productBigClassId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe69d;</i></a>
							</s:if>
							<s:else>
								<a title="已隐藏" href="javascript:;" onclick="show_cate(this,${productBigClassId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe69e;</i></a>
							</s:else>
						
						<a title="删除" href="javascript:;" onclick="del_cate(this,${productBigClassId})"
						class="ml-5" style="text-decoration:none"><i
							class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
				<tr class="text-l Subclass">
					<td colspan="4">
						<table class="zl_table">
							<s:iterator value="productclasses">
							<tr>
								<td width="200">${pcName }</td>
								<td><img src="${pcClassPic }"
									style="height: 50px; width: auto;"></td>
								<td width="140" class="text-c"><a title="编辑"
									href="javascript:;"
									onclick="Add_branch('修改支分类','<%=basePath%>pages/product/editProClass.jsp?id=${productClassId }','500','400')"
									class="ml-5" style="text-decoration:none"><i
										class="Hui-iconfont">&#xe6df;</i></a> 
							<s:if test="status == 1">
								<a title="已显示" href="javascript:;" onclick="hide_cate2(this,${productClassId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe69d;</i></a>
							</s:if>
							<s:else>
								<a title="已隐藏" href="javascript:;" onclick="show_cate2(this,${productClassId})" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe69e;</i></a>
							</s:else>
									<a title="删除" href="javascript:;" onclick="del_cate2(this,${productClassId})"
									class="ml-5" style="text-decoration:none"><i
										class="Hui-iconfont">&#xe6e2;</i></a></td>
							</tr>
							</s:iterator>
							
						</table>
					</td>
				</tr>
				</s:iterator>
				<%-- <tr class="text-l">
					<td>时尚大咖</td>
					<td>品牌、型号、颜色分类、材质、风格、货号、尺寸、图案</td>
					<th>3</th>
					<td><a title="查看子分类" href="javascript:;"
						onclick="look_Subclass(this)" data-id="true" class="ml-5"
						style="text-decoration:none"><i class="Hui-iconfont">&#xe6d5;</i></a>
						<a title="添加支分类" href="javascript:;"
						onclick="Add_branch('添加支分类','添加支分类.html','500','400')"
						class="ml-5" style="text-decoration:none"><i
							class="Hui-iconfont">&#xe600;</i></a> <a title="编辑"
						href="javascript:;" onclick="edit_cate('修改分类','添加分类.html')"
						class="ml-5" style="text-decoration:none"><i
							class="Hui-iconfont">&#xe6df;</i></a> <a title="已显示"
						href="javascript:;" onclick="hide_cate(this)" class="ml-5"
						style="text-decoration:none"><i class="Hui-iconfont">&#xe69d;</i></a>
						<a title="删除" href="javascript:;" onclick="del_cate(this)"
						class="ml-5" style="text-decoration:none"><i
							class="Hui-iconfont">&#xe6e2;</i></a></td>
				</tr>
				<tr class="text-l Subclass">
					<td colspan="4"><span class="span_silver">暂无支分类</span></td>
				</tr> --%>
			</tbody>
		</table>
	</div>

	
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
</body>
<script>

function delBigClass(id){
	$.ajax({
			url:"<%=basePath%>company/delBigClass.do",
			type:"post",
			dataType:"json",
			data:{
				"id":id
			},
			success:function(data){
				console.log("delBigClass success");
			},
			error:function (){
				console.log("delBigClass error");
				
			}
		});
}
function delClass(id){
	$.ajax({
			url:"<%=basePath%>company/delClass.do",
			type:"post",
			dataType:"json",
			data:{
				"id":id
			},
			success:function(data){
				console.log("delClass success");
			},
			error:function (){
				console.log("delClass error");
				
			}
		});
}
function updateClassStatus(id,status){
	$.ajax({
			url:"<%=basePath%>company/updateClassStatus.do",
			type:"post",
			dataType:"json",
			data:{
				"id":id,
				"status":status
			},
			success:function(data){
				console.log("updateClassStatus success");
			},
			error:function (){
				console.log("updateClassStatus error");
				
			}
		});

}

function updateBigClassStatus(id,status){
	$.ajax({
			url:"<%=basePath%>company/updateBigClassStatus.do",
			type:"post",
			dataType:"json",
			data:{
				"id":id,
				"status":status
			},
			success:function(data){
				console.log("updateBigClassStatus success");
			},
			error:function (){
				console.log("updateBigClassStatus error");
				
			}
		});

}

/*添加支分类*/
function Add_branch(title,url,w,h){
	layer_show(title,url,w,h);
}
/*添加分类*/
function add_cate(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url,
		end: function () {
	        location.reload();
	      }
	});
	layer.full(index);
}
	//	修改类别
	function edit_cate(title,url){
		var index = layer.open({
			type: 2,
			title: title,
			content: url
		});
		layer.full(index);
	}
	
	//删除类别
	function del_cate(obj,id){
		//询问框
		layer.confirm('确定删除此大类吗？（大类下的所有信息都会一并删除哦）', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			delBigClass(id);
		  layer.msg('删除成功', {
		    time: 1000, //20s后自动关闭
		  });
		  $(obj).parents(".text-l").remove();
		}, function(){
		 //取消删除的操作
		});
	}
	function del_cate2(obj,id){
		//询问框
		layer.confirm('确定删除此子类吗？（子类下的所有信息都会一并删除哦）', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			delClass(id);
		  layer.msg('删除成功', {
		    time: 1000, //20s后自动关闭
		  });
		  $(obj).parents(".text-l").remove();
		}, function(){
		 //取消删除的操作
		});
	}
	
	
	//显示类别
	function show_cate(obj,id){
		//询问框
		layer.confirm('确定显示此类别吗？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			updateBigClassStatus(id,1);
		  layer.msg('该类别将在小程序上显示');
		  $(obj).after('<a title="已显示" href="javascript:;" onclick="hide_cate(this,'+id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe69d;</i></a> ')
		 $(obj).remove();
		}, function(){
			layer.msg('该类别将不显示在小程序');
		});
	}
	//隐藏类别
	function hide_cate(obj,id){
		//询问框
		layer.confirm('确定隐藏此类别吗？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			updateBigClassStatus(id,0);
		  layer.msg('该类别将不显示在小程序');
		   $(obj).after('<a title="已隐藏" href="javascript:;" onclick="show_cate(this,'+id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe69e;</i></a> ')
			 $(obj).remove();
		}, function(){
			layer.msg('该类别将在小程序上显示');
		});
	}
	//显示类别
	function show_cate2(obj,id){
		//询问框
		layer.confirm('确定显示此类别吗？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			updateClassStatus(id,1);
		  layer.msg('该类别将在小程序上显示');
		  $(obj).after('<a title="已显示" href="javascript:;" onclick="hide_cate2(this,'+id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe69d;</i></a> ')
		 $(obj).remove();
		}, function(){
			layer.msg('该类别将不显示在小程序');
		});
	}
	//隐藏类别
	function hide_cate2(obj,id){
		//询问框
		layer.confirm('确定隐藏此类别吗？', {
		  btn: ['确定','取消'] //按钮
		}, function(){
			updateClassStatus(id,0);
		  layer.msg('该类别将不显示在小程序');
		   $(obj).after('<a title="已隐藏" href="javascript:;" onclick="show_cate2(this,'+id+')" class="ml-5" style="text-decoration:none"><i class="Hui-iconfont">&#xe69e;</i></a> ')
			 $(obj).remove();
		}, function(){
			layer.msg('该类别将在小程序上显示');
		});
	}
	function look_Subclass(obj){
		console.log($(obj).data("id"));
		if($(obj).data("id")){
			//console.log($(obj).parents(".text-l").next(".Subclass").show());
			$(obj).parents(".text-l").next(".Subclass").show()
			$(obj).children(".Hui-iconfont").html("&#xe6d6");
			$(obj).data("id",false);
		}else{
			$(obj).children(".Hui-iconfont").html("&#xe6d5");
			$(obj).parents(".text-l").next(".Subclass").hide()
			$(obj).next(".Subclass").hide();
			$(obj).data("id",true);
		}
	}
</script>
</html>
