<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加邮箱状态信息</title>

<link href="<%=basePath %>/css/add_update.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="shareBox">
	<center>
  		<h1 class="cen">添加邮箱状态信息</h1>
  	</center>
  	<p />
  	<form action="<%=basePath %>/emailState/add.do" method="post" >
		<ul class="form_info">
		    <li>
		    	<label>状态编号：</label>
		    	<input name="stateNo" type="text" class="dfinput" placeholder="请输入状态编号" class="top_txtbox"  onfocus="if (placeholder =='请输入状态编号'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入状态编号'}"/> 
		    </li>
		    <li>
		    	<label>状态内容：</label>
		    	<input name="stateContent" type="text" class="dfinput" placeholder="请输入状态内容" class="top_txtbox"  onfocus="if (placeholder =='请输入状态内容'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入状态内容'}"/>
	    	</li>
		    <li>
		    	<label>&nbsp;</label>
		    	<input name="" type="submit" class="btn" value="确认保存"/>
		    </li>
	    </ul>
    </form>
</div>
	

</body>
</html>