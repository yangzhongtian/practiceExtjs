<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		document.title= "插入新的标题"
		function newPage(){
			alert(document.getElementById("val"));
			top.location.href=document.getElementById("val").value;
		}
	</script>
</head>
<body>
	<input type="text" id="val" value="">
	<input type="button" onclick="newPage()" value="点击">
</body>
</html>