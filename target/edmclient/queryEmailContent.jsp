<%@ page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询邮箱内容信息</title>
</head>
<body>

	<center>
  		<h1>邮箱内容信息显示页面</h1>
  	</center>
  	<p />
    <table border="1px;" width="100%" style="text-align: center;">
    	<tr>
    		<td>id</td>
    		<td>邮件标题</td>
    		<td>邮件内容</td>
    		<td>是否发送</td>
    		<td>创建日期</td>
    		<td>修改日期</td>
    		<td>是否有效</td>
    		<td>操作</td>
   		</tr>
    	<c:forEach items="${ecList }" var="ec">
    		<tr>
	    		<td>${ec.id }</td>
	    		<td>
	    			<textarea cols="30" rows="10" >${ec.emailTitle }</textarea>
	    		</td>
	    		<td>
	    			<textarea cols="50" rows="10" >${ec.content }</textarea>
	    		</td>
	    		<td>
	    			<c:choose>
	    				<c:when test="${ec.isSend eq 1 }">
	    					发送
	    				</c:when>
	    				<c:otherwise>
	    					不发送
	    				</c:otherwise>
	    			</c:choose>
	    		</td>
	    		<td><fmt:formatDate value="${ec.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td><fmt:formatDate value="${ec.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	    		<td>
	    			<c:choose>
	    				<c:when test="${ec.contentIsValid eq 1 }">
	    					有效
	    				</c:when>
	    				<c:otherwise>
	    					无效
	    				</c:otherwise>
	    			</c:choose>
	    		</td>
	    		<td>
	    			<a href="queryById.do?id=${ec.id }">修改</a>
					<a href="javascript:if(confirm('您确定删除吗？')){window.location.href='delete.do?id=${ec.id }';}">删除</a>
    			</td>
    		</tr>
    	</c:forEach>
    </table>

</body>
</html>