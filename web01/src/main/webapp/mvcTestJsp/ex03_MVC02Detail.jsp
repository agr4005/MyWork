<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
tr {
	text-align: center;
}
</style>
<title>** MyInfo **</title>
</head>
<body>
	<h2>** MyInfo **</h2>


	<table style="width: 200px; height: 300px;">

		<c:if test="${!empty sessionScope.myInfo}">
			<tr>
			<td bgcolor="DeepSkyBlue">${"sno"}</td>
				<td>${sessionScope.myInfo.sno}</td>
			</tr>
			<tr>
			<td bgcolor="DeepSkyBlue">${"name"}</td>
				<td>${sessionScope.myInfo.name}</td>
			</tr>
			<tr>
			<td bgcolor="DeepSkyBlue">${"age"}</td>
				<td>${sessionScope.myInfo.age}</td>
			</tr>
			<tr>
			<td bgcolor="DeepSkyBlue">${"jno"}</td>
				<td>${sessionScope.myInfo.jno}</td>
			</tr>
			<tr>
			<td bgcolor="DeepSkyBlue">${"info"}</td>
				<td>${sessionScope.myInfo.info}</td>
			</tr>
			<tr>
			<td bgcolor="DeepSkyBlue">${"point"}</td>
				<td>${sessionScope.myInfo.point}</td>
			</tr>
		</c:if>
		<c:if test="${empty sessionScope.myInfo}">
			<tr>
				<td colspan="6">
					<h3>출력할 자료가 없습니다.</h3>
				</td>
			</tr>
		</c:if>
	</table>
	<hr>
	<h3>
		<a href='javascript:history.go(-1)'>이전으로</a>
	</h3>



</body>
</html>