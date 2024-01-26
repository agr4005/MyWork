<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web_MVC2 Jo Detail **</title>
</head>
<body>
<h2>** Web_MVC2 Jo Detail **</h2>

<table style="width: 300px; height: 300px; text-align: center;" >
<c:set var="s" value="${requestScope.apple}"/>
<c:if test="${!empty requestScope.apple}">
	<tr>
		<td bgcolor="green">JNO</td>
		<td>${s.jno}</td>
	</tr>
	<tr>
		<td bgcolor="green">JNAME</td>
		<td>${s.jname}</td>
	</tr>
	<tr>
		<td bgcolor="green">CAPTAIN</td>
		<td>${s.captain}</td>
	</tr>
	<tr>
		<td bgcolor="green">PROJECT</td>
		<td>${s.project}</td>
	</tr>
	<tr>
		<td bgcolor="green">SLOGAN</td>
		<td>${s.slogan}</td>
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

&nbsp;<a href="/spring02/jo/joUpdate?jno=${s.jno}">[조 수정]</a>&nbsp;
&nbsp;<a href="/spring02/jo/delete?jno=${s.jno}">[조 삭제]</a>&nbsp;
&nbsp;<a href="/spring02/home">[Home]</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">[이전으로]</a>&nbsp;

</body>
</html>