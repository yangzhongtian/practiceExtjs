<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
	<script type="text/javascript" >  
		var willclose = true ;
		function clickBody(){
			willclose = false;
			
		}
		
		setInterval(function(){
			if(willclose){
				function quitBox(cmd) 
				{      
				  
				       open(location, '_self').close();     //ie 可以实现        goole 火狐不在支持
				    
				}
			}else{
				willclose=false;
			}
		},3000)
	</script>
<body onclick="clickBody()">

</body>
</html>