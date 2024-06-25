<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>test03.jsp</title>
</head>
<body>
	<button id="getBtn">회원정보 가져오기</button>
	<p>번호 : <strong id="num"></strong></p>
	<p>이름 : <strong id="name"></strong></p>
	<p>주소 : <strong id="addr"></strong></p>
	
	<button id="getBtn2">친구 목록 가져오기</button>
	<button id="getBtn3">친구 목록 가져오기2</button>
	<ul id="friend">
	
	</ul>
	<script>
		document.querySelector("#getBtn3").addEventListener("click", ()=>{
		
			fetch("${pageContext.request.contextPath }/friends.jsp")
			.then(res=>res.json())
			.then((data)=>{
				console.log(data);
				//data 는 친구이름 여러개가 들어 있는 배열이다.
				data.forEach((item)=>{				
					// html 형식의 문자열을 구성하고 
					const li= `<li>\${item}</li>`;
					// 원하는 위치에 실체 HTML 로 해석을 시키면서 추가하기
					document.querySelector("#friend").insertAdjacentHTML("beforeend", li);
				});
			});
		
		});
		
		
		/*
			친구 목록 가져오기 버튼을 눌렀을때 friends.jsp 페이지로 fetch() 요청을 하고
			응답되는 데이터를 이용해서 친구이름을 ul 에 li 요소를 이용해서 목록 출력해 보세요.
		*/
		document.querySelector("#getBtn2").addEventListener("click", ()=>{
			
			fetch("friends.jsp")
			.then(res=>res.json())
			.then((data)=>{
				console.log(data);
				//data 는 친구이름 여러개가 들어 있는 배열이다.
				data.forEach((item)=>{					
					//item 은 친구 이름이다.
					const li = document.createElement("li"); // li 요소를 만들어서 					
					li.innerText = item; // innerText 에 친구 이름을 출력하고 					
					document.querySelector("#friend").append(li); // ul 의 자식요소로 추가
				});
			});
			
		});
	
	
		/*
			회원 정보 가져오기 버튼을 눌렀을때 member.jsp 페이지로 fetch() 요청을 하고 
			응답되는 데이터를 이용해서 위에 회원의 번호, 이름, 주소를 출력해 보세요
		*/
		document.querySelector("#getBtn").addEventListener("click", ()=>{
			fetch("${pageContext.request.contextPath }/member.jsp")
			.then(res=>res.json())
			.then((data)=>{
				console.log(data);
				document.querySelector("#num").innerText = data.num;
				document.querySelector("#name").innerText = data.name;
				document.querySelector("#addr").innerText = data.addr;
			});
		});
		
		
	</script>
</body>
</html>



