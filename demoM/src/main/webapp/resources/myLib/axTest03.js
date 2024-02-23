// ** Ajax_REST API, Axios Test **
// => Axios 메서드형식 적용
// => 1. List 출력
//   - axiMList : MemberController, Page response
//   - idbList(id별 boardList) : RTestController, List_Data response 
// => 2. 반복문에 이벤트 적용하기
//   - Delete, JoDetail
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// 1. List 출력 
// 1.1) Page response
// => response를 resultArea1에 출력하기
// 요청명 : /member/aximlist
// => response(axMemberList.jsp) 

"use strict"

function axiMList() {
	let url="/member/aximlist";
	axios.get(url
	).then(response => {
		console.log('** response : MemberList 성공 ')
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err => {
		alert(`** response : MemberList 실패 => ${err/message}`);
	});
	document.getElementById('resultArea2').innerHTML="";
}

function axiMpage() {
	let url="/member/aximPage";
	axios.get(url
	).then(response => {
		console.log('** response : MemberPage 성공 ')
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err => {
		alert(`** response : MemberPage 실패 => ${err/message}`);
	});
	document.getElementById('resultArea2').innerHTML="";
}



