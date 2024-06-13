<%@page import="test.member.dto.MemberDto"%>
<%@page import="test.member.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 폼전송되는 수정할 회원의 번호, 이름, 주소를 추출한다.
	int num=Integer.parseInt(request.getParameter("num"));
	String name=request.getParameter("name");
	String addr=request.getParameter("addr");		
	//2. DB 에 수정반영한다.
	MemberDto dto=new MemberDto(num, name, addr); 
	boolean isSuccess = new MemberDao().update(dto);
	//3. 응답하기 
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/update.jsp</title>
</head>
<body>
	<script>
		/*
			javascript 를 로딩시켜서 Redirect 응답한 효과 내기
			location.href = "이동할경로";
		*/
		
		<%if(isSuccess){%>
			alert("수정 했습니다.");
			location.href="${pageContext.request.contextPath }/member/list.jsp";
		<%}else{%>
		 	alert("수정 실패!");
		 	location.href="${pageContext.request.contextPath }/member/list.jsp?num=<%=num%>";
		<%}%>
	</script>
</body>
</html>