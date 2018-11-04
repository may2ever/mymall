package com.test.mymall.service;

import org.apache.ibatis.session.SqlSession;
import com.test.mymall.commons.DBHelper;
import com.test.mymall.dao.MemberDao;
import com.test.mymall.dao.MemberItemDao;
import com.test.mymall.vo.Member;

public class MemberService {
	private MemberItemDao memberItemDao;
	private MemberDao memberDao;
	public void removeMember(int no) {
		SqlSession sqlSession = null;
		try {
			sqlSession = DBHelper.getSqlSession();
			memberItemDao = new MemberItemDao();
			memberDao = new MemberDao();
			memberItemDao.deleteMemberItem(sqlSession, no, true);
			memberDao.deleteMember(sqlSession, no);
			sqlSession.commit();
		}
		catch(Exception e) {
			sqlSession.rollback();
		}
		finally {
			sqlSession.close();
		}
	}
	public void addMember(Member member) {
		SqlSession sqlSession = null;
		memberDao = new MemberDao();
		try {
			sqlSession = DBHelper.getSqlSession();
			memberDao.insertMember(sqlSession, member);
			sqlSession.commit();
		}
		catch(Exception e) {
			sqlSession.rollback();
		}
		finally {
			sqlSession.close();
		}
	}
	public Member getMember(String id) {
		memberDao = new MemberDao();
		SqlSession sqlSession = null;
		Member member = null;
		try {
			sqlSession = DBHelper.getSqlSession();
			member = memberDao.selectMember(sqlSession, id);
		}
		catch(Exception e) {
			sqlSession.rollback();
		}
		finally {
			sqlSession.close();
		}
		return member;
	}
	public void modifyMember(Member member) {
		SqlSession sqlSession = null;
		memberDao = new MemberDao();
		try {
			sqlSession = DBHelper.getSqlSession();
			memberDao.modifyMember(sqlSession, member);
			sqlSession.commit();
 
		}
		catch(Exception e) {
			sqlSession.rollback();
		}
		finally {
			sqlSession.close();
		}
	}
	public Member loginMember(Member member) {
		SqlSession sqlSession = null;
		memberDao = new MemberDao();
		Member loginmember = null;
		try {
			sqlSession = DBHelper.getSqlSession();
			loginmember = memberDao.login(sqlSession, member);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			sqlSession.close();
		}
		return loginmember;
	}
}
