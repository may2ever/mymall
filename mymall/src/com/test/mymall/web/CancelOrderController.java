package com.test.mymall.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.mymall.service.MemberItemService;

@WebServlet("/CancelOrderController")
public class CancelOrderController extends HttpServlet {
	private MemberItemService memberItemService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CancelOrderController.doGet()");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			int memberItemNo = Integer.parseInt(request.getParameter("memberItemNo"));
			memberItemService = new MemberItemService();
			memberItemService.deleteMemberItem(memberItemNo);
		}
		response.sendRedirect(request.getContextPath() + "/IndexController");
	
		
	}

}
