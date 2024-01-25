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

   <c:if test="${!empty requestScope.message}" >
      <hr><h4> ${ requestScope.message } <hr></h4>
   </c:if>
   
	<img alt="" src="resources/images/jerry01.gif" width="300" height="200">
	<img alt="" src="resources/images/jerry01.gif" width="300" height="200">
	<hr>
	
	&nbsp; <a href="mlist">MList</a> &nbsp;	
	&nbsp; <a href="mdetail">MDetail</a> &nbsp;	
	&nbsp; <a href="mlistsp">MListSp</a> &nbsp;	
	&nbsp; <a href="mdetailsp">MDetailSp</a> &nbsp;	

</body>
</html>
