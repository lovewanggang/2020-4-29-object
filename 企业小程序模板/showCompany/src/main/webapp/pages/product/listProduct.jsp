<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<base href="<%=basePath%>">
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

<!--/meta 作为公共模版分离出去-->

<title>产品列表</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 产品 <span class="c-gray en">&gt;</span>
		产品列表
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
		<p class="blue">
			好好的排兵布阵，专业化管理
			<!-- 筛选 -->
		<%-- <div class="text-l pad10 bgw mb10 seach_liinput"
			style="margin-top: 25px;">
			产品类别： <select class="input-text"
				style="width:160px;margin-right: 10px;" placeholder="" id="" name="">
				<option>鞋子</option>
				<option>上衣</option>
				<option>高帮鞋</option>
				<option>护肤</option>
			</select> 产品状态： <select class="input-text"
				style="width:160px;margin-right: 10px;" placeholder="" id="" name="">
				<option>已上架</option>
				<option>已下架</option>

			</select>
			<button type="submit" class="btn btn-cancle ok_btn" id="" name="">
				清空</button>
			<button type="submit" class="btn btn-success ok_btn" id="" name="">
				搜索</button>

		</div> --%>
		<!-- 筛选END -->
		<div class="cl pd-5 bg-1 bk-gray mt-20">
				<a href="javascript:;" onclick="product_add('添加产品信息','<%=basePath %>pages/product/addProduct.jsp')"" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加产品</a>
				<a href="javascript:;" onclick="dels()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
				批量删除</a> <a href="javascript:;" onclick="shangjia()"
				class="btn btn-warning   radius"><i class="Hui-iconfont">&#xe679;</i>
				上架</a> <a href="javascript:;" onclick="xiajia()" class="btn btn-default  radius"><i
				class="Hui-iconfont">&#xe674;</i> 下架</a>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="50"><input type="checkbox" name="" value=""></th>
						<th width="">图片</th>
						<th width="150">编号</th>
						<th width="">产品描述</th>
						<th width="120">分类</th>
						<th width="180">创建日期</th>
						<th width="100">状态</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="products">
					<tr class="text-c">
						<td><input type="checkbox" value="${productId }" name="check"></td>
						<td><img src="${productPic1 }" class="rq_product"></td>
						<s:if test="hot == 1">
							<td>${productNum }【人气】</td>
						</s:if>
						<s:else>
							<td>${productNum }</td>
						</s:else>
						<td>${productDetail }</td>
						<td>${productClass.pcName }</td>
						<td><s:date format="yyyy-MM-dd hh:mm:ss" name="createTime"/></td>
						<s:if test="status == 1">
							<td>已发布</td>
						</s:if>
						<s:else>
							<td>待发布</td>
						</s:else>
						<td class="td-manage"><a style="text-decoration:none"
							onclick="edit_Product('修改产品信息','<%=basePath %>pages/product/editProduct.jsp?id=${productId }')" href="javascript:;" title="编辑"><i
								class="Hui-iconfont">&#xe60c;</i></a> <a title="删除"
							href="javascript:;"
							onclick="delProduct(${productId })"
							class="ml-5" style="text-decoration:none"><i
								class="Hui-iconfont">&#xe609;</i></a></td>
					</tr>
				</s:iterator>
				</tbody>
			</table>
		</div>
	</article>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer /作为公共模版分离出去-->
	<script type="text/javascript"
		src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>


	<!--省市区插件-->
	<script src="static/city/distpicker.data.js"></script>
	<script src="static/city/distpicker.js"></script>
	<script src="static/city/main.js"></script>

	<script type="text/javascript">
	function dels(){
		var obj=document.getElementsByName('check'); 
		var s=''; 
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked){
				s = s + obj[i].value+',';
			}  
		} 
		s=s.substring(0,s.length-1);
		
		$.ajax({
			url:"<%=basePath%>company/delPros.do",
			type:"post",
			dataType:"json",
			data:{
				"str":s
			},
			success:function(data){
				alert("批量删除成功");
				window.location.reload();
			},
			error:function (){
				alert("批量删除失败");
			}
		});
	}
	function onoff(){
		$.ajax({
			url:"<%=basePath%>company/productOnoff.do",
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
	function shangjia(){
		var obj=document.getElementsByName('check'); 
		var s=''; 
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked){
				s = s + obj[i].value+',';
			}  
		} 
		s=s.substring(0,s.length-1);
		
		$.ajax({
			url:"<%=basePath%>company/shangjia.do",
			type:"post",
			dataType:"json",
			data:{
				"str":s
			},
			success:function(data){
				alert("上架成功");
				window.location.reload();
			},
			error:function (){
				alert("上架失败");
			}
		});
		
	}
	function xiajia(){
		var obj=document.getElementsByName('check'); 
		var s=''; 
		for(var i=0; i<obj.length; i++){ 
			if(obj[i].checked){
				s = s + obj[i].value+',';
			}  
		} 
		s=s.substring(0,s.length-1);
		
		$.ajax({
			url:"<%=basePath%>company/xiajia.do",
			type:"post",
			dataType:"json",
			data:{
				"str":s
			},
			success:function(data){
				alert("下架成功");
				window.location.reload();
			},
			error:function (){
				alert("下架失败");
			}
		});
		
	}
	/*添加公司信息*/
	function product_add(title,url){
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
	/*修改产品信息*/
	function edit_Product(title,url){
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
	function delProduct(id){
		$.ajax({
			url:"<%=basePath%>company/delProduct.do",
			type:"post",
			dataType:"json",
			data:{
				"id":id
			},
			success:function(data){
				alert("删除成功");
				window.location.reload();
			},
			error:function (){
				alert("删除失败");
			}
		});
	}
$(function(){
	
	$(function(){
	$('.table-sort').dataTable({
		"aaSorting": [[ 1, "asc" ]],//默认第几个排序
		"bStateSave": true,//状态保存
		"aoColumnDefs": [
		  {"orderable":false,"aTargets":[0,1,2,3,4,5,6,7]}// 制定列不参与排序
		]
	});
	
});

});
</script>
</body>
</html>
