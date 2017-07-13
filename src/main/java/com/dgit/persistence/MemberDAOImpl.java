package com.dgit.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dgit.domain.MemberVO;

@Repository // DAO
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	private SqlSession sqlSession;

	private static final String namespace = "com.dgit.mappers.MemberMapper";

	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	@Override
	public void insertMember(MemberVO vo) {
		sqlSession.insert(namespace + ".insertMember", vo);
	}

	@Override
	public MemberVO readMember(String userid) throws Exception {
		return sqlSession.selectOne(namespace + ".selectMember", userid);
	}

	@Override
	public MemberVO login(String userid, String userpw) throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("userid", userid);
		paramMap.put("userpw", userpw);
		return sqlSession.selectOne(namespace + ".login", paramMap);
	}

	@Override
	public List<MemberVO> selectAll() throws Exception {
		return sqlSession.selectList(namespace + ".selectAll");
	}

}
