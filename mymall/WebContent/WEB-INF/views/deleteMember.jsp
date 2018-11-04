<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원삭제</title>
</head>
<body>
	<h1>비밀번호 확인</h1>
	<div>
		<form action="DeleteMemberController" method ="post">
			<input type="password" name ="pw" />
			<input type="submit" value = "삭제"/>
		</form>
	</div>
</body>
</html>