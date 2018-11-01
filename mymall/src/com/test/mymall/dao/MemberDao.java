package com.test.mymall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.test.mymall.commons.DBHelper;
import com.test.mymall.vo.Member;

public class MemberDao {
	/** 
	 * 폼에서 입력한 멤버의 정보를 데이터베이스에 등록
	 * 
	 * @param Member 폼에서 입력한 멤버의 정보(id,pw,level)
	 */
	public void insertMember(Connection connection, Member member) throws SQLException{
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement("insert into member(id,pw,level) values(?,?,?)");
		preparedStatement.setString(1, member.getId());
		preparedStatement.setString(2, member.getPw());
		preparedStatement.setInt(3, member.getLevel());
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	//id와 pw값을 가지는 member객체를 이용해 로그인체크를 하는 메서드
	public Member login(Connection connection, Member member) throws SQLException{
		//로그인 실패시 -> null 
		//로그인 성공시 -> 성공한 Member객체
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		preparedStatement = connection.prepareStatement("SELECT no, id, level from member where id = ? and pw = ?");
		preparedStatement.setString(1, member.getId());
		preparedStatement.setString(2, member.getPw());
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			member.setNo(resultSet.getInt(1));
			member.setId(resultSet.getString(2));
			member.setLevel(resultSet.getInt(3));
			member.setPw("");
		}
		else {
			member = null;
		}
		resultSet.close();
		preparedStatement.close();
		return member;
	}
	/**
	 * 데이터베이스에서 로그인된 회원의 id에 해당하는 회원정보를 가져온다
	 * 
	 * @param	id	로그인된 회원의 id
	 * @return	회원의정보(no,id,pw,level)
	 */
	public Member selectMember(Connection connection,String id) throws SQLException{
		System.out.println("MemberDao.selectMember()");
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Member member = new Member();
		preparedStatement = connection.prepareStatement("SELECT no, id, pw, level from member WHERE id = ?");
		preparedStatement.setString(1, id);
		resultSet = preparedStatement.executeQuery();
		if(resultSet.next()) {
			member.setNo(resultSet.getInt(1));
			member.setId(resultSet.getString(2));
			member.setPw(resultSet.getString(3));
			member.setLevel(resultSet.getInt(4)); 
		}
		resultSet.close();
		preparedStatement.close();
		return member;
	}
    /**
     * 매개변수로전달된 로그인한 회원의 수정된 데이터(member)를 데이터베이스에서 갱신
     *
     * @param   member   로그인한 회원의 수정된 데이터(pw,level)
     * @return  없음
     */
	public void modifyMember(Connection connection,Member member) throws SQLException{
		PreparedStatement preparedStatement = null;
		preparedStatement = connection.prepareStatement("UPDATE member SET pw = ?, level = ? WHERE id = ?");
		preparedStatement.setString(1, member.getPw());
		preparedStatement.setInt(2, member.getLevel());
		preparedStatement.setString(3, member.getId());
		preparedStatement.executeUpdate();
		preparedStatement.close();

	}
	// 회원탈퇴
	public void deleteMember(Connection connection, int no) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM member WHERE no = ?");
		preparedStatement.setInt(1, no);
		preparedStatement.executeUpdate();
		preparedStatement.close();
	}
	
	
}
