<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>遍历打印一个元素所有的属性</title>
<script type="text/javascript">
	function getDom(){
		var as = document.getElementById("dd");
		var str ='';
		for(var prop in as){
			
			str += prop +":" +as[prop]+'</br>';
			
			
		}
		document.getElementById('dd').innerHTML=str;
	}
</script>
</head>
<body>
	<a href="#">链接</a>
	<input type="button" onclick="getDom()">
	<div id ="dd"></div>
</body>
</html>