<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
        <h2>JOIN</h2>
	    	<form action="MemberJoin" method="post">
		        <label for="email">이메일:</label>
		        <input type="email" id="email" name="email"><br>
		
		        <label for="password">비밀번호:</label>
		        <input type="password" id="pw" name="pw" required><br>
		
		        <label for="birthdate">생년월일:</label>
		        <!-- 사용자가 날짜를 직접 입력할 수 있도록 input 타입을 date로 설정 -->
		        <!-- min과 max 속성으로 날짜 범위를 제한할 수 있음 -->
		        <!-- 여기서는 1900년부터 오늘까지의 범위로 설정함 -->
		        <input type="date" id="age" name="age"
		               min="1900-01-01"
		               max="<?php echo date('Y-m-d'); ?>" required><br>
		
		        <label for="">성별:</label>
		        <!-- 라디오 버튼으로 남자와 여자만 선택할 수 있도록 함 -->
				<input type=radio id=male name=gender value=male checked=true>남자
				<input type=radio id=female name=gender value=female>여자<br>
		
			    <label for="">닉네임:</label>
			    <!-- 닉네임은 텍스트 필드로 입력 받음 -->
			    <input type=text id=nickName name=nickName required><br>
		
			    <!-- 회원 가입 버튼 -->
			    <button type=submit>회원 가입</button>
			</form>
    </div>
</body>
</html>