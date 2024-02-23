<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>** Spring_Boot Mybatis MemberPageList **</title>
<link rel="stylesheet" type="text/css"
	 href="/resources/myLib/boardcss2.css">
<script>
"use strict"
//1. 검색조건 입력후 버튼클릭
//=> 입력값들을 서버로 전송 요청처리:  location

 /* 엔터 이벤트 */
function enterKey() {
        if (window.event.keyCode == 13) {
        	searchDB();
        }
    }
// ** self.location   
// 1) location 객체 직접사용 Test : url로 이동, 히스토리에 기록됨
// 2) location 객체의 메서드
// => href, replace('...'), reload() 

function searchDB() {
	self.location='mPageList'
        	     + '?currPage=1&rowsPerPage=5'
				 +'&searchType='+document.getElementById('searchType').value
				 +'&keyword='+document.getElementById('keyword').value;
}

//	2. searchType을 '전체'로 변경하면 keyword는 clear
function keywordClear() {
	if (document.getElementById('searchType').value=='all')
		document.getElementById('keyword').value ='';
}

//	** Member Check_List
function checkClear() {
	
	// document.querySelectorAll('.clear').checked=false;
	//	=> nodeList를 반환하기 때문에 적용안됨
	let ck= document.querySelectorAll('.clear');
	
	for(let i = 0;i < ck.length;i++) {
		ck[i].checked=false;
	}
	return false;	//reset의 기본이벤트 제거
} // checkClear
// ** querySelector
// => css 선택자를 이용하여 첫번째 만난 요소 1개만 선택

// ** querySelectorAll 
// => css 선택자를 이용하여 해당하는 nodeList 를 반환
// =>  ","를 사용하면 여러 요소를 한번에 가져올 수 있음
//     querySelectorAll("#id,.class");
// => 그러므로 반복문과 이용됨.

</script>
</head>
<body>
<h2>** Spring_Boot Mybatis MemberPageList **</h2>
<hr>
<c:if test="${!empty requestScope.message}">
=> ${requestScope.message}<br><hr>
</c:if>
<div id="searchBar">
	<select name="searchType" id="searchType" onchange="keywordClear()">
	<option value="all" ${pageMaker.cri.searchType =='all' ? 'selected' : ''}>전체</option>
	<option value="id" ${pageMaker.cri.searchType =='id' ? 'selected' : ''}>I D</option>
	<option value="name" ${pageMaker.cri.searchType =='name' ? 'selected' : ''}>이 름</option>
	<option value="age" ${pageMaker.cri.searchType =='age' ? 'selected' : ''}>연 령</option>
	<option value="info" ${pageMaker.cri.searchType =='info' ? 'selected' : ''}>정 보</option>
	<option value="birthday" ${pageMaker.cri.searchType =='birthday' ? 'selected' : ''}>생년월일</option>
	<option value="rid" ${pageMaker.cri.searchType =='rid' ? 'selected' : ''}>추천인</option>
	</select>
	<input type="text" name="keyword" id="keyword" value="${pageMaker.cri.keyword}" placeholder="검색어 입력" onkeypress="enterKey()">
	<button id="searchBtn" onclick="searchDB()">Search</button>
	<hr>
	<!-- CheckBox Test -->
	<form action="mCheckList" method="get">
		<b>조 번호 : </b>
		<!-- check 의 선택한 값 유지를 위한 코드 -->
      <c:set var="ck1" value="false" />
      <c:set var="ck2" value="false" />
      <c:set var="ck3" value="false" />
      <c:set var="ck4" value="false" />
      <c:set var="ck5" value="false" />
      <c:forEach  var="id" items="${pageMaker.cri.check}" >
         <c:if test="${jno==1}"> <c:set var="ck1" value="true" /> </c:if>
         <c:if test="${jno==2}"> <c:set var="ck2" value="true" /> </c:if>
         <c:if test="${jno==3}"> <c:set var="ck3" value="true" /> </c:if>
         <c:if test="${jno==4}"> <c:set var="ck4" value="true" /> </c:if>
         <c:if test="${jno==7}"> <c:set var="ck5" value="true" /> </c:if>
      </c:forEach>
      <!-- --------------------------------- -->	
		<input type="checkbox" name="check" class="clear" value="1" ${ck1 ? 'checked':''}>Business&nbsp;
		<input type="checkbox" name="check" class="clear" value="2"${ck2 ? 'checked':''}>static&nbsp;
		<input type="checkbox" name="check" class="clear" value="3"${ck3 ? 'checked':''}>칭찬해조&nbsp;
		<input type="checkbox" name="check" class="clear" value="4"${ck4 ? 'checked':''}>카톡조&nbsp;
		<input type="checkbox" name="check" class="clear" value="7"${ck5 ? 'checked':''}>칠면조&nbsp;
		<input type="submit" value="Search">&nbsp;
		<input type="reset" value="Clear" onclick="return checkClear()"><br><hr>
	</form>
	
</div>
<br>
<table border="1" style="width:100%">
<tr bgcolor="DeepSkyBlue">
			<th>ID</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th>
			<th>Point</th><th>Birthday</th><th>추천인</th><th>Image</th>
			<!-- <th>Password</th> -->
		</tr>
		
		<c:if test="${!empty requestScope.banana}">
			<c:forEach var="s" items="${requestScope.banana}">
			<tr>
			<td>${s.id}</td><td>${s.name}</td><td>${s.age}</td><td>${s.jno}</td>
			<td>${s.info}</td><td>${s.point}</td><td>${s.birthday}</td><td>${s.rid}</td>
			<td><img alt="myImage" width="50" height="70"
			src="/resources/uploadImages/${s.uploadfile}"></td>
			</tr>
			</c:forEach>
		</c:if>
	<c:if test="${empty requestScope.banana}">
		<tr>
		<td colspan="9">
		<h3>~~ 출력할 자료가 없습니다. ~~</h3>
		</td>
		</tr>
	</c:if>
</table>
<br>
<div align="center">
    <c:choose>
      <c:when test="${pageMaker.prev && pageMaker.spageNo > 1}">
      		<a href="${pageMaker.searchQuery(1)}">&#9664;</a>&nbsp;
      		<a href="${pageMaker.searchQuery(pageMaker.spageNo-1)}">&#8678;</a>&nbsp;&nbsp;
      </c:when>
      <c:otherwise>
     	<font color="Gray">&#9664;&nbsp;&#8678;&nbsp;&nbsp;</font>
   	  </c:otherwise>    
     </c:choose>
     
	<!-- 2) PageNo -->
	<c:forEach var="i" begin="${pageMaker.spageNo}" end="${pageMaker.epageNo}">
		<c:if test="${i == pageMaker.cri.currPage}">
			<font color="Orange" size="5"><b>${i}</b></font>&nbsp;
		</c:if>
		<c:if test="${i != pageMaker.cri.currPage}">
			<a href="${pageMaker.searchQuery(i)}">${i}</a>&nbsp;
		</c:if>
	</c:forEach>

<!-- 3) Next, LastPage  -->
	<c:choose>
		<c:when test="${pageMaker.next && pageMaker.epageNo > 0}">
			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.epageNo+1)}">&#8680;</a>
			&nbsp;<a href="${pageMaker.searchQuery(pageMaker.lastPageNo)}">&#9654;</a>
		</c:when>
		<c:otherwise>
			<font color="Gray">&nbsp;&#8680;&nbsp;&nbsp;&#9654;</font>
		</c:otherwise>
	</c:choose>	

</div>
<br>
<hr>

&nbsp;<a href="/home">Home</a>&nbsp;
&nbsp;<a href="javascript:history.go(-1)">이전으로</a>&nbsp;

</body>
</html>