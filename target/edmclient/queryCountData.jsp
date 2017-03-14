<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计每分钟、每小时、每天的发送量页面</title>

<link href="<%=basePath %>/css/add_update.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/js/calendar.js"></script>

</head>
<body>
	<center>
		<h1>统计每分钟的邮件发送量</h1>
	</center>
  	<form action="queryCountByMinute.do" method="post" >
		<ul class="form_info">
			<li>
		    	<label>请填邮箱日志表：</label>
		    	<input name="table" type="text" class="dfinput" class="top_txtbox" />
	    	</li>
	    	<li>
		    	<label>当前分钟已发送邮件数量：${countMinute }</label> 
		    </li>
		    <li>
		    	<label>&nbsp;</label>
		    	<input name="" type="submit" class="btn" value="确认统计"/>
		    </li>
	    </ul>
    </form>
    
    <p>
    <p>
    
    <center>
    	<h1>统计每小时的邮件发送量</h1>
    </center>
    <form action="queryCountByHour.do" method="post" >
		<ul class="form_info">
	    	<li>
		    	<label>选择邮箱日志编号：</label>
		    	<input name="table" type="text" class="dfinput" class="top_txtbox" />
	    	</li>
	    	<li>
		    	<label>当前小时已发送邮件数量：${countHour }</label> 
		    </li>
		    <li>
		    	<label>&nbsp;</label>
		    	<input name="" type="submit" class="btn" value="确认统计"/>
		    </li>
	    </ul>
    </form>
    
    <p>
    <p>
    
    <center>
    	<h1>统计每天的邮件发送量</h1>
    </center>
    <form action="queryCountByDay.do" method="post" >
		<ul class="form_info">
	    	<li>
		    	<label>请填邮箱日志表：</label>
		    	<input name="table" type="text" class="dfinput" class="top_txtbox" />
	    	</li>
	    	<li>
		    	<label>当天已发送邮件数量：${countDay }</label> 
		    </li>
		    <li>
		    	<label>&nbsp;</label>
		    	<input name="" type="submit" class="btn" value="确认统计"/>
		    </li>
	    </ul>
    </form>
</body>
</html>