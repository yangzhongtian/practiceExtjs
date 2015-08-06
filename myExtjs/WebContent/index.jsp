<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="js-lib/Extjs/resources/css/ext-all-gray.css">
	<script type="text/javascript" src="js-lib/Extjs/ext-all-debug-w-comments.js"></script>
	<script type="text/javascript"src="js-lib/Extjs/locale/ext-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/test.js"></script>
	
	<script type="text/javascript">
		Ext.onReady(function(){
			
			Ext.create('Ext.form.Panel',{
				title:'登录',
				width:300,
				x:450,
				y:150,
				frame:true,
				url:'webview/loginAction_login',
				height:150,
				defaultType:'textfield',
				items:[{
					name:'userName',
					fieldLabel:'用户名',
				},{
					name:'passWord',
					fieldLabel:'密码'
				}],
				renderTo:Ext.getBody(),
				buttons:[{
					text:'重置',
					handler:function(){
						this.up('form').getForm().reset();
					}
				},{
					text:'提交',
					handler:function(){
						this.up('form').submit();
					}
				}]
			})
			
			
		});
	</script>
  </head>
  
  <body>
    
  </body>
</html>
