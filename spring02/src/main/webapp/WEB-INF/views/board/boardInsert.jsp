<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** 글쓰기 **</title>
</head>
<body>
<form action="insert" method="post">
<table>
	<tr height="40">
		<td bgcolor="aqua" ><label for="title">Title</label></td>
		<td><input type="text" name="title" id="title" placeholder="제목 필수 기입!" size="20"></td>
	</tr>
	<tr height="100">
		<td bgcolor="aqua"><label for="content">Content</label></td>
		<td><input type="text" name="content" id="content" size="80" height="100px"></td>
	</tr>
	
	<tr> <td></td>
		<td><input type= "submit" value="작성 완료">&nbsp;&nbsp;
			<input type="reset" value="취소"> 
		</td>
	</tr>
</table>
</form>
<br><hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>	

&nbsp;<a href="/spring02/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>