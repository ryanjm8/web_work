
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/guest/insertform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<nav>
			<ol class="breadcrumb">
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/">Home</a></li>
				<li class="breadcrumb-item"><a href="${pageContext.request.contextPath }/guest/list.jsp">방명록</a></li>
				<li class="breadcrumb-item active">글추가</li>
			</ol>
		</nav>
		<h1>좋은 글을 남겨 주세요</h1>
		<form action="${pageContext.request.contextPath }/guest/insert.jsp" method="post">
			<div class="form-floating mb-3">
				<input class="form-control" placeholder="작성자 입력..." type="text" id="writer" name="writer" />
				<label for="writer">작성자</label>	
			</div>
			<div class="form-floating mb-3">
				<textarea class="form-control" placeholder="내용 입력..." style="height:200px;" name="content" id="content"></textarea>
				<label for="content">내용</label>
			</div>
			<div class="form-floating mb-3">
				<input class="form-control" placeholder="비밀번호 입력..." type="password" id="pwd" name="pwd"/>
				<label for="pwd">비밀번호</label>
			</div>
			<button class="btn btn-outline-success" type="submit">
				저장
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-floppy" viewBox="0 0 16 16">
				  <path d="M11 2H9v3h2z"/>
				  <path d="M1.5 0h11.586a1.5 1.5 0 0 1 1.06.44l1.415 1.414A1.5 1.5 0 0 1 16 2.914V14.5a1.5 1.5 0 0 1-1.5 1.5h-13A1.5 1.5 0 0 1 0 14.5v-13A1.5 1.5 0 0 1 1.5 0M1 1.5v13a.5.5 0 0 0 .5.5H2v-4.5A1.5 1.5 0 0 1 3.5 9h9a1.5 1.5 0 0 1 1.5 1.5V15h.5a.5.5 0 0 0 .5-.5V2.914a.5.5 0 0 0-.146-.353l-1.415-1.415A.5.5 0 0 0 13.086 1H13v4.5A1.5 1.5 0 0 1 11.5 7h-7A1.5 1.5 0 0 1 3 5.5V1H1.5a.5.5 0 0 0-.5.5m3 4a.5.5 0 0 0 .5.5h7a.5.5 0 0 0 .5-.5V1H4zM3 15h10v-4.5a.5.5 0 0 0-.5-.5h-9a.5.5 0 0 0-.5.5z"/>
				</svg>
			</button>
		</form>
	</div>
</body>
</html>
