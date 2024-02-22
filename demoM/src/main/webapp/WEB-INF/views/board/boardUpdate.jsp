<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Mybatis Update Form **</title>
</head>
	<link rel="stylesheet" type="text/css"
	 href="/resources/myLib/boardcss.css">
<body>
<h2>** Spring_Boot Mybatis Update Form **</h2>
<form action="update" method="post">
<table>
<c:set var="s" value="${requestScope.apple}"/>
<c:if test="${!empty requestScope.apple}">
<tr height="40">
		<td bgcolor="gray"><label for="id">ID</label></td>
		<td><input type="text" name="id" id="id" size="10" value="${loginID}" readonly></td>
	</tr>
	<tr height="40">
		<td bgcolor="gray" ><label for="seq">Seq</label></td>
		<td><input type="text" name="seq" id="seq" value="${requestScope.apple.seq}" readonly size="10"></td>
	</tr>
	<tr height="40">
		<td bgcolor="gray" ><label for="title">Title</label></td>
		<td><input type="text" name="title" id="title" value="${requestScope.apple.title}" size="50"></td>
	</tr>
	<tr height="150">
		<td bgcolor="gray"><label for="content">Content</label></td>
		<td><textarea rows ="5" cols="50" name="content">${requestScope.apple.content}</textarea></td>
	</tr>
	<tr height="40">
		<td bgcolor="gray" ><label for="cnt">조회수</label></td>
		<td><input type="text" name="cnt" id="cnt" value="${requestScope.apple.cnt}" readonly size="50"></td>
	</tr>
	
	<tr> <td></td>
		<td><input type= "submit" value="수정">&nbsp;&nbsp;
			<input type="reset" value="취소"> 
		</td>
		</c:if>
</table>
</form>
<br><hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>	

&nbsp;<a href="/home">[Home]</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">[이전으로]</a>&nbsp;

</body>
</html>