<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" type="text/css"
	 href="/spring02/resources/myLib/boardcss.css">
<meta charset="UTF-8">
<title>** Web_MVC2 Board Detail **</title>
</head>
<body>
<h2>** Web_MVC2 Board Detail **</h2>

<table style="width: 500px; height: 300px; text-align: center;" >
<c:set var="m" value="${requestScope.apple}"/>
<c:if test="${!empty requestScope.apple}">
	<tr>
		<td bgcolor="yellowgreen">Seq</td>
		<td>${m.seq}</td>
	</tr>
	<tr>
		<td bgcolor="yellowgreen">Title</td>
		<td>${m.title}</td>
	</tr>
	<tr>
		<td bgcolor="yellowgreen">I D</td>
		<td>${m.id}</td>
	</tr>
	<tr>
		<td bgcolor="yellowgreen">Content</td>
		<td>${m.content}</td>
	</tr>
	<tr>
		<td bgcolor="yellowgreen">Regdate</td>
		<td>${m.regdate}</td>
	</tr>
	<tr>
		<td bgcolor="yellowgreen">조회수</td>
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
<c:if test="${!empty sessionScope.loginID}">
&nbsp;<a href="boardInsert">새글등록</a>&nbsp;
<!-- 답글등록을 위해 부모글의 root, step, indent 값이 필요하기 때문에
    서버로 보내주어야함 (쿼리스트링으로 작성)    -->	
&nbsp; <a href="replyInsert?root=${apple.root}&step=${apple.step}&indent=${apple.indent}">답글등록</a> &nbsp;
</c:if>
<c:if test="${m.id == sessionScope.loginID}">
&nbsp;<a href="boardUpdate?seq=${m.seq}">[글 수정]</a>&nbsp;
&nbsp;<a href="delete?seq=${apple.seq}&root=${apple.root}">[글 삭제]</a>&nbsp;
</c:if>

&nbsp; <a href="boardList">BList</a> &nbsp;
&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>