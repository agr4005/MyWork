<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Mybatis Jo Detail **</title>
<link rel="stylesheet" type="text/css"
	 href="/resources/myLib/boardcss2.css">
</head>
<body>
<h2>** Spring_Boot Mybatis Jo Detail **</h2>

<table style="width: 300px; height: 300px; text-align: center;" >
<c:set var="s" value="${requestScope.apple}"/>
<c:if test="${!empty requestScope.apple}">
	<tr>
		<td bgcolor="cyan">JNO</td>
		<td>${s.jno}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">JNAME</td>
		<td>${s.jname}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">CAPTAIN</td>
		<td>${s.captain}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">PROJECT</td>
		<td>${s.project}</td>
	</tr>
	<tr>
		<td bgcolor="cyan">SLOGAN</td>
		<td>${s.slogan}</td>
	</tr>
</c:if>
</table>

<h2>** ${s.jno} Jo MemberList **</h2>

<table style="width:100%">
<tr bgcolor="yellowgreen">
			<th>ID</th><th>Password</th><th>Name</th><th>Age</th><th>Jno</th>
			<th>Info</th><th>Point</th><th>Birthday</th><th>추천인</th>
		</tr>
		
		<c:if test="${!empty requestScope.banana}">
			<c:forEach var="ss" items="${requestScope.banana}">
			<tr>
			<td>${ss.id}</td><td>${ss.password}</td><td>${ss.name}</td><td>${ss.age}</td><td>${ss.jno}</td>
			<td>${ss.info}</td><td>${ss.point}</td><td>${ss.birthday}</td><td>${ss.rid}</td>
			</tr>
			</c:forEach>
		</c:if>
	<c:if test="${empty requestScope.banana}">
		<tr>
		<td colspan="9">
		<h3>~~ 출력할 멤버리스트가 없습니다. ~~</h3>
		</td>
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

&nbsp;<a href="/jo/joUpdate?jno=${s.jno}">[조 수정]</a>&nbsp;
&nbsp;<a href="/jo/delete?jno=${s.jno}">[조 삭제]</a>&nbsp;
&nbsp;<a href="/home">[Home]</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">[이전으로]</a>&nbsp;

</body>
</html>