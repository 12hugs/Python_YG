package com.plandly.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.plandly.model.Plandly_MemberDAO;
import com.plandly.model.Plandly_MemberVO;

public class MemberJoin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String nickName = request.getParameter("nickName");

		System.out.println(email);
		System.out.println(pw);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(nickName);
		
		// 중복 체크
		Plandly_MemberVO vo = new Plandly_MemberVO(email, pw, age, gender, nickName);
		Plandly_MemberDAO dao = new Plandly_MemberDAO();

		int cnt = dao.login(vo);

		if (cnt > 0) {
			System.out.println("이미 가입된 회원이므로 세션을 드리겠습니다.");
			
			// selectList를 통해 vo 재할당하고 session값에 넣어주기.
			List<Plandly_MemberVO> list = dao.memberSession(vo);
			email = list.get(0).getEmail();
			pw = list.get(0).getPw();
			age = list.get(0).getAge();
			gender = list.get(0).getGender();
			nickName = list.get(0).getNickName();
			vo = new Plandly_MemberVO(email, pw, age, gender, nickName);
			HttpSession session = request.getSession();
			session.setAttribute("vo", vo);
			response.sendRedirect("Main.jsp"); // 추후 메인페이지로 이동
			return;
		}

		// 회원가입 로직 진행
		int joinCnt = dao.join(new Plandly_MemberVO(email, pw, age, gender, nickName));

		if (joinCnt > 0) {
			System.out.println("회원가입 완료"); // 회원가입 완료 후 세션을 주고 메인페이지로 이동
			
			HttpSession session = request.getSession();
			session.setAttribute("vo", vo);
			response.sendRedirect("Main.jsp"); // 추후 메인페이지로 이동
			return;
		} else {
			System.out.println("회원가입 실패");
			
			response.sendRedirect("Join.jsp");
			return;
		}


	}

}
