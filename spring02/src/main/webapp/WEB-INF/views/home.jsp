<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Hello Spring_MVC02</title>
		<link rel="stylesheet" type="text/css"
	 href="/spring02/resources/myLib/boardcss2.css">
	 <style>
</style>
</head>
<body>
<div id="container"><h1 class="style-1">Hello Spring_MVC02</h1></div>
<div id="container"><h1 class="style-1">${serverTime}</h1></div>
<hr>

<c:if test="${!empty sessionScope.loginName}">
	<div id="container"><h1 class="style-2">${sessionScope.loginName}님 안녕하세요 ~~<br></h1></div>
</c:if>
  <c:if test="${!empty requestScope.message}" >
       ${requestScope.message}<br>
   </c:if>
<c:if test="${empty sessionScope.loginID && empty requestScope.message}">
<div id="container"><h1 class="style-2">로그인 후 이용하세요 ~~<br></h1></div>
</c:if>
   
   <hr>
	<img alt="mainImage" src="/spring02/resources/images/jerry01.gif" width="300" height="200">
	<hr>
	
	<!-- Login 전 -->
	<c:if test="${empty sessionScope.loginID}">
	 <div class="menu align-center expanded text-center SMN_effect-8 style="display: flex">
      <a href="member/loginForm" data-hover="LoginF" style="width: 120"><span style="width: 120">LoginF</span></a>
       <a href="member/joinForm" data-hover="JoinF"style="width: 120"><span style="width: 120">JoinF</span></a>
	</div>    
<!-- 	&nbsp; <a href="member/loginForm" >LoginF</a> &nbsp;	
	&nbsp; <a href="member/joinForm" >JoinF</a> &nbsp; -->
	</c:if>	 
	<!-- Login 후 -->

	<c:if test="${!empty sessionScope.loginID}">
       <div class="menu align-center expanded text-center SMN_effect-8 style="display: flex">
        <a href="member/detail?jCode=D" data-hover="내정보" style="width: 120"><span style="width: 120">내정보</span></a>
        <a href="member/detail?jCode=U" data-hover="정보 수정" style="width: 120"><span style="width: 120">내정보수정</span></a>
        <a href="member/logout" data-hover="로그아웃" style="width: 120"><span style="width: 120">Logout</span></a>
        <a href="member/delete" data-hover="회원탈퇴" style="width: 120"><span style="width: 120">탈퇴</span></a>
      </div>
<!-- 	&nbsp; <a href="member/detail?jCode=D" >내정보</a> &nbsp;	
	&nbsp; <a href="member/detail?jCode=U" >내정보수정</a> &nbsp;	
	&nbsp; <a href="member/logout" >Logout</a> &nbsp;
	&nbsp; <a href="member/delete" >탈퇴</a> &nbsp;	 -->
	</c:if>

	<br><hr>
	 <div class="menu align-center expanded text-center SMN_effect-8" style="display: flex">
        <a href="member/memberList" data-hover="MList" style="width: 120"><span style="width: 120">MList</span></a>&nbsp;
        <a href="jo/joList" data-hover="JList" style="width: 120"><span style="width: 120">JList</span></a>&nbsp;
       <a href="board/boardList" data-hover="BList" style="width: 120"><span style="width: 120">BList</span></a>&nbsp;
       <a href="bcrypt" data-hover="BCrypt" style="width: 120"><span style="width: 120">BCrypt</span></a>&nbsp;
       </div>
       <div class="menu align-center expanded text-center SMN_effect-8" style="display: flex">
       <a href="board/bPageList" data-hover="BPage" style="width: 120"><span style="width: 120">BPage</span></a>&nbsp;
       <a href="member/mPageList" data-hover="MPage" style="width: 120"><span style="width: 120">MPage</span></a>&nbsp;
       <a href="etest" data-hover="Exception" style="width: 120"><span style="width: 120">Exception</span></a>&nbsp;
       <a href="member/log4jTest" data-hover="log4j" style="width: 120"><span style="width: 120">Log4j</span></a><br>
       </div>
       <div class="menu align-center expanded text-center SMN_effect-8" style="display: flex">
       <a href="greensn" data-hover="greenSN" style="width: 120"><span style="width: 120">GreenSN</span></a>&nbsp;
       <a href="greenall" data-hover="greenALL" style="width: 120"><span style="width: 120">GreenALL</span></a>&nbsp;
       <a href="jeju" data-hover="Jeju" style="width: 120"><span style="width: 120">Jeju</span></a>&nbsp;
       <a href="gps" data-hover="GPS" style="width: 120"><span style="width: 120">GPS</span></a>&nbsp;
       <hr>
      </div>
<!-- 	&nbsp; <a href="member/memberList" >MList</a> &nbsp;	
	&nbsp; <a href="jo/joList" >JList</a> &nbsp;	
	&nbsp; <a href="board/boardList" >BList</a> &nbsp;		
	&nbsp; <a href="bcrypt" >BCrypt</a> <br>	
	&nbsp; <a href="board/bPageList" >BPage</a> &nbsp;	
	&nbsp; <a href="member/mPageList" >MPage</a> &nbsp;	 -->
	
	
</body>
</html>
