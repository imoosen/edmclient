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
<title>添加邮箱信息</title>

<link href="<%=basePath %>/css/add_update.css" rel="stylesheet" type="text/css" />

</head>
<body>
<div class="shareBox">
	<center>
  		<h1 class="cen">添加邮箱信息</h1>
  	</center>
  	<p />
  	<form action="<%=basePath %>/email/add.do" method="post" >
		<ul class="form_info">
		    <li>
		    	<label>邮箱地址：</label>
		    	<input name="email" type="text" class="dfinput" placeholder="请输入邮箱地址" class="top_txtbox"  onfocus="if (placeholder =='请输入邮箱地址'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入邮箱地址'}"/> 
		    </li>
		    <li>
		    	<label>省份：</label>
		    	<input name="province" type="text" class="dfinput" placeholder="请输入省份" class="top_txtbox"  onfocus="if (placeholder =='请输入省份'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入省份'}"/>
	    	</li>
	    	<li>
		    	<label>城市：</label>
		    	<input name="city" type="text" class="dfinput" placeholder="请输入城市" class="top_txtbox"  onfocus="if (placeholder =='请输入城市'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入城市'}"/>
	    	</li>
	    	<li>
		    	<label>来源：</label>
		    	<input name="source" type="text" class="dfinput" placeholder="请输入邮箱地址来源" class="top_txtbox"  onfocus="if (placeholder =='请输入邮箱地址来源'){placeholder =''}" onblur="if (placeholder ==''){placeholder='请输入邮箱地址来源'}"/>
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