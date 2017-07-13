package com.dgit.persistence;

import java.util.List;

import com.dgit.domain.MemberVO;

public interface MemberDAO {
	public String getTime();
	public void insertMember(MemberVO vo);
	public MemberVO readMember(String userid) throws Exception;
	public MemberVO login(String userid, String userpw) throws Exception;
	public List<MemberVO> selectAll() throws Exception;
}
