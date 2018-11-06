package com.test.mymall.service;


import java.util.ArrayList;
import java.util.HashMap;
import org.apache.ibatis.session.SqlSession;
import com.test.mymall.commons.DBHelper;
import com.test.mymall.dao.ItemDao;
import com.test.mymall.vo.Item;
import com.test.mymall.vo.PageMarker;

public class ItemService {
	private ItemDao itemDao;
	public ArrayList<Item> getItemList(PageMarker pageMaker) {
		ArrayList<Item> itemList = null;
		SqlSession sqlSession = null;
		itemDao = new ItemDao();
		try {
			sqlSession = DBHelper.getSqlSession();
			itemList = (ArrayList<Item>)itemDao.selectItemList(sqlSession, pageMaker.getPageNum(), pageMaker.getContentNum());
			int totalCount = itemDao.getTotalItemCount(sqlSession);
			pageMaker.setTotalCount(totalCount);
			pageMaker.executePaging();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		return itemList;
	}
}
