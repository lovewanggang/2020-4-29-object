<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyJsp.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script src="js/ajaxfileupload.js"></script>
  </head>
  <script type="text/javascript">
function uploadImg() {
	$.ajaxFileUpload({
		type : "post",
		url : "<%=basePath%>company/test.do",
		dataType : 'json',
		fileElementId : 'imageInput',
		success : function(data) {
			alert("0000000000000");
		}
	});
}
</script>
  <body>
    <li id="uploadImg">
<input id="imageInput" name="image" type="file" class="file" onchange="uploadImg()">
</li>
  </body>
</html>
