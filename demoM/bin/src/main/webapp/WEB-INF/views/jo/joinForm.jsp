<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Mybatis JoinForm **</title>
<link rel="stylesheet" type="text/css"
	 href="/resources/myLib/boardcss2.css">
</head>
<body>
<form action="join" method="post">
<table>
	<tr height="40">
		<td bgcolor="aqua" ><label for="jno">Jno</label></td>
		<td><input type="text" name="jno" id="jno" placeholder="숫자만 입력!" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="jname">Jname</label></td>
		<td><input type="text" name="jname" id="jname" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="captain">Captain</label></td>
		<td><input type="text" name="captain" id="captain" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="project">Project</label></td>
		<td><input type="text" name="project" id="project" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="slogan">Slogan</label></td>
		<td><input type="text" name="slogan" id="slogan" size="20"></td>
	</tr>
	
	<tr> <td></td>
		<td><input type= "submit" value="추가">&nbsp;&nbsp;
			<input type="reset" value="취소"> 
		</td>
	</tr>
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