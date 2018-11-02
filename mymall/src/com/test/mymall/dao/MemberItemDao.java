package com.test.mymall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import com.test.mymall.commons.DBHelper;
import com.test.mymall.vo.MemberItem;

public class MemberItemDao {
	/**
	 * 주문시 주문정보(주문한 회원의 번호,물품번호,주문일자)를 데이터베이스에 등록
	 * 
	 * @return 없다
	 * @param memberItem 주문정보(주문한 회원의 번호(member_no),물품번호(item_no)
	 */
	public void insertMemberItem(Connection connection, MemberItem memberItem) throws SQLException{
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement("INSERT INTO member_item(member_no, item_no, order_date) VALUES(?, ?, now() )");
		preparedStatement.setInt(1, memberItem.getMember_no());
		preparedStatement.setInt(2, memberItem.getItem_no());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	/**
	 * 현재 로그인되있는 회원의 가입번호를 통해 그 회원의 주문정보를 데이터베이스에서 얻어온다.
	 * 
	 * @return 주문정보(member_item)의 리스트
	 * @param memberNO 현재 로그인되있는 회원의 가입번호
	 */
	public ArrayList<HashMap<String, Object>> getMemberItemList(Connection connection, int memberNO) throws SQLException{
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;		
		preparedStatement = connection.prepareStatement("SELECT mi.no, mi.item_no, i.name, i.price, mi.order_date FROM member_item mi inner join item i ON mi.item_no = i.no WHERE mi.member_no = ?");
		preparedStatement.setInt(1, memberNO);
		resultSet =  preparedStatement.executeQuery();
		while(resultSet.next()) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("memberItemNo", resultSet.getInt(1));
			map.put("itemNO", resultSet.getInt(2));
			map.put("itemName", resultSet.getString(3));
			map.put("itemPrice", resultSet.getInt(4));
			map.put("orderDate", resultSet.getString(5));
			list.add(map);
		}
		resultSet.close();
		preparedStatement.close();
		return list;
	}
	/**
	 * 회원번호 or 주문번호에 따라 주문목록의 데이터를 데이터베이스에서 삭제
	 * 
	 * @param 데이터베이스 연결을 위한 객체
	 * @param 회원번호 or 주문번호
	 * @param 두번째 매개변수가 회원번호인경우 true
	 * @return 없음
	 */
	public void deleteMemberItem(Connection connection, int no, boolean isMemberNo)  throws SQLException {
		String query;
		if(isMemberNo) {
			query = "DELETE FROM member_item WHERE member_no = ?";
		}
		else {
			query = "DELETE FROM member_item WHERE no = ?";
		}
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, no);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
}
