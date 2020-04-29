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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<!--/meta 作为公共模版分离出去-->

<title>团队风采</title>
</head>
<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 企业 <span class="c-gray en">&gt;</span>
		团队风采
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
		<p class="blue">让大家知道我们是一个友爱的集体，我们朝气蓬勃，热爱生活
		<div class="cl pd-5 bg-1 bk-gray mt-20">
			<span class="r"> <a href="javascript:;"
				onclick="article_add('添加团队风采','<%=basePath%>pages/company/addActivity.jsp')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加团队风采文章</a>
			</span>
		</div>
		<div class="mt-20">
			<table
				class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="">封面</th>
						<th width="">标题</th>
						<th width="180">发布日期</th>
						<th width="100">操作</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="comActivities">
					<tr class="text-c">
						<td><img src="${activityTitlePic }" class="fc_tp"></td>
						<td>${activityTitle }</td>
						<td>${createTime }</td>
						<td class="td-manage"><a style="text-decoration:none"
							onClick="article_edit('编辑团队风采','<%=basePath%>pages/company/editActivity.jsp?id=${companyActivitiesId }')"
							href="javascript:;" title="编辑"><i class="Hui-iconfont">&#xe60c;</i></a>
							<a title="删除" href="javascript:;" onclick="article_DEL(this,${companyActivitiesId})"
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
	$(function() {

		$(function() {
			$('.table-sort').dataTable({
				"aaSorting" : [ [ 1, "asc" ] ], //默认第几个排序
				"bStateSave" : true, //状态保存
				"aoColumnDefs" : [
					{
						"orderable" : false,
						"aTargets" : [ 0, 1, 2, 3 ]
					} // 制定列不参与排序
				]
			});

		});



	});
	/*添加团队风采文章*/
	function article_add(title, url) {
		var index = layer.open({
			type : 2,
			title : title,
			content : url,
			end:function (){
				location.reload();
			}
		});
		layer.full(index);
	}

	/*编辑团队风采文章*/
	function article_edit(title, url) {
		var index = layer.open({
			type : 2,
			title : title,
			content : url,
			end: function () {
		        location.reload();
		      }
		});
		layer.full(index);
	}
	function onoff(){
		$.ajax({
			url:"<%=basePath%>company/activityOnoff.do",
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
	/*删除文章*/
	function article_DEL(obj, id) {
		layer.confirm('确认要删除该文章吗？', function(index) {
			$.ajax({
				type : 'POST',
				url : '<%=basePath%>company/delComActivity.do',
				dataType : 'json',
				data:{
					"id":id					
				},
				success : function(data) {
					$(obj).parents("tr").remove();
					layer.msg('已删除!', {
						icon : 1,
						time : 1000
					});
				},
				error : function(data) {
					console.log(data.msg);
				},
			});
		});
	}
</script>
</body>
</html>
