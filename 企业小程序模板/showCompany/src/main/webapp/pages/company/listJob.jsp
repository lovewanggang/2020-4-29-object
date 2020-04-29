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
<base href="<%=basePath%>">

<head>
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


<title>加入我们</title>
</head>

<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 企业管理 <span class="c-gray en">&gt;</span>
		加入我们
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
		<p class="blue">招兵买马，增加团队实力！</p>
		<div class="cl pd-5 bg-1 bk-gray mt-20 mb-20">
			<span class="r"> <a href="javascript:;"
				onclick="position_add('添加职位','<%=basePath %>pages/company/addJob.jsp')"
				class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
					添加公司职位</a>
			</span>
		</div>
		<table
			class="table table-border table-bordered table-hover table-bg table-sort">
			<thead>
				<tr class="text-c">
					<th width="">职位</th>
					<th width="">薪资</th>
					<th width="">工作经验</th>
					<th width="">学历</th>
					<th width="230">发布时间</th>
					<th width="">发布时长</th>
					<th width="">操作</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="jobs">
				<tr class="text-c">
					<td>${cjiName }</td>
					<td>${cjiSalary }</td>
					<td>${cjiExp }</td>
					<td>${cjiEdu }</td>
					<td>${creatTime }</td>
					<td>永久</td>
					<td class="td-manage"><a title="编辑职位信息"
						onClick="alter_position('编辑','<%=basePath %>pages/company/editJob.jsp?id=${companyJobInfoId}')"><i
							class="Hui-iconfont">&#xe647;</i></a> <a title="删除"
						href="javascript:;" onclick="del_position(this,${companyJobInfoId})" class="ml-5"
						style="text-decoration:none"><i class="Hui-iconfont">&#xe609;</i></a>
					</td>
				</tr>
			</s:iterator>
				
			</tbody>
		</table>
	</article>

	<!--_footer 作为公共模版分离出去-->
	
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<script type="text/javascript"
		src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.table-sort').dataTable({
				"bSort" : false, //默认第几个排序
				"bStateSave" : true, //状态保存
				"aoColumnDefs" : [
					{"orderable" : false} // 制定列不参与排序
				]
			});
	
		});
	</script>


	<script type="text/javascript">
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
	
			//var ue = UE.getEditor('editor');
		});
		/*添加职位信息*/
		function position_add(title, url) {
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
		/*编辑职位信息*/
		function alter_position(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*删除职位*/
		function del_position(obj, id) {
			layer.confirm('确认要删除该职位吗？', function(index) {
				$.ajax({
					type : 'POST',
					url : '<%=basePath%>company/delJob.do',
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
		function onoff(){
			$.ajax({
				url:"<%=basePath%>company/jobOnoff.do",
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
	</script>

</body>

</html>
