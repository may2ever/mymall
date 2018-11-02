package com.test.mymall.dao;


import org.apache.ibatis.session.SqlSession;
import com.test.mymall.vo.Member;

public class MemberDao {
	/** 
	 * 폼에서 입력한 멤버의 정보를 데이터베이스에 등록
	 * 
	 * @param Member 폼에서 입력한 멤버의 정보(id,pw,level)
	 */
	public void insertMember(SqlSession sqlSession, Member member) {
		sqlSession.insert("com.test.mymall.dao.MemberMapper.insertMember", member);
	}
	//id와 pw값을 가지는 member객체를 이용해 로그인체크를 하는 메서드
	public Member login(SqlSession sqlSession, Member member) {
		return sqlSession.selectOne("com.test.mymall.dao.MemberMapper.loginMember",member);
	}
	/**
	 * 데이터베이스에서 로그인된 회원의 id에 해당하는 회원정보를 가져온다
	 * 
	 * @param	id	로그인된 회원의 id
	 * @return	회원의정보(no,id,pw,level)
	 */
	public Member selectMember(SqlSession sqlSession, String id) {
		return sqlSession.selectOne("com.test.mymall.dao.MemberMapper.selectMember", id);
	}
    /**
     * 매개변수로전달된 로그인한 회원의 수정된 데이터(member)를 데이터베이스에서 갱신
     *
     * @param   member   로그인한 회원의 수정된 데이터(pw,level)
     * @return  없음
     */
	public void modifyMember(SqlSession sqlSession, Member member) {
		sqlSession.update("com.test.mymall.dao.MemberMapper.modifyMember", member);
	}
    /**
     * 현재 로그인되어있는 회원의 정보를 데이터베이스에서 삭제
     *
     * @param	데이터베이스 연결에 필요한 객체
     * @return  없음
     */
	public void deleteMember(SqlSession sqlSession, int no) {
		sqlSession.delete("com.test.mymall.dao.MemberMapper.deleteMember",no);
	}
	
	
}
