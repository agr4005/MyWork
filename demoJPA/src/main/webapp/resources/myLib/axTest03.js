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

//	=> event 객체 적용하기
//	=> document.getElementById(id) 대신 e.target으로 이벤트 발생 대상 객체 인식 가능
function axiDelete(e, id) {
	let url="/rest/axidelete/"+id;
	axios.delete(url
	).then(response => {
		alert(response.data);
		//	=> 삭제성공 : Deleted로 변경, onclick 이벤트 해제
		//	- Delete -> Deleted, Gray_color, Bold
		//	- onclick 이벤트 제거
		//	- Style 제거
		
	// document.getElementById(id).innerHTML="Deleted";
	// e.targer 적용
	
	e.target.innerHTML="Deleted";
	document.getElementById(id).style.color="gray";
	document.getElementById(id).style.fontWeight="bold";
	document.getElementById(id).classList.remove('textlink');
	document.getElementById('id').removeEventListener(onclick,axiDelete);
	//document.getElementById(id).removeAttribute(onclick);
	
	}).catch(err => {
		if(err.response.status=='502') 	alert(err.response.data)		
		 else alert("** 시스템 오류, 잠시후에 다시 하세요 => " + err.message);
	});
	return url;
}	//axiDelete

//	2.3) JoDetail
//	2.3.1) MouseOver : showJoDetail
//	=> jno mouseover : JoDetail content Div에 출력 (마우스 포인터 위치에)
//	=> request : axios, get, RESTController에 "/jodetail" 요청
//	=> response: 성공시 JoDTO객체 

function showJoDetail(e, jno) {
   // ** 마우스포인터 위치 확인
   // => 이벤트객체 활용
   //     - event객체 (이벤트핸들러 첫번째 매개변수) 가 제공
   //     - event객체 프로퍼티: type, target, preventDefault() 등 (JS 9장_Event.pptx 28p)   
   //    - e.pageX, e.pageY : 전체 Page 기준
   //     - e.clientX, e.clientY : 보여지는 화면 기준-> page Scroll 시에 불편함
	console.log(`** e.pageX = ${e.pageX}, e.pageY = ${e.pageY}`);
	console.log(`** e.clientX = ${e.clientX}, e.clientY = ${e.clientY}`);
	
	let url="/rest/jodetail/" + jno;
	axios.get(url
	).then(response => {		
	console.log(`** response 성공 => ${response.data}`);
	let jo=response.data;
	
	// ** JSON.stringify 적용 비교
      let jj =JSON.stringify(response.data);   
      // => Object -> JSON : Data를 나열해줌 
      // => JS 객체포맷을 JSON 포맷화 하면 key:value 형태로 나열해줌
      //    (즉, 하나의 긴문자열, 문자Type 이됨)
      //    console.log 로 response.data 의 내용을 확인할때 사용하면 편리함.  
      console.log(`** response 성공: JSON포맷 => ${jj}`);
	
	console.log(jo.jno);
	let resultHTML = `
	<tr height="10"><td>Jno: </td><td>${jo.jno}</td></tr><br>
	<tr height="10"><td>JoName: </td><td>${jo.jname}</td></tr><br>
	<tr height="10"><td>Project: </td><td>${jo.project}</td></tr><br>
	<tr height="10"><td>Captain: </td><td>${jo.captain}</td></tr><br>
	<tr height="10"><td>Slogan: </td><td>${jo.slogan}</td></tr>
	`
	document.getElementById('content').innerHTML=resultHTML;
	document.getElementById('content').style.display='block';
	document.getElementById('content').style.left=e.pageX+"px";
	document.getElementById('content').style.top=e.pageY+"px";
	
	 // ** for 간편출력 : of, in
      // => in: undifined 는 통과하고, 배열(index Return), 객체(속성명 Return)
      // => of: undifined 까지 모두출력 (순차출력과 동일), value 를 return, 
      //        ES6 에 for ~ in 의 단점을 보완 개선하여 추가됨.
      //        일반 객체에는 적용안되지만, (오류발생, 개발자모드로 확인가능)
      //         Array, String, Map, Set, function의 매개변수 객체 와
      //        이터러블 규약을 따르는 이터러블 객체 (Iterable Object) 는 적용됨
      // => 이터러블 규약
      //      내부에 Symbol.iterator (줄여서 @@iterator로 표현하기도함) 메서드가 구현되어 있어야 한다는 규약
	
	}).catch(err => {
		if(err.response.status=='502') 	alert(err.response.data)		
		 else alert("** 시스템 오류, 잠시후에 다시 하세요 => " + err.message);
	})
	
} //showJoDetail

//	2.3.2) MouseOut : hideJoDetail
//	=> 화면에 표시되어 있던 content Div가 사라짐
function hideJoDetail() {
	document.getElementById('content').style.display='none';
} //hideJoDetail

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