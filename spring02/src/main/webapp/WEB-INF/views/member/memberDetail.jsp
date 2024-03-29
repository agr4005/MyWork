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

<table style="width: 300px; height: 300px; text-align: center;" border="1">
<c:set var="ss" value="${requestScope.apple}"/>
<c:if test="${!empty requestScope.apple}">
	<tr>
		<td bgcolor="green">I D</td>
		<td>${ss.id}</td>
	</tr>
	<tr>
		<td bgcolor="green">Password</td>
		<td>${ss.password}</td>
	</tr>
	<tr>
		<td bgcolor="green">Name</td>
		<td>${ss.name}</td>
	</tr>
	<tr>
		<td bgcolor="green">Age</td>
		<td>${ss.age}</td>
	</tr>
	<tr>
		<td bgcolor="green">Jno</td>
		<td>${ss.jno}</td>
	</tr>
	<tr>
		<td bgcolor="green">Info</td>
		<td>${ss.info}</td>
	</tr>
	<tr>
		<td bgcolor="green">Point</td>
		<td>${ss.point}</td>
	</tr>
	<tr>
		<td bgcolor="green">Birthday</td>
		<td>${ss.birthday}</td>
	</tr>
	<tr>
		<td bgcolor="green">추천인</td>
		<td>${ss.rid}</td>
	</tr>
	<tr>
		<td bgcolor="green">Image</td>
		<td><img alt="myImage" width="50" height="70"
			src="/spring02/resources/uploadImages/${ss.uploadfile}"></td>
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

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>