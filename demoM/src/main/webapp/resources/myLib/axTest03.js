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


 /* 엔터 이벤트 */
function enterKey() {
        if (window.event.keyCode == 13) {
        	searchDB();
        }
    }

function axiMList() {
	let url="/member/aximlist";
	axios.get(url
	).then(response => {
		console.log('** response : MemberList 성공 ')
		document.getElementById('resultArea1').innerHTML=response.data;
	}).catch(err => {
		alert(`** response : MemberList 실패 => ${err.message}`);
	});
	document.getElementById('resultArea2').innerHTML="";
}
//	1.2) idbList(id별 boardList)
//	=> RESTController, PathVariable처리, List_Data response
//	=> Server : service, sql 구문 작성
//	=> request : id를 path로 전송 "/rest/idblist/banana"
//	=> response
//	-> 성공 : 반복문, table로 List 출력문 완성, resultArea2에 출력
//	-> 출력자료의 유/무 : Server에서 status로 처리(없으면 502)
//	-> 실패 : resultArea2 clear, alert으로 에러 메세지 출력
function idbList(id) {
	let url="/rest/idblist/"+ id;
	axios.get(url
	).then(response => {
		alert("** 성공 => resultArea2에 List 작성 **");
		console.log("** result List Data => " + response.data);
		let listData = response.data;
		let resultHtml =
		`<table style="width:100%">
		<tr bgcolor="khaki">
		<th>Seq</th><th>Title</th><th>ID</th><th>RegDate</th><th>조회수</th>
		</tr>`;
		//	=> 반복문 적용
		
		for (let b of listData) {
			resultHtml += `
			<tr><td>${b.seq}</td><td>${b.title}</td>
			<td>${b.id}</td><td>${b.regdate}</td><td>${b.cnt}</td>
			</tr>`;
		}	//for
		resultHtml += `</table>`;
		document.getElementById('resultArea2').innerHTML=resultHtml;
	}).catch(err => {
		//	=> response의 status 값이 502면 출력자료 없음
		if(err.response.status=='502') {			
		document.getElementById('resultArea2').innerHTML
		= err.response.data;
		} else {			
		document.getElementById('resultArea2').innerHTML="";
		alert("** 시스템 오류, 잠시후에 다시 하세요 => " + err.message);
		}
	});
}
//	2.2) axidelete(${s.id})
//	=> 요청 : "rest/axidelete/id" PathVariable 적용
//	=> response: 성공/실패 여부만 전달받음, 그러므로 RESTController로
//	=> 성공 : Deleted로 변경, onclick 이벤트 해제
function axiDelete(id) {
	let url="/rest/axidelete/"+id;
	axios.delete(url
	).then(response => {
		alert(response.data);
		//	=> 삭제성공 : Deleted로 변경, onclick 이벤트 해제
		//	- Delete -> Deleted, Gray_color, Bold
		//	- onclick 이벤트 제거
		//	- Style 제거
		
	document.getElementById('id').innerHTML="Deleted";
	document.getElementById('id').style.color="gray";
	document.getElementById('id').style.fontWeight="bold";
	document.getElementById('id').classList.remove('textlink');
//	document.getElementById('id').removeEventListener(onclick,axiDelete);
	document.getElementById('id').removeAttribute('onclick');
	
	}).catch(err => {
		if(err.response.status=='502') 	alert(err.response.data)		
		 else alert("** 시스템 오류, 잠시후에 다시 하세요 => " + err.message);
	});
	
	
}	//axiDelete


function searchDB() {
	let url='axmcri'
        	     + '?currPage=1&rowsPerPage=5'
				 +'&searchType='+document.getElementById('searchType').value
				 +'&keyword='+document.getElementById('keyword').value;
			axiMListCri(url);
}

// => 2) searchType 을 '전체' 로 변경하면 keyword는 clear 
function keywordClear(){
   if ( document.getElementById('searchType').value=='all' )
      document.getElementById('keyword').value='';   
}

// => 3) axios Code
function axiMListCri(url) {
	url = "/member/" + url;
	alert(`axiMListCri url = ${url}`);
	axios.get(url
	).then(response => {
		console.log("** response 성공 **");
		document.getElementById('resultArea1').innerHTML=response.data;
		}).catch(err => {
		document.getElementById('resultArea1').innerHTML="axiMListCri " + err.message;
		});
		document.getElementById('resultArea2').innerHTML="";
} // axiMList

// => 4) Check 검색기능 추가
// => Check 검색 submit 을 Button(type 속성주의) 으로 변경
// => MemberController : axmcri 메서드 공유
// => 단, 조건 구분을 위해 요청명은 "/axmcheck"  
function axiMListCheck() {
   // => 첫요청 url 생성
   // url=/axmcheck?currPage=1&rowsPerPage=5&check=1&check=2&check=3
   
   let checkAll = document.querySelectorAll(".check");
   let checkData ="";
   for (let i=0; i<checkAll.length; i++) {
      if (checkAll[i].checked)
         checkData +="&check="+checkAll[i].value;
   }
   let url='axmcheck'
            +'?currPage=1&rowsPerPage=5'
            +checkData;
   axiMListCri(url); // axios 호출   
} //axiMListCheck

function checkClear() {

	let ck= document.querySelectorAll('.clear');
	for(let i = 0;i < ck.length;i++) {
		ck[i].checked=false;
	}
	return false;	//reset의 기본이벤트 제거
}