<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询邮箱状态信息</title>
</head>
<body>

	<center>
  		<h1>邮箱状态信息显示页面</h1>
  	</center>
  	<p />
  	<form action="<%=basePath %>/emailState/list.do" method="post" >
	    <table border="1px;" width="100%" style="text-align: center;">
	    	<tr>
	    		<td>id</td>
	    		<td>状态编号</td>
	    		<td>状态内容</td>
	    		<td>操作</td>
    		</tr>
	    	<c:forEach items="${esList }" var="es">
	    		<tr>
		    		<td>${es.id }</td>
		    		<td>${es.stateNo }</td>
		    		<td><textarea cols="100" rows="2" >${es.stateContent }</textarea></td>
		    		<td>
		    			<a href="javascript(0);">修改</a> 
		    			<a href="javascript(0);">删除</a>
		    		</td>
	    		</tr>
	    	</c:forEach>
	    </table>
	 
    </form>

</body>
</html>