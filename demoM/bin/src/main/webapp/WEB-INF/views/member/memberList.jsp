<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Mybatis MemberList **</title>
<link rel="stylesheet" type="text/css"
	 href="/resources/myLib/boardcss2.css">
</head>
<body>
<h2>** Spring_Boot Mybatis MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%">
<tr bgcolor="DeepSkyBlue">
			<th>ID</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th>
			<th>Point</th><th>Birthday</th><th>추천인</th><th>Image</th>
			<!-- <th>Password</th> -->
		</tr>
		
		<c:if test="${!empty requestScope.banana}">
			<c:forEach var="s" items="${requestScope.banana}">
			<tr>
			<td>${s.id}</td><td>${s.name}</td><td>${s.age}</td><td>${s.jno}</td>
			<td>${s.info}</td><td>${s.point}</td><td>${s.birthday}</td><td>${s.rid}</td>
			<td><img alt="myImage" width="50" height="70"
			src="/resources/uploadImages/${s.uploadfile}"></td>
			<%-- <td>${s.password}</td> --%>
			</tr>
			</c:forEach>
		</c:if>
	<c:if test="${empty requestScope.banana}">
		<tr>
		<td colspan="9">
		<h3>~~ 출력할 자료가 없습니다. ~~</h3>
		</td>
		</tr>
	</c:if>
</table>
<hr>

&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>