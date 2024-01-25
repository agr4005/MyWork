<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web_MVC2 Member Detail **</title>
</head>
<body>
<h2>** Web_MVC2 Member Detail **</h2>

<table style="width: 300px; height: 300px; text-align: center;" >
<c:set var="ss" value="${requestScope.apple}"/>
<c:if test="${!empty requestScope.apple}">
	<tr>
		<td bgcolor="cyan">I D </td>
		<td>${ss.id}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">Password</td>
		<td>${ss.password}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">Name</td>
		<td>${ss.name}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">Age</td>
		<td>${ss.age}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">Jno</td>
		<td>${ss.jno}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">Info</td>
		<td>${ss.info}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">Point</td>
		<td>${ss.point}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">Birthday</td>
		<td>${ss.birthday}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">추천인</td>
		<td>${ss.rid}</td>
	</tr>
</c:if>
</table>
<c:if test="${empty requestScope.apple}">
	<h3>출력할 자료가 없습니다.</h3>
</c:if>

&nbsp;<a href="home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>