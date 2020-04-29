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
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />


<title>账号管理列表</title>
<body>
	<div id="tab-system" class="HuiTab" style="padding: 20px;">
		<div class="tabBar cl">
			<span>账户管理列表</span> <span>统计功能</span>
		</div>

		<!--账户管理列表-->
		<div class="tabCon">
			<div class="cl pd-5 bg-1 bk-gray mt-20">
				<span class="r"> <a href="javascript:;"
					onclick="company_add('添加公司信息','<%=basePath %>pages/manage/addComAccount.jsp')"
					class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i>
						添加公司账户</a>
				</span>
			</div>
			<table class="table table-border table-bordered table-hover table-bg table-sort">
				<thead>
					<tr class="text-c">
						<th width="">账户人</th>
						<th width="">公司名称</th>
						<th width="">账户ID</th>
						<th width="">到期时间</th>
						<th width="280">缴费</th>
						<th width="">套餐</th>
						<th width="">联系电话</th>
						<th width="">操作</th>
					</tr>
				</thead>
				<tbody>
				<s:iterator value="companyAccounts">
					<tr class="text-c">
						<td>${owner }</td>
						<td>${companyName }</td>
						<!--<td><u style="cursor:pointer" class="text-primary" onclick="userDetail('查看用户详情','查看用户详情.html')">sophia</u></td>-->
						<td>${companyAccountId }</td>
						<td>${registerDate }</td>
						<td><s:date format="yyyy-MM-dd" name="validityDate"/>
							<button class="pink_btn" onclick="txjf(this,${company.companyId})">提醒缴费</button>
							<s:if test="company.comStatus == 1">
								<button class="dj_btn" onclick="dj(this,${company.companyId})">冻结</button>
							</s:if>
							<s:else>
								<button class="dj_btn" disabled="disabled">已冻结</button>
							</s:else>
						</td>
						<s:if test="taocan == 1">
							<td>套餐一</td>
						</s:if>
						<s:elseif test="taocan == 2">
							<td>套餐二</td>
						</s:elseif>
						<s:elseif test="taocan == 3">
							<td>套餐三</td>
						</s:elseif>
						<s:else>
							<td></td>
						</s:else>
						<td>${phoneNum }</td>
						<td class="td-manage"><a style="text-decoration:none"
							onClick="account_detail('查看','<%=basePath %>pages/manage/detailComAccount.jsp?id=${companyAccountId }','','510')"
							href="javascript:;" title="账户详情"><i class="Hui-iconfont">&#xe709;</i></a>
							<a title="编辑公司信息" onClick="alter_detail('编辑','<%=basePath %>pages/manage/updateComAccount.jsp?id=${companyAccountId }')"><i
								class="Hui-iconfont">&#xe647;</i></a>
						</td>
					</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
		<!--中文结束-->
		<!--english开始-->
		<div class="tabCon"></div>
		<!--english结束-->
	</div>
	</div>

	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript"
		src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$('.table-sort').dataTable({
				"aaSorting" : [ [ 1, "asc" ] ], //默认第几个排序
				"bStateSave" : true, //状态保存
				"aoColumnDefs" : [
					{
						"orderable" : false,
						"aTargets" : [ 0, 1, 2, 3, 4, 5, 6 ]
					} // 制定列不参与排序
				]
			});
	
		});
	</script>
	<script type="text/javascript">
			$(function() {
				$("#tab-system").Huitab({
					index: 0
				});
			});
				/*添加公司信息*/
			function company_add(title,url){
				var index = layer.open({
					type: 2,
					title: title,
					content: url
				});
				layer.full(index);
			}
			/*编辑账户信息*/
			function alter_detail(title,url){
				var index = layer.open({
					type: 2,
					title: title,
					content: url
				});
				layer.full(index);
			}

		
			/*冻结账户*/
			function dj(obj, id) {
				layer.confirm('确认要冻结该用户吗？', function(index) {
					$.ajax({
						type: 'POST',
						url: '<%=basePath%>manage/updateComStatus.do',
						dataType: 'json',
						data:{
							"id":id,
							"str":"冻结"
						},
						success: function(data) {
							layer.msg('已冻结!', {
								icon: 1,
								time: 1000
								
							},function (){
								window.location.reload();
							});
						},
						error: function(data) {
							console.log(data.msg);
						},
					});
				});
			}
			/*删除账户*/
			function del_account(obj, id) {
				layer.confirm('确认要删除该账户吗？', function(index) {
					$.ajax({
						type: 'POST',
						url: '',
						dataType: 'json',
						success: function(data) {
							$(obj).parents("tr").remove();
							layer.msg('已删除!', {
								icon: 1,
								time: 1000
							});
						},
						error: function(data) {
							console.log(data.msg);
						},
					});
				});
			}
			
			
			//提醒缴费
			function txjf(obj,id){
				layer.confirm('确认要要向该帐号发送邮件提醒吗？', function(index) {
					$.ajax({
						type: 'POST',
						url: '<%=basePath%>manage/reminded2Pay.do',
						dataType: 'json',
						data:{
							"id":id
						},
						success: function(data) {
							if(data.code==0){
								layer.msg('提醒成功');
								$(obj).after('<button class="orange_btn"  disabled="disabled" >已提醒缴费</button>');
								$(obj).remove();
							}else if(data.code == -1){
								layer.msg(data.str,{
									  time: 4000 
									});
							}else if(data.code == -2){
								layer.msg("邮件发送失败。");
							}
						},
						error: function(data) {
							layer.msg('提醒失败')
						},
					});
				});
				

				
			}
			/*查看账户详情*/
			function account_detail(title, url, w, h) {
				layer_show(title, url, w, h);
			}
			
			//查看客户详情
			function Customer_detail(title, url, w, h) {
				layer_show(title, url, w, h);
			}
			sj_detail
			
			//查看发票
			function sj_detail(title, url, w, h) {
				layer_show(title, url, w, h);
			}

			//接单
			function jd_order(title, url, w, h) {
				layer_show(title, url, w, h);
			}
			
			/*拒单*/
			function Refuse_Order(obj,id){
				layer.confirm('确定拒单吗？',function(index){
					//此处请求后台程序，下方是成功后的前台处理……
					   $(obj).parents(".panel ").remove();
				});
			}
		</script>
</body>

</html>
