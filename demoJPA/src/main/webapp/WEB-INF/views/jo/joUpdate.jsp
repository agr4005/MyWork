<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Mybatis Update Form **</title>
<link rel="stylesheet" type="text/css"
	 href="/resources/myLib/boardcss2.css">
</head>
<body>
<h2>** Spring_Boot Mybatis Update Form **</h2>
<form action="update" method="post">
<table>
<c:set var="s" value="${requestScope.apple}"/>
<c:if test="${!empty requestScope.apple}">
	<tr height="40">
		<td bgcolor="gray" ><label for="jno">Jno</label></td>
		<td><input type="text" name="jno" id="jno" value="${requestScope.apple.jno}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="gray"><label for="jname">Jname</label></td>
		<td><input type="text" name="jname" id="jname" value="${requestScope.apple.jname}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="gray"><label for="captain">Captain</label></td>
		<td><input type="text" name="captain" id="captain" value="${requestScope.apple.captain}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="gray"><label for="project">Project</label></td>
		<td><input type="text" name="project" id="project" value="${requestScope.apple.project}" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="gray"><label for="slogan">Slogan</label></td>
		<td><input type="text" name="slogan" id="slogan" value="${requestScope.apple.slogan}" size="20"></td>
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