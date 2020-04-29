<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="lib/Hui-iconfont/1.0.8/iconfont.css" />

<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>用户分析</title>
</head>

<body>
	<nav class="breadcrumb">
		<i class="Hui-iconfont">&#xe67f;</i> 账户管理 <span class="c-gray en">&gt;</span>
		用户分析

		<div class="switch" data-animated="false"
			style="float: right; margin-right: 50px;">
			<input type="checkbox" checked />
		</div>
	</nav>
	<div class="date_div">
		<ul class="dataul">
			<li onclick="getDaysData()">日</li>
			<!-- <li>周</li> -->
			<li class="active" onclick="getMonthsData()">月</li>
		</ul>
	</div>
	<div class="page-container">
		<div id="container"
			style="min-width:700px;height:400px;position: relative;"></div>
	</div>
	<div style="padding: 20px;">
		<h2 class="fx_title">今日访客列表</h2>
		<table
			class="table table-border table-bordered table-hover table-bg table-sort  bgw">
			<thead>
				<tr class="text-c">
					<th width="150">访客头像</th>
					<th width="150">微信名</th>
					<th width="">访问时间</th>
				</tr>
			</thead>
			<tbody id="taga">
				<!-- <tr class="text-c" >
					<td><img src="img/pc_img/login_mylogo.png"
						style="height: 100px;"></td>
					<td>SOPHIA</td>
					<td>2017-12-25 14:23:22</td>
					<td >542</td>
				</tr> -->
			</tbody>
		</table>



		<h2 class="fx_title">今日浏览列表</h2>
		<table
			class="table table-border table-bordered table-hover table-bg table-sorta  bgw">
			<thead>
				<tr class="text-c">
					<th width="150">产品头像</th>
					<th width="150">产品编号</th>
					<th width="120">浏览量</th>

				</tr>
			</thead>
			<tbody id="tagb">
<!-- 				<tr class="text-c">
					<td><img src="img/pc_img/login_mylogo.png"
						style="height: 150px;"></td>
					<td>KENZO 春夏新款男装衬衫短袖</td>
					<td>542</td>
				</tr>
 -->				
			</tbody>
		</table>

	</div>
	<div style="height: 50px;"></div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
	<script type="text/javascript"
		src="lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
	<script type="text/javascript"
		src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
	<script type="text/javascript">
		var hcdata = new Array();
		var categories = new Array();
		
		
			$(function() {
				getMonthsData();
				getVisitorData();
				getProVisitData();
			});
			$(function() {
				//日周月切换---选中者li加上Class===active
				$(".dataul li").click(function() {
					$(".dataul li").removeClass("active");
					$(this).addClass("active");
				})
				$('.table-sort').dataTable({
					"aaSorting": [[ 1, "asc" ]],//默认第几个排序
					"bStateSave": true,//状态保存
					"aoColumnDefs": [
					  {"orderable":false,"aTargets":[0,1,2]}// 制定列不参与排序
					]
				});
				$('.table-sorta').dataTable({
					"aaSorting": [[ 1, "asc" ]],//默认第几个排序
					"bStateSave": true,//状态保存
					"aoColumnDefs": [
					  {"orderable":false,"aTargets":[0,1,2]}// 制定列不参与排序
					]
				});
			})
			function getProVisitData(){
				$.ajax({
					url:"<%=basePath %>company/queryTodayProductVisited.do",
					type:"post",
					dataType:"json",
					async:false,
					success:function (data){
					var tmp = "";
					for(var i=0;i < data.productVisits.length;i++){
						tmp = tmp +'<tr class="text-c"> <td><img src="'+data.productVisits[i][0]+'" style="height: 150px;"></td>'
								+'<td>'+data.productVisits[i][1]+'</td>'
								+'<td>'+data.productVisits[i][2]+'</td></tr>'
						
					}
					$("#tagb").append(tmp);
					},
					error:function (){
						alert("获取产品访问数据失败");
					}
				});
			}
			
			function getVisitorData(){
				$.ajax({
					url:"<%=basePath %>company/queryTodayVisitor.do",
					type:"post",
					dataType:"json",
					async:false,
					success:function (data){
					var tmp = "";
					for(var i=0;i < data.visitors.length;i++){
						tmp = tmp +'<tr class="text-c" ><td><img src="'+data.visitors[i].user.avatarUrl+'" style="height: 100px;"></td>'
								+'<td>'+data.visitors[i].user.nickName+'</td>'
								+'<td>'+formatDateTime(new Date(data.visitors[i].visitTime))+'</td></tr>'
					}
					$("#taga").append(tmp);
						
					},
					error:function (){
						alert("获取访客数据失败");
					}
				});
			}
			function formatDateTime(date) {  
		        var y = date.getFullYear();
		        var m = date.getMonth() + 1;
		        m = m < 10 ? ('0' + m) : m;  
		        var d = date.getDate();  
		        d = d < 10 ? ('0' + d) : d;  
		        var h = date.getHours();  
		        h=h < 10 ? ('0' + h) : h;  
		        var minute = date.getMinutes();  
		        minute = minute < 10 ? ('0' + minute) : minute;  
		        var second=date.getSeconds();  
		        second=second < 10 ? ('0' + second) : second;  
		        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;  
		    };

			function paint(){
				Highcharts.chart('container', {
					title: {
						text: '实时概况',
						x: -20 //center
					},
					xAxis: {
						categories: categories
					},
					yAxis: {
						title: {
							text: '人数'
						},
						plotLines: [{
							value: 0,
							width: 1,
							color: '#ffffff'
						}]
					},
					tooltip: {
						valueSuffix: '人'
					},
					legend: {
						layout: 'vertical',
						align: 'right',
						verticalAlign: 'middle',
						borderWidth: 0
					},
					series: [{
						name: '访问量',
						data: hcdata
					}]
				});
			}
			function getDaysData(){
				$.ajax({
					url:"<%=basePath %>company/queryUserVisit.do",
					type:"post",
					dataType:"json",
					async:false,
					data:{
						"id":1
					},
					success:function (data){
						categories = new Array();
						hcdata = new Array();
						for(var i=0;i < data.userVisits.length;i++){
							categories[i]=data.userVisits[i][0];
							hcdata[i] = data.userVisits[i][1];
							categories[i] = categories[i].substring(5,categories[i].length);
						}
						paint();
					},
					error:function (){
						alert("获取数据失败");
					}
				});
				
			}
			function getMonthsData(){
				$.ajax({
					url:"<%=basePath %>company/queryUserVisit.do",
					type:"post",
					dataType:"json",
					async:false,
					data:{
						"id":3
					},
					success:function (data){
						categories = new Array();
						hcdata = new Array();
						for(var i=0;i < data.userVisits.length;i++){
							categories[i]=data.userVisits[i][0];
							hcdata[i] = data.userVisits[i][1];
							categories[i] = categories[i].substring(4,categories[i].length)+"月";
						}
						paint();
					},
					error:function (){
						alert("获取数据失败");
					}
				});
			}
		</script>
	<style>
.highcharts-credits, .highcharts-contextbutton {
	display: none;
}

body {
	background: #f5f5f5;
}
</style>
</body>

</html>
