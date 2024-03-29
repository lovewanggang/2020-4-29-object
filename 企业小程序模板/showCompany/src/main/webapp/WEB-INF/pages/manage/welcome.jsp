<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>企业管理系统</title>
</head>
<body>
	<header class="navbar-wrapper">
		<div class="navbar navbar-fixed-top">
			<div class="container-fluid cl">
				<a class="logo navbar-logo f-l mr-10 hidden-xs"
					href="javascript:void(0)">小程序客户管理</a> <a aria-hidden="false"
					class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>

				<nav id="Hui-userbar"
					class="nav navbar-nav navbar-userbar hidden-xs">
					<ul class="cl">

						<!-- <li class="dropDown dropDown_hover"><a href="#"
							class="dropDown_A">admin <i class="Hui-iconfont">&#xe6d5;</i></a>
							<ul class="dropDown-menu menu radius box-shadow">
								<li><a href="#">切换账户</a></li>
								<li><a href="#">退出</a></li>
							</ul></li> -->
							<li><a onclick="window.location.href='<%=basePath%>pages/manage/login.jsp'">退出</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</header>
	<aside class="Hui-aside">
		<div class="menu_dropdown bk_2">
			<dl id="menu-article">
				<dt>
					<i class="Hui-iconfont">&#xe63c;</i> 客户管理后台<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="<%=basePath %>manage/queryComAccountInfo.do" data-title="账户管理列表"
							href="javascript:void(0)">账户管理列表</a></li>
						<li><a data-href="<%=basePath %>pages/manage/addComAccount.jsp" data-title="添加公司信息"
							href="javascript:void(0)">添加公司信息</a></li>
					</ul>
				</dd>
			</dl>
			<%-- <dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe639;</i> 企业管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="<%=basePath %>company/queryCompanyInfo.do" data-title="企业信息"
							href="javascript:void(0)">企业信息</a></li>
						<li><a data-href="<%=basePath %>company/queryBusiness.do" data-title="我们提供"
							href="javascript:void(0)">我们提供</a></li>
						<!-- <li><a data-href="人气产品.html" data-title="人气产品"
							href="javascript:void(0)">人气产品</a></li> -->
						<li><a data-href="<%=basePath %>company/findVedio.do" data-title="视频展示"
							href="javascript:void(0)">视频展示</a></li>
						<li><a data-href="<%=basePath %>company/queryComActivities.do" data-title="团队风采列表"
							href="javascript:void(0)">团队风采列表</a></li>
						<li><a data-href="<%=basePath %>company/ourImage.do" data-title="我们的形象"
							href="javascript:void(0)">我们的形象</a></li>
						<li><a data-href="<%=basePath %>company/queryHonorsImage.do" data-title="荣誉资质"
							href="javascript:void(0)">荣誉资质</a></li>
						<li><a data-href="<%=basePath %>company/queryPartnerImage.do" data-title="合作伙伴"
							href="javascript:void(0)">合作伙伴</a></li>
						<li><a data-href="<%=basePath %>company/findJob.do" data-title="加入我们"
							href="javascript:void(0)">加入我们</a></li>
						<li><a data-href="<%=basePath %>company/getComInfo.do" data-title="联系我们"
							href="javascript:void(0)">联系我们</a></li>

					</ul>
				</dd>
			</dl>
			<dl id="menu-picture">
				<dt>
					<i class="Hui-iconfont">&#xe646;</i> 产品管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="<%=basePath %>pages/product/addBigClass.jsp" data-title="添加分类"
							href="javascript:void(0)">添加分类</a></li>
						<li><a data-href="<%=basePath %>company/queryProClass.do" data-title="分类列表"
							href="javascript:void(0)">分类列表</a></li>
						<li><a data-href="<%=basePath %>pages/product/addProduct.jsp" data-title="添加产品信息"
							href="javascript:void(0)">添加产品信息</a></li>
						<li><a data-href="<%=basePath %>company/listProduct.do" data-title="产品列表"
							href="javascript:void(0)">产品列表</a></li>
					</ul>
				</dd>
			</dl>

			<dl id="menu-product">
				<dt>
					<i class="Hui-iconfont">&#xe62b;</i>账户管理<i
						class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
				</dt>
				<dd>
					<ul>
						<li><a data-href="<%=basePath %>company/findComAccount.do" data-title="账户信息"
							href="javascript:void(0)">账户信息</a></li>
						<li><a data-href="<%=basePath %>pages/account/editPassword.jsp" data-title="修改密码"
							href="javascript:void(0)">修改密码</a></li>
					</ul>
					</ul>
				</dd>
			</dl>
--%>
			<!--<dl id="menu-product">
			<dt><i class="Hui-iconfont">&#xe60a;</i> 账户总览<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
			<dd>
				<ul>
					<li><a data-href="Add_Account.html" data-title="添加账号" href="javascript:void(0)">添加账号</a></li>
					<li><a data-href="Account_Management.html" data-title="管理员账号" href="javascript:void(0)">管理员账号</a></li>
			</ul>
		</dd>
	</dl> 
	-->

		</div>
	</aside>
	<div class="dislpayArrow hidden-xs">
		<a class="pngfix" href="javascript:void(0);"
			onClick="displaynavbar(this)"></a>
	</div>
	<section class="Hui-article-box">
		<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
			<div class="Hui-tabNav-wp">
				<ul id="min_title_list" class="acrossTab cl">
					<li class="active"><span title="欢迎" data-href="欢迎">欢迎</span> <em></em></li>
				</ul>
			</div>
			<div class="Hui-tabNav-more btn-group">
				<a id="js-tabNav-prev" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a
					id="js-tabNav-next" class="btn radius btn-default size-S"
					href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
			</div>
		</div>
		<div id="iframe_box" class="Hui-article">
			<div class="show_iframe">
				<div style="display:none" class="loading"></div>
				<iframe scrolling="yes" frameborder="0" src="<%=basePath %>pages/company/welcome.html"></iframe>
			</div>
		</div>
	</section>

	<div class="contextMenu" id="Huiadminmenu">
		<ul>
			<li id="closethis">关闭当前</li>
			<li id="closeall">关闭全部</li>
		</ul>
	</div>
	<!--_footer 作为公共模版分离出去-->
	<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
	<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
	<script type="text/javascript"
		src="static/h-ui.admin/js/H-ui.admin.js"></script>
	<!--/_footer 作为公共模版分离出去-->

	<!--请在下方写此页面业务相关的脚本-->
	<script type="text/javascript"
		src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
	<script type="text/javascript">
	
		/*个人信息*/
		function myselfinfo() {
			layer.open({
				type : 1,
				area : [ '300px', '200px' ],
				fix : false, //不固定
				maxmin : true,
				shade : 0.4,
				title : '查看信息',
				content : '<div>管理员信息</div>'
			});
		}
	
		/*资讯-添加*/
		function article_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*图片-添加*/
		function picture_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*产品-添加*/
		function product_add(title, url) {
			var index = layer.open({
				type : 2,
				title : title,
				content : url
			});
			layer.full(index);
		}
		/*用户-添加*/
		function member_add(title, url, w, h) {
			layer_show(title, url, w, h);
		}
	</script>


</body>
</html>
