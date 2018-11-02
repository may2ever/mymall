package com.test.mymall.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.mymall.dao.ItemDao;
import com.test.mymall.service.ItemService;
import com.test.mymall.vo.Item;

@WebServlet("/ItemListController")
public class ItemListController extends HttpServlet {
	private ItemService itemService;
	private ItemDao itemDao;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("ItemListController.doGet()");
		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		itemService = new ItemService();
		HashMap<String, Integer> pagingInfo = new HashMap<String, Integer>();
		ArrayList<Item> itemList = itemService.getItemList(pagingInfo, currentPage, 10, 10);
		request.setAttribute("itemList", itemList);
		request.setAttribute("pagingInfo", pagingInfo);
		request.getRequestDispatcher("WEB-INF/views/itemList.jsp").forward(request, response);
	}

}
