<%@page import="test.guest.dao.GuestDao"%>
<%@page import="test.guest.dto.GuestDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//1. 수정할 글번호를 읽어온다.
	int num=Integer.parseInt(request.getParameter("num"));
	//2. 글번호에 해당하는 글정보를 DB 에서 얻어온다.
	GuestDto dto=GuestDao.getInstance().getData(num);
	//3. 글 수정 양식을 응답한다.
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/">Home</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/guest/list.jsp">방명록목록</a></li>
				<li class="breadcrumb-item active">방명록수정</li>
			</ol>
		</nav>
		<h1>방명록 글 수정 폼</h1>
		<form action="${pageContext.request.contextPath }/guest/update.jsp" method="post">
			<div>
				<label for="num">번호</label>
				<input type="text" id="num" name="num" value="<%=dto.getNum() %>" readonly/>
			</div>
			<div>
				<label for="writer">작성자</label>
				<input type="text" id="writer" name="writer" value="<%=dto.getWriter()%>"/>
			</div>
			<div>
				<label for="content">내용</label>
				<textarea id="content" name="content" rows="5"><%=dto.getContent() %></textarea>
			</div>
			<div>
				<label for="pwd">글 작성시 입력했던 비밀번호</label>
				<input type="password" id="pwd" name="pwd"/>
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">취소</button>
		</form>
	</div>	
</body>
</html>