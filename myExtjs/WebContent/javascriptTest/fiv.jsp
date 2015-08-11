<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>打开页面是广告</title>
	<script type="text/javascript">
		var links = document.getElementsByTagName("a");
		console.log(links);
		
		window.onload= function(){		
			var a =  document.getElementById('ad');
			a.innerHTML='<div style="width:300px;height:300px;background:grey">广告=</div>'
			
			setInterval(function(){
				a.style.display='none'
			}, 5000);
		
		}
	</script>
</head>
<body>
	<p>
		网页上部
	</p>
	<div id ="ad">
		
	</div>
	<p height:1000px>
		网页下部
	</p>
</body>
</html>