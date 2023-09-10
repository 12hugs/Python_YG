<%@page import="com.plandly.controller.Lifecount"%>
<%@page import="com.plandly.model.Plandly_MemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery-3.7.0.min.js"></script>
</head>

<body>
	<%
		Plandly_MemberVO vo = (Plandly_MemberVO)session.getAttribute("vo");
		String age = vo.getAge();
		System.out.println(age);
		Lifecount watch = new Lifecount();
		String lifeWatch = watch.counting(age);
		System.out.println(lifeWatch);
	%>

	<span id="MyTimer"></span>

	<script type="text/javascript">
        function startTimer(duration, display) {
            var timer = duration, days, hours, minutes, seconds;
            var interval = setInterval(function() {
                days = Math.floor(timer / (24 * 60 * 60));
                hours = Math.floor((timer % (24 * 60 * 60)) / (60 * 60));
                minutes = Math.floor((timer % (60 * 60)) / 60);
                seconds = Math.floor(timer % 60);

                days = days < 10 ? "0" + days : days;
                hours = hours < 10 ? "0" + hours : hours;
                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;

                display.textContent =
                    days + "일 " +
                    hours + "시 " +
                    minutes + "분 " +
                    seconds + "초";

                if (--timer < -1) { // -1로 수정
                    clearInterval(interval); // 타이머 종료
                }
            },1000);
        }

        window.onload = function() {
            var lifeWatchStr= "<%= lifeWatch %>";
            var lifeWatchArr= lifeWatchStr.split("-");
            console.log(lifeWatchArr);

            // 주어진 날짜와 시간을 초 단위로 변환
            var totalSeconds=
              ((parseInt(lifeWatchArr[0])*(1000*24*3600))+
               (parseInt(lifeWatchArr[1])*(1000*3600))+
               (parseInt(lifeWatchArr[2])*(1000*60))+
               parseInt(lifeWatchArr[3]))/1000;

           startTimer(totalSeconds, document.querySelector('#MyTimer'));
        };
    </script>

</body>
</html>