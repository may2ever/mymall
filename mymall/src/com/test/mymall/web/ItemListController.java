package com.test.mymall.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.mymall.service.ItemService;
import com.test.mymall.vo.Item;
import com.test.mymall.vo.PageMarker;

@WebServlet("/ItemListController")
public class ItemListController extends HttpServlet {
	private ItemService itemService;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("ItemListController.doGet()");
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		itemService = new ItemService();
		PageMarker pageMaker = new PageMarker(currentPage, 10, 5);
		ArrayList<Item> itemList = itemService.getItemList(pageMaker);
		request.setAttribute("itemList", itemList);
		request.setAttribute("pageMaker", pageMaker);
		request.getRequestDispatcher("WEB-INF/views/itemList.jsp").forward(request, response);
	}

}
