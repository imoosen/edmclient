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
<title>修改邮箱内容信息</title>

<link href="<%=basePath %>/css/add_update.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/js/calendar.js"></script>
</head>
<body>
<div class="shareBox">
	<center>
  		<h1 class="cen">修改邮箱内容信息</h1>
  	</center>
  	<p />
  	<form action="update.do" method="post" >
  		<input type="hidden" name="id" value="${emailContent.id }"/>
		<ul class="form_info">
		    <li>
		    	<label>邮件主题：</label>
				<input name="title" type="text" class="dfinput" class="top_txtbox" value="${emailContent.emailTitle }" /> 		    	
				<%-- <textarea name="title" cols="30" rows="5" class="dfinput" class="top_txtbox" value="${emailContent.emailTitle }" ></textarea> --%>
		    </li>
		    <li>
		    	<label>邮件内容：</label>
				<%-- <input name="content" type="text" class="dfinput" class="top_txtbox" value="${emailContent.content }" /> --%>	    		
				<textarea name="content" cols="50" rows="10" class="dfinput" class="top_txtbox" value="${emailContent.content }" ></textarea>
	    	</li>
	    	<li>
		    	<label>邮件是否发送：</label>
				<select name="isSend" id="isSend">
					<option value="">请选择</option>
				    <option value="0">不发送</option>
				    <option value="1">发送</option>
				</select>
	    	</li>
	    	<li>
		    	<label>邮件创建时间：</label>
		    	<input name="createDate" type="text" class="dfinput" class="top_txtbox" readonly="readonly" value="${emailContent.createDate }" />
	    	</li>
	    	<li>
		    	<label>邮件修改时间：</label>
		    	<input name="updateDate" type="text" class="dfinput" class="top_txtbox" value="${emailContent.updateDate }" onclick="SelectDate(this,'yyyy-MM-dd hh:mm:ss')" />
	    	</li>
	    	<li>
		    	<label>邮件内容是否有效：</label>
				<select name="isValid" id="isValid">
					<option value="">请选择</option>
				    <option value="0">无效</option>
				    <option value="1">有效</option>
				</select>
	    	</li>
		    <li>
		    	<label>&nbsp;</label>
		    	<input name="" type="submit" class="btn" value="确认修改"/>
		    </li>
	    </ul>
    </form>
</div>

</body>
</html>