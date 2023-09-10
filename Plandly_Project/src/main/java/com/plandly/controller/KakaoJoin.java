package com.plandly.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plandly.model.Plandly_MemberDAO;
import com.plandly.model.Plandly_MemberVO;


//@WebServlet("/JoinServlet")
public class KakaoJoin extends HttpServlet {
	public static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String pw = request.getParameter("userId");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String nickName = request.getParameter("nickName");

		
		if (age.equals("10~19")) {
			age = "2008-01-01";
		} else if (age.equals("20~29")) {
			age = "1998-01-01";
		} else if (age.equals("30~39")) {
			age = "1988-01-01";
		} else if (age.equals("40~49")) {
			age = "1978-01-01";
		} else if (age.equals("50~59")) {
			age = "1968-01-01";
		} else if (age.equals("60~69")) {
			age = "1958-01-01";
		}

		System.out.println("아이디 : " + email);
		System.out.println("비밀번호(고유ID값) : " + pw);
		System.out.println("나이대 : " + age);
		System.out.println("성별 : " + gender);
		System.out.println("닉네임 : " + nickName);

		// 중복 체크
		Plandly_MemberVO vo = new Plandly_MemberVO(email, pw, age, gender, nickName);
		Plandly_MemberDAO dao = new Plandly_MemberDAO();

		int cnt = dao.login(vo);

		if (cnt > 0) {
			System.out.println("이미 가입된 회원이므로 세션을 드리겠습니다.");
			HttpSession session = request.getSession();
			session.setAttribute("vo", vo);
			
			response.getWriter().write("SUCCESS"); // 추후 메인페이지로 이동
			
			System.out.println("왜 안돼?");
			return;
		}

		// 회원가입 로직 진행
		int joinCnt = dao.join(new Plandly_MemberVO(email, pw, age, gender, nickName));

		if (joinCnt > 0) {
			System.out.println("회원가입 완료"); // 회원가입 완료 후 세션을 주고 메인페이지로 이동
			HttpSession session = request.getSession();
			session.setAttribute("vo", vo);
			
			response.getWriter().write("SUCCESS"); // 추후 메인페이지로 이동
			return;
		} else {
			System.out.println("회원가입 실패");
			response.getWriter().write("FAIL");
			return;
		}


	}
}
