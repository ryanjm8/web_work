<%@page import="test.member.dto.MemberDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// request 영역에 "dto" 라는 키값으로 저장된 String type 얻어내기
	MemberDto dto = (MemberDto)request.getAttribute("dto");
%>   
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/test/member.jsp</title>
</head>
<body>	
	<h3>회원 한명의 정보 입니다</h3>
	<p> 번호 : <strong><%=dto.getNum() %></strong></p>
	<p> 이름 : <strong><%=dto.getName() %></strong></p>
	<p> 주소 : <strong><%=dto.getAddr() %></strong></p>	
	
	<%--
		EL 을 이용하면 dto 의 필드에 있는 값을 편하게 출력할수 있다.
		
		${dto.getNum() } or ${dto.num}
		
		위와 같이 필드명만 적어도 자동으로 dto 의 getter 메소드를 호출해 준다.
	 --%>
	
	<h3>EL 을 이용하면</h3>
	<p> 번호 : <strong>${dto.num }</strong></p>
	<p> 이름 : <strong>${dto.name  }</strong></p>
	<p> 주소 : <strong>${dto.addr }</strong></p>
	
</body>
</html>