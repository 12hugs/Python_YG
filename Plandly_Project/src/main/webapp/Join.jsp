<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="//code.jquery.com/jquery-3.7.0.min.js"></script>
</head>

<style>
.login-wrapper{
    width: 400px;
    height: 350px;
    padding: 40px;
    box-sizing: border-box;
}

.login-wrapper > h2{
    font-size: 24px;
    color: #6A24FE;
    margin-bottom: 20px;
}
#login-form > input{
    width: 100%;
    height: 48px;
    padding: 0 10px;
    box-sizing: border-box;
    margin-bottom: 16px;
    border-radius: 6px;
    background-color: #F8F8F8;
}
#login-form > input::placeholder{
    color: #D2D2D2;
}
#login-form > input[type="submit"]{
    color: #fff;
    font-size: 16px;
    background-color: #6A24FE;
    margin-top: 20px;
}

</style>

<body>
	<div class="login-wrapper">
        <h2>Login</h2>
        <form action="MemberJoin" method="post" id="login-form">
            <input type="text" name="email" placeholder="Email">
            <input type="password" name="pw" placeholder="Password">
            <label for="remember-check">
                <input type="checkbox" id="remember-check">아이디 저장하기
            </label>
            <input type="submit" value="Login">
        </form>
    </div>
    
		<a href="MemJoin.jsp">회원가입</a>
	
        <a href="javascript:void(0)" onclick="kakaoLogin();"> <span>카카오 로그인</span> </a>
        <a href="javascript:void(0)" onclick="kakaoLogout();"> <span>카카오 로그아웃</span> </a>
    
    <script>
    // 카카오 로그인
        Kakao.init('933b64ba38169aa59da21e60a71d4944'); 
        
        function kakaoLogin() {
            Kakao.Auth.login({
                scope : 'profile_nickname, account_email, gender, age_range',
                success : function(response) {
                    Kakao.API.request({
                        url : '/v2/user/me',
                        success : function(response) {
                            var responseData = JSON.stringify(response);

                            // Ajax를 이용해 post방식으로 값 넘기기
                            $.ajax({
                                type : 'POST',
                                url : 'JoinServlet', // Login이라는 매핑값에 값을 넘김
                                data : {
                                    userId : response.id, 
                                    email : response.kakao_account.email,
                                    age : response.kakao_account.age_range,
                                    gender : response.kakao_account.gender,
                                    nickName: response.properties.nickname,
                                },
                                success : function(result) {
                                    console.log(result);
                                    if (result.trim() === "SUCCESS") {  // trim() 메소드를 사용하여 공백 제거 후 비교
                                        location.href = "Main.jsp";  // 회원 가입 성공 시 Main.jsp로 페이지 이동
                                    } else {
                                        alert("회원 가입 실패");
                                        // 실패 시 처리할 로직 작성
                                    }
                                },
                                error : function(error) {
                                    console.log(error);
                                },
                            });
                        },
                        fail : function(error) {
                            console.log(error);
                        },
                    });
                },
                fail : function(error) {
                    console.log(error);
                },
            });
        }

	// 카카오 로그아웃
        function kakaoLogout() {
            if (Kakao.Auth.getAccessToken()) {
                Kakao.API.request({
                    url : '/v1/user/unlink',
                    success : function(response) {
                        console.log(response)
                    },
                    fail : function(error) {
                        console.log(error)
                    },
                })
                Kakao.Auth.setAccessToken(undefined)
            }
        }
    </script>

</body>
</html>