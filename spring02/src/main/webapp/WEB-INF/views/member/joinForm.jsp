<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** JoinForm **</title>
</head>
<body>
<form action="join" method="post">
<table>
	<tr height="40">
		<td bgcolor="aqua" ><label for="id">I D</label></td>
		<td><input type="text" name="id" id="id" placeholder="영문과 숫자로 4~10글자" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="password">Password</label></td>
		<td><input type="password" name="password" id="password" placeholder="특수문자 필수" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="name">Name</label></td>
		<td><input type="text" name="name" id="name" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="age">Age</label></td>
		<td><input type="text" name="age" id="age" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="jno">Jno</label></td>
		<td><select name="jno" id="jno">
			<option value="1">1조: Business</option>
			<option value="2">2조: static</option>
			<option value="3">3조: 칭찬해조</option>
			<option value="4">4조: 카톡으로얘기하조</option>
			<option value="7">7조: 칠면조(관리팀)</option>
		</select></td></tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="info">Info</label></td>
		<td><input type="text" name="info" id="info" placeholder="자기소개 & 희망사항" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="point">Point</label></td>
		<td><input type="text" name="point" id="point" placeholder="실수 입력" size="20"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="birthday">Birthday</label></td>
		<td><input type="date" name="birthday" id="birthday"></td>
	</tr>
	<tr height="40">
		<td bgcolor="aqua"><label for="rid">추천인</label></td>
		<td><input type="text" name="rid" id="rid" size="20"></td>
	</tr>
	
	
	<tr> <td></td>
		<td><input type= "submit" value="가입">&nbsp;&nbsp;
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