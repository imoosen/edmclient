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
<title>添加邮箱内容信息</title>

<link href="<%=basePath %>/css/add_update.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="shareBox">
	<center>
  		<h1 class="cen">添加邮箱内容信息</h1>
  	</center>
  	<p />
  	<form action="<%=basePath %>/emailContent/add.do" method="post" >
		<ul class="form_info">
		    <li>
		    	<label>邮件主题：</label>
		    	<input name="title" type="text" class="dfinput" placeholder="请输入邮件主题" class="top_txtbox"  onfocus="if (placeholder =='请输入邮件主题'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入邮件主题'}"/> 
		    </li>
		    <li>
		    	<label>邮件内容：</label>
		    	<textarea cols="50" rows="10" name="content" class="dfinput" placeholder="请输入邮件内容 " class="top_txtbox"  onfocus="if (placeholder =='请输入邮件内容'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入邮件内容'}" ></textarea>
				<!-- <input name="content" type="text" class="dfinput" placeholder="请输入邮件内容 " class="top_txtbox"  onfocus="if (placeholder =='请输入邮件内容'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入邮件内容'}"/> -->	    	
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