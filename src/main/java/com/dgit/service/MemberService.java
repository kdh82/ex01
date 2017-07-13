package com.dgit.service;

import java.util.List;

import com.dgit.domain.MemberVO;

public interface MemberService {
	
	public void createMember(MemberVO vo) throws Exception;
	public MemberVO readMember(String userid) throws Exception;
	public void updateMember(MemberVO vo) throws Exception;
	public void deleteMember(String userid) throws Exception;
	public MemberVO login(String userid, String userpw) throws Exception;

}
