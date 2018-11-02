package com.test.mymall.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.test.mymall.commons.DBHelper;
import com.test.mymall.dao.MemberDao;
import com.test.mymall.dao.MemberItemDao;
import com.test.mymall.vo.Member;

public class MemberService {
	private MemberItemDao memberItemDao;
	private MemberDao memberDao;
	public void removeMember(int no) {
		Connection connection = null;
		try {
			connection = DBHelper.getConnection();
			connection.setAutoCommit(false); //자동커밋 방지
			memberItemDao = new MemberItemDao();
			memberDao = new MemberDao();
			memberItemDao.deleteMemberItem(connection, no, true);
			memberDao.deleteMember(connection,no);
			connection.commit();
		}
		catch(Exception e) {
			try {
				e.printStackTrace();
				connection.rollback();
			}
			catch(SQLException e1) {
				e1.printStackTrace();
			}
		}
		finally {
			DBHelper.close(null, null, connection);
		}
	}
	public void addMember(Member member) {
		Connection connection = null;
		memberDao = new MemberDao();
		try {
			connection = DBHelper.getConnection();
			memberDao.insertMember(connection, member);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, null, connection);
		}
	}
	public Member getMember(String id) {
		Connection connection = null;
		Member member = null;
		memberDao = new MemberDao();
		try {
			connection = DBHelper.getConnection();
			member = memberDao.selectMember(connection, id);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, null, connection);
		}
		return member;
	}
	public void modifyMember(Member member) {
		Connection connection = null;
		memberDao = new MemberDao();
		try {
			connection = DBHelper.getConnection();
			memberDao.modifyMember(connection, member);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, null, connection);
		}
	}
	public Member loginMember(Member member) {
		Connection connection = null;
		memberDao = new MemberDao();
		try {
			connection = DBHelper.getConnection();
			memberDao.login(connection, member);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			DBHelper.close(null, null, connection);
		}
		return member;
	}
}
