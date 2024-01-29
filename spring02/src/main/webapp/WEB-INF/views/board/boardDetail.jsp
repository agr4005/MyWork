<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web_MVC2 Board Detail **</title>
</head>
<body>
<h2>** Web_MVC2 Board Detail **</h2>

<table style="width: 300px; height: 300px; text-align: center;" >
<c:set var="m" value="${requestScope.apple}"/>
<c:if test="${!empty requestScope.apple}">
	<tr>
		<td bgcolor="green">Seq</td>
		<td>${m.seq}</td>
	</tr>
	<tr>
		<td bgcolor="green">Title</td>
		<td>${m.title}</td>
	</tr>
	<tr>
		<td bgcolor="green">I D</td>
		<td>${m.id}</td>
	</tr>
	<tr>
		<td bgcolor="green">Content</td>
		<td>${m.content}</td>
	</tr>
	<tr>
		<td bgcolor="green">Regdate</td>
		<td>${m.regdate}</td>
	</tr>
	<tr>
		<td bgcolor="green">조회수</td>
		<td>${m.cnt}</td>
	</tr>
</c:if>
</table>
<c:if test="${empty requestScope.apple}">
	<h3>출력할 자료가 없습니다.</h3>
</c:if>
<br><hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>	

&nbsp;<a href="boardUpdate?seq=${m.seq}">[글 수정]</a>&nbsp;
&nbsp;<a href="delete?seq=${m.seq}">[글 삭제]</a>&nbsp;
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>