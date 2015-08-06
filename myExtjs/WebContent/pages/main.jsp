<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'main.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- Ext导入文件 -->
	<link rel="stylesheet" type="text/css" href="js-lib/Extjs/resources/css/ext-all-gray.css">
	<script type="text/javascript" src="js-lib/Extjs/ext-all-debug-w-comments.js"></script>
	<script type="text/javascript"src="js-lib/Extjs/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
  </head>
  
  <body>
    
  </body>
</html>
