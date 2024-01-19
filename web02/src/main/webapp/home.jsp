<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** home **</title>
</head>
<body>
<h2>** Web02_MVC02 **</h2>

<c:choose>
<c:when test="${!empty sessionScope.loginName}">
<h3>${loginName}님 안녕하세요~~</h3>
</c:when>
<c:otherwise>
<h3>로그인후 이용 하세요~~</h3>
</c:otherwise>
</c:choose>


<hr>
	<img alt="" src="/web02/images/jerryInsa.gif" width="300" height="200">
	<img alt="" src="/web02/images/jerry04.gif" width="300" height="200">
	<hr>
	
<c:choose>
<c:when test="${!empty sessionScope.loginName}">
	&nbsp; <a href="/web02/mdetail">MyInfo</a>&nbsp;
	&nbsp; <a href="/web02/mdetail?jCode=U">내정보수정</a>&nbsp;
	&nbsp; <a href="/web02/logout">Logout</a>&nbsp;
	&nbsp; <a href="/web02/mdelete">탈퇴</a><br>
</c:when>
<c:otherwise>
&nbsp; <a href="/web02/member/loginForm.jsp">Login</a>&nbsp;
&nbsp; <a href="/web02/member/joinForm.jsp">Join</a><br>
</c:otherwise>
</c:choose>	
	&nbsp; <a href="/web02/mlist">MList</a> &nbsp;	
	
</body>
</html>