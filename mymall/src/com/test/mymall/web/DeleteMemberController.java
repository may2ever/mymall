package com.test.mymall.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.test.mymall.service.MemberService;
import com.test.mymall.vo.Member;

@WebServlet("/DeleteMemberController")
public class DeleteMemberController extends HttpServlet {
	private MemberService memberService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteMemberController.doGet()");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			request.getRequestDispatcher("/WEB-INF/views/deleteMember.jsp").forward(request, response);;
		}
		else {
			response.sendRedirect(request.getContextPath() + "/IndexController");
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DeleteMemberController.doPost()");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			String pw = request.getParameter("pw");
			memberService = new MemberService();
			Member member = (Member)session.getAttribute("loginMember");
			member.setPw(pw);
			member = memberService.loginMember(member);
			if(member != null) {
				memberService.removeMember(member.getNo());
				session.invalidate();
			}

		}
		response.sendRedirect(request.getContextPath() + "/IndexController");

	}
}
