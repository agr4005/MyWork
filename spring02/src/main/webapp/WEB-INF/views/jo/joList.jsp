<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Web02_MVC02 joList **</title>
</head>
<body>
<h2>** Web02_MVC02 joList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%">
<tr bgcolor="DeepSkyBlue">
			<th>JNO</th><th>JNAME</th><th>CAPTAIN</th><th>조장명</th><th>PROJECT</th><th>SLOGAN</th>
		</tr>
		
		<c:if test="${!empty requestScope.banana}">
			<c:forEach var="s" items="${requestScope.banana}">
			<tr>
			<td><a href="/spring02/jo/joDetail?jno=${s.jno}">${s.jno}</a></td><td>${s.jname}</td><td>${s.captain}</td>
			<td>${s.cname}</td><td>${s.project}</td><td>${s.slogan}</td>
			</tr>
			</c:forEach>
		</c:if>
	<c:if test="${empty requestScope.banana}">
		<tr>
		<td colspan="5">
		<h3>~~ 출력할 자료가 없습니다. ~~</h3>
		</td>
		</tr>
	</c:if>
</table>
<hr>

&nbsp;<a href="/spring02/jo/joinForm">[조 등록]</a>&nbsp;
&nbsp;<a href="/spring02/home">[Home]</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">[이전으로]</a>&nbsp;
</body>
</html>