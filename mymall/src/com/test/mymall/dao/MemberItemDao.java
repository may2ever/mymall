package com.test.mymall.dao;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.test.mymall.vo.MemberItem;

public class MemberItemDao {
	/**
	 * 주문시 주문정보(주문한 회원의 번호,물품번호,주문일자)를 데이터베이스에 등록
	 * 
	 * @return 없다
	 * @param memberItem 주문정보(주문한 회원의 번호(member_no),물품번호(item_no)
	 */
	public void insertMemberItem(SqlSession sqlSession, MemberItem memberItem) {
		sqlSession.insert("com.test.mymall.dao.MemberItemMapper.insertMemberItemList",memberItem);
	}
	/**
	 * 현재 로그인되있는 회원의 가입번호를 통해 그 회원의 주문정보를 데이터베이스에서 얻어온다.
	 * 
	 * @return 주문정보(member_item)의 리스트
	 * @param memberNO 현재 로그인되있는 회원의 가입번호
	 */
	public List<HashMap<String, Object>> getMemberItemList(SqlSession sqlSession, int memberNO) {
		return sqlSession.selectList("com.test.mymall.dao.MemberItemMapper.selectMemberItemList",memberNO);
	}
	public List<HashMap<String, Object>> getTotalMemberItemList(SqlSession sqlSession) {
		return sqlSession.selectList("com.test.mymall.dao.MemberItemMapper.selectTotalMemberItemList");
	}
	/**
	 * 회원번호 or 주문번호에 따라 주문목록의 데이터를 데이터베이스에서 삭제
	 * 
	 * @param 데이터베이스 연결을 위한 객체
	 * @param 회원번호 or 주문번호
	 * @param 두번째 매개변수가 회원번호인경우 true
	 * @return 없음
	 */
	public void deleteMemberItem(SqlSession sqlSession, int no, boolean isMemberNO) {
		if(isMemberNO) {
			sqlSession.delete("com.test.mymall.dao.MemberItemMapper.deleteMemberItemFromMemberNO",no);
		}
		else {
			sqlSession.delete("com.test.mymall.dao.MemberItemMapper.deleteMemberItemFromNO",no);
		}
	}
}
