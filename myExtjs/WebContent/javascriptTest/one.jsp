<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>页面等待</title>
<script type="text/javascript">
	function submitForm(){
		var a = document.getElementById("doing");
		a.style.display='';
		//self.location.reload();
		
	}

</script>
</head>
<body>
	<div id="doing" style="Z-index: 99; left:0x;top:0px;display: none; width: 100%;position: absolute;height: 100%; background: grey">
			<br/>
			<br/>
			<br/>载入中请等待。。。。
	</div>
	<p>
		<input type="button" value="提交表单" onclick="submitForm()">
	</p>
</body>
</html>