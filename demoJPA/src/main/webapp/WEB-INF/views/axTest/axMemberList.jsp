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
				=> a Tag에 이벤트 적용시 책갈피 기능 활용 가능
					-> href : 적용하지않음 (이동하지 않음)
					-> href="#id" : id 위치로 이동 / "#" : 최상단으로 이동
					-> href="javascript:;" : 이동하지 않음
			-->
			<tr><td><a href="#resultArea2" onclick="idbList('${s.id}')">${s.id}</a>
<%-- 			<span class="textlink" onclick="idbList( '${s.id}' )">${s.id}</span>--%>			
			</td>
			<td>${s.name}</td><td align="center">${s.age}</td>
			<!-- ** Jo 정보 Div에 출력 -->
			<td align="center">
				<span class="textlink" onmouseover="showJoDetail(event, ${s.jno})" 
										onmouseout="hideJoDetail()">${s.jno}</span></td>
			<td>${s.info}</td><td>${s.point}</td><td>${s.birthday}</td><td>${s.rid}</td>
			<td><img alt="myImage" width="50" height="70"
			src="/resources/uploadImages/${s.uploadfile}"></td>
			   <!--  ** Delete 기능 추가 
            => 선택된 id를 function 에 전달 (매개변수를 활용)
            => 결과는 성공/실패 여부만 전달: RESTController 로 
            => 성공 : Deleted 로 변경, onclick 이벤트 해제 
                     이를 위해 Delete Tag 를 function 에서 인식할수있어야함. 
                     
            ** function 에 이벤트객체 전달
            => 이벤트핸들러의 첫번째 매개변수에 event 라는 이름으로 전달함.
             => a Tag 와 span 사용시 e.target 값 비교
                -> a Tag : "javascript:;" 
                -> span  : [object HTMLSpanElement]          
         -->
			 <td><span class="textlink" onclick="axiDelete(event,'${s.id}')" id="${s.id}">Delete</span></td>
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
<div id="content"></div>
<hr>
&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;
</body>
</html>