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
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->

<title>人气产品</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 企业管理 <span class="c-gray en">&gt;</span>
		人气产品
		<div class="switch" data-animated="false"
			style="float: right; margin-right: 50px;">
			<input type="checkbox" checked />
		</div>
	</nav>
	<article class="page-container">
		<p class="blue">
			人气产品橱窗展示列表： <span class="stip">仅4个橱窗窗口</span>
		</p>

		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="l"><a href="javascript:;" onclick="datadel()"
				class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i>
					批量删除</a> <a href="javascript:;"
				onclick="prod_add('添加人气产品','产品添加.html')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加人气产品</a></span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg ">
				<thead>
					<tr class="text-c">
						<th width="50"><input type="checkbox" name="" value=""></th>
						<th width="">图片</th>
						<th width="100">编号</th>
						<th width="">产品标题</th>
						<th width="120">分类</th>
						<th width="180">创建日期</th>
						<th width="100">状态</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="products">
					<tr class="text-c">
						<td><input type="checkbox" value="${productId}" name=""></td>
						<td><img src="${productPic1 }" class="rq_product"></td>
						<td>${productNum }</td>
						<td>这是产品标题标题啊</td>
						<td>鞋子</td>
						<td>2013-6-1 11:11:42</td>
						<td>已发布</td>
						<td class="td-manage"><a style="text-decoration:none"
							onClick="send_again(this,'10001')" href="javascript:;" title="编辑"><i
								class="Hui-iconfont">&#xe60c;</i></a> <a title="删除"
							href="javascript:;"
							onclick="order_detail('删除','订单小详情.html','4','','510')"
							class="ml-5" style="text-decoration:none"><i
								class="Hui-iconfont">&#xe609;</i></a></td>
					</tr>
				</s:iterator>
					<tr class="text-c">
						<td><input type="checkbox" value="1" name=""></td>
						<td><img src="img/pc_img/demo.png" class="rq_product"></td>
						<td>13413514</td>
						<td>这是产品标题标题啊</td>
						<td>鞋子</td>
						<td>2013-6-1 11:11:42</td>
						<td>已发布</td>
						<td class="td-manage"><a style="text-decoration:none"
							onClick="send_again(this,'10001')" href="javascript:;" title="编辑"><i
								class="Hui-iconfont">&#xe60c;</i></a> <a title="删除"
							href="javascript:;"
							onclick="order_detail('删除','订单小详情.html','4','','510')"
							class="ml-5" style="text-decoration:none"><i
								class="Hui-iconfont">&#xe609;</i></a></td>
					</tr>
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


	<script>
	
		/*添加产品*/
		function prod_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
	</script>
</body>
</html>
