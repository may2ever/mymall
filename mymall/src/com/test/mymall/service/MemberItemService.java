package com.test.mymall.service;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.test.mymall.commons.DBHelper;
import com.test.mymall.dao.MemberItemDao;
import com.test.mymall.vo.MemberItem;

public class MemberItemService {
	private MemberItemDao memberItemDao;
	public void addMemberItem(MemberItem memberItem) {
		SqlSession sqlSession = null;
		try {
			memberItemDao = new MemberItemDao();
			sqlSession = DBHelper.getSqlSession();
			memberItemDao.insertMemberItem(sqlSession, memberItem);
			sqlSession.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				sqlSession.rollback();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		finally {
			sqlSession.close();
		}
		
	}
	public List<HashMap<String, Object>> memberItemList(int memberNO) {
		SqlSession sqlSession = null;
		List<HashMap<String, Object>> list = null;
		try {
			sqlSession = DBHelper.getSqlSession();
			memberItemDao = new MemberItemDao();
			list = memberItemDao.getMemberItemList(sqlSession, memberNO);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		return list;
	}
	public List<HashMap<String, Object>> totalMemberItemList() {
		SqlSession sqlSession = null;
		List<HashMap<String, Object>> list = null;
		try {
			sqlSession = DBHelper.getSqlSession();
			memberItemDao = new MemberItemDao();
			list = memberItemDao.getTotalMemberItemList(sqlSession);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		return list;
	}
	public void deleteMemberItem(int memberItemNo) {
		SqlSession sqlSession = null;
		memberItemDao = new MemberItemDao();
		try {
			sqlSession = DBHelper.getSqlSession();
			memberItemDao.deleteMemberItem(sqlSession, memberItemNo, false);
			sqlSession.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			try {
				sqlSession.rollback();
			}
			catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		finally {
			sqlSession.close();
		}
		
	}
}
