<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		function submitForm(){
			var a =document.getElementById("doing");
			a.style.display="";
		}
	</script>
</head>
<body>
	<div id="doing" style="position:absolute;width: 100%;height: 100%;background: grey;top:0px;left:0px;display: none" >
		<br/>                  <!-- 换行符 -->
		<br/>
		<br/>页面加载中。。。。。
	</div>
	<p>
		<input type="button" value="提交效果" onclick="submitForm()">
	</p>
</body>
</html>