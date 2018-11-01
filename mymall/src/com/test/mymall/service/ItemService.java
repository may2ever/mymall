package com.test.mymall.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import com.test.mymall.commons.DBHelper;
import com.test.mymall.dao.ItemDao;
import com.test.mymall.vo.Item;

public class ItemService {
	private ItemDao itemDao;
	public ArrayList<Item> getItemList(HashMap<String, Integer> pagingInfo, int currentPage, int rowPerPage, int pagePerScreen) {
		int totalCount;
		int currentScreen; //현재 화면 번호
		int lastScreen; //마지막 화면 번호
		int currentScreenPage; //현재 화면에 보이는 페이지의 개수
		int startScreenPage; //현재 화면에 보이는 페이지의 시작 번호(첫번째화면 1,두번째화면 11..)
		int lastPage; //마지막 페이지번호
		Connection connection = null;
		ArrayList<Item> itemList = null;
		itemDao = new ItemDao();
		try {
			connection = DBHelper.getConnection();
			itemList = itemDao.selectItemList(connection, currentPage, rowPerPage);
			totalCount = itemDao.getTotalItemCount(connection);
			lastPage = (int)Math.ceil((double) totalCount / rowPerPage);
			currentScreen = (int)Math.ceil((double) currentPage / rowPerPage);
			lastScreen = (int) Math.ceil((double) totalCount / (rowPerPage * pagePerScreen));
			startScreenPage = (currentScreen - 1) * pagePerScreen;
			if(currentScreen == lastScreen) {
				if(totalCount % (rowPerPage * pagePerScreen) != 0) { //마지막 화면에 보이는 리스트 개수(rowPerPage * pagePerScreen)가 100개이면 totalCount % (rowPerPage * pagePerScreen)은 0이 되기때문에 pagePerScreen 값을 넣어주어야 한다
					int temp = totalCount % (rowPerPage * pagePerScreen);
					currentScreenPage = (int) Math.ceil((double) temp / rowPerPage);
				}
				else {
					currentScreenPage = pagePerScreen;
				}
			}
			else {
				currentScreenPage = pagePerScreen;
			}
			pagingInfo.put("currentPage", currentPage);
			pagingInfo.put("lastPage", lastPage);
			pagingInfo.put("currentScreen", currentScreen);
			pagingInfo.put("lastScreen", lastScreen);
			pagingInfo.put("pagePerScreen", pagePerScreen);
			pagingInfo.put("currentScreenPage", currentScreenPage);
			pagingInfo.put("startScreenPage", startScreenPage);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, null, connection);
		}

		return itemList;
	}
}
