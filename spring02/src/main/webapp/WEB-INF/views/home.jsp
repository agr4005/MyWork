<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
		<link rel="stylesheet" type="text/css"
	 href="/spring02/resources/myLib/boardcss2.css">
	 <style>
</style>
</head>
<body>
<h2>** Hello Spring_MVC02 !!! **</h2>
<P>* Home_time: ${serverTime}. </P>
<hr>

<c:if test="${!empty sessionScope.loginName}">
	${sessionScope.loginName}님 안녕하세요 ~~<br>
</c:if>
  <c:if test="${!empty requestScope.message}" >
       ${requestScope.message}<br>
   </c:if>
<c:if test="${empty sessionScope.loginID && empty requestScope.message}">
	로그인 후 이용하세요 ~~<br>
</c:if>
   
   <hr>
	<img alt="mainImage" src="/spring02/resources/images/jerry01.gif" width="300" height="200">
	<hr>
	
	<!-- Login 전 -->
	<div class="back color-8">
    <div class="row columns">
	<c:if test="${empty sessionScope.loginID}">
	 <ul class="menu align-center expanded text-center SMN_effect-8">
      <li><a href="member/loginForm" data-hover="LoginF"><span>LoginF</span></a>
      </li>
       <li><a href="member/joinForm" data-hover="JoinF"><span>JoinF</span></a>
       </li>
	</ul>    
<!-- 	&nbsp; <a href="member/loginForm" >LoginF</a> &nbsp;	
	&nbsp; <a href="member/joinForm" >JoinF</a> &nbsp; -->
	</c:if>
	</div>
	</div> 
	<!-- Login 후 -->
	<div class="back color-8">
    <div class="row columns">
	<c:if test="${!empty sessionScope.loginID}">
       <ul class="menu align-center expanded text-center SMN_effect-8">
        <li><a href="member/detail?jCode=D" data-hover="About"><span>내정보</span></a></li>
        <li><a href="member/detail?jCode=U" data-hover="Gallery"><span>내정보수정</span></a></li>
        <li><a href="member/logout" data-hover="Notes"><span>Logout</span></a></li>
        <li><a href="member/delete" data-hover="Contact"><span>탈퇴</span></a></li>
      </ul>
<!-- 	&nbsp; <a href="member/detail?jCode=D" >내정보</a> &nbsp;	
	&nbsp; <a href="member/detail?jCode=U" >내정보수정</a> &nbsp;	
	&nbsp; <a href="member/logout" >Logout</a> &nbsp;
	&nbsp; <a href="member/delete" >탈퇴</a> &nbsp;	 -->
	</c:if>
	</div>
	</div>
	<br><hr>
	<div class="back color-8">
    <div class="row columns">
	 <ul class="menu align-center expanded text-center SMN_effect-8">
        <li><a href="member/memberList" data-hover="MList"><span>MList</span></a></li>
        <li><a href="jo/joList" data-hover="JList"><span>JList</span></a></li>
        <li><a href="board/boardList" data-hover="BList"><span>BList</span></a></li>
        <li><a href="bcrypt" data-hover="BCrypt"><span>BCrypt</span></a></li>
        <li><a href="board/bPageList" data-hover="BPage"><span>BPage</span></a></li>
        <li><a href="member/mPageList" data-hover="MPage"><span>MPage</span></a></li>
      </ul>
      </div>
      </div>
<!-- 	&nbsp; <a href="member/memberList" >MList</a> &nbsp;	
	&nbsp; <a href="jo/joList" >JList</a> &nbsp;	
	&nbsp; <a href="board/boardList" >BList</a> &nbsp;		
	&nbsp; <a href="bcrypt" >BCrypt</a> <br>	
	&nbsp; <a href="board/bPageList" >BPage</a> &nbsp;	
	&nbsp; <a href="member/mPageList" >MPage</a> &nbsp;	 -->
	
	
</body>
</html>
