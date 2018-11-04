package com.test.mymall.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.mymall.service.MemberItemService;
import com.test.mymall.vo.Member;

@WebServlet("/OrderListController")
public class OrderListController extends HttpServlet {
	private MemberItemService memberItemService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("OrderListController.doGet()");
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			List<HashMap<String, Object>> list = null;
			memberItemService = new MemberItemService();
			Member member = (Member)session.getAttribute("loginMember");
			if(member.getLevel() == 1) {
				list = memberItemService.totalMemberItemList();
			}
			else {
				int memberNO = Integer.parseInt(request.getParameter("memberNO"));
				list = memberItemService.memberItemList(memberNO);
				
			}
			request.setAttribute("memberItemList", list);
			request.getRequestDispatcher("/WEB-INF/views/orderList.jsp").forward(request, response);
		}	
	}

}
