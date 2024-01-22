<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h2>** Hello Spring !!! **</h2>

<P>  The time on the server is ${serverTime}. </P>
<hr>
<c:choose>
<c:when test="${!empty sessionScope.loginName}">
<h3>${loginName}님 안녕하세요~~</h3>
</c:when>
<c:otherwise>
<h3>로그인후 이용 하세요~~</h3>
</c:otherwise>
</c:choose>
   <c:if test="${!empty requestScope.message}" >
      <hr><h4> ${ requestScope.message } <hr></h4>
   </c:if>
	<img alt="" src="resources/images/jerry01.gif" width="300" height="200">
	<img alt="" src="resources/images/jerry01.gif" width="300" height="200">
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
	&nbsp; <a href="mlist">MList</a> &nbsp;	
	&nbsp; <a href="mdetail">MDetail</a> &nbsp;	
	&nbsp; <a href="mlistsp">MListSp</a> &nbsp;	
	&nbsp; <a href="mdetailsp">MDetailSp</a> &nbsp;	

</body>
</html>
