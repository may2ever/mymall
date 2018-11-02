package com.test.mymall.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.test.mymall.vo.Item;

public class ItemDao {
	/**
	 * 현재 페이지에서 한 페이지에 보여지는 개수만큼 물품정보를 데이터베이스에서 가져온다
	 * 
	 * @return 물품정보(Item)의 리스트
	 * @param currentPage 현재 보여지는 페이지의 번호
	 * @param rowPerPage 한 페이지에 보여지는 아이템의 개수
	 */
	public List<Item> selectItemList(SqlSession sqlSession, int currentPage,int rowPerPage) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("currentPage", (currentPage-1)*rowPerPage);
		map.put("rowPerPage", rowPerPage);
		return sqlSession.selectList("com.test.mymall.dao.ItemMapper.selectItemList",map);
	}
	/**
	 * 데이터베이스에서 물품의 총 개수를 얻어온다
	 * 
	 * @return 물품의 총 개수
	 */
	public int getTotalItemCount(SqlSession sqlSession) throws SQLException{
		return sqlSession.selectOne("com.test.mymall.dao.ItemMapper.totalItemCount");
	}
}
