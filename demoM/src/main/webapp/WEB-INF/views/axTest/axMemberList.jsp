<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Axios MemberList **</title>
<link rel="stylesheet" type="text/css"
	 href="/resources/myLib/boardcss.css">
</head>
<body>
<h2>** Spring_Boot Axios MemberList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<table border="1" style="width:100%">
<tr bgcolor="hotpink">
			<th>ID</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th>
			<th>Point</th><th>Birthday</th><th>추천인</th><th>Image</th><th>Delete</th>
			<!-- <th>Password</th> -->
		</tr>
		
		<c:if test="${!empty requestScope.banana}">
			<c:forEach var="s" items="${requestScope.banana}">
			<!-- ** - idbList(id별 boardList) 
				=> 선택된 id를 function에 전달 (매개변수를 활용)
				   idbList( 'banana' )
			-->
			<tr><td><span class="textlink" onclick="idbList( '${s.id}' )">${s.id}</span></td>
			<td>${s.name}</td><td>${s.age}</td><td>${s.jno}</td>
			<td>${s.info}</td><td>${s.point}</td><td>${s.birthday}</td><td>${s.rid}</td>
			<td><img alt="myImage" width="50" height="70"
			src="/resources/uploadImages/${s.uploadfile}"></td>
			<!-- Delete 기능 추가 
				=> 선택된 id를 function에 전달 (매개변수를 활용)
				=> 결과는 성공/실패 여부만 전달: RESTController로 
				=> 성공 : Deleted로 변경, onclick 이벤트 해제 -->
			 <td><span class="textlink" onclick="axiDelete(${s.id})" id="${s.id}">Delete</span></td>
			</tr>
			</c:forEach>
		</c:if>
	<c:if test="${empty requestScope.banana}">
		<tr>
		<td colspan="10">
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