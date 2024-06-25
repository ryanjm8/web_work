<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/validation/form.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h1>폼 유효성 검증 style 테스트</h1>
		<form action="signup.jsp" method="post">
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<!-- 
					is-valid 와 is-invalid 클래스를 input 요소에 조건부로 
					추가 혹은 제거 하면 style 이 변경된다.				
				 -->
				<input class="form-control is-valid" type="text" id="id" name="id" />
				<div class="valid-feedback">잘 입력했군!</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label>
				<input class="form-control is-invalid" type="text" id="email" name="email"/>
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요</div>
				<div class="valid-feedback">잘 입력 했군!</div>
			</div>
			<button class="btn btn-primary" type="submit">가입</button>
		</form>
	</div>
</body>
</html>