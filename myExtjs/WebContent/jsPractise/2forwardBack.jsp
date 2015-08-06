<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '2forwardBack.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
			function goForward(){
				window.history.forward();
			}
			
			function goBack(){
				window.history.back();
			}
			function closeWin(){
				alert('hello')
				window.close();
				alert('hello2');
			}
		</script>
  </head>
  
  <body>
    <input type="button" onclick="goForward()" value="Hello">
    <input type="button" onclick="goBack()" value="back">
    
    <input type ="button" onclick="closeWin()" value="关闭窗口">
  </body>
</html>
