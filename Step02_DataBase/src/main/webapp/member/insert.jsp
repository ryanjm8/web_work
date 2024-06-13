<%@page import="test.member.dto.MemberDto"%>
<%@page import="javax.swing.text.html.parser.DTD"%>
<%@page import="test.member.dao.MemberDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	//1. 폼 전송되는 이름과 주소를 추출한다.
	String name=request.getParameter("name");
	String addr=request.getParameter("addr");
	//MemberDto 객체에 담는다.
	MemberDto dto = new MemberDto();
	dto.setName(name);
	dto.setAddr(addr);
	//2. DB 에 저장한다.	
	MemberDao dao = new MemberDao();
	boolean isSuccess=dao.insert(dto);
	//3. 응답한다	
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/member/insert.jsp</title>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h3>알림</h3>
		<%if(isSuccess){ %>
			<p class="alert alert-success">
				<strong><%=name %></strong> 님의 정보를 저장했습니다.
				<a class="alert-link" href="${pageContext.request.contextPath }/member/list.jsp"></a>
			</p>
		<%}else{%>
			<p class="alert alert-danger">
				회원 정보 저장 실패!
				<a class="alert-link" href="${pageContext.request.contextPath }/member/insertform.jsp"></a>			
			</p>		
		<%} %>
	</div>
</body>
</html>