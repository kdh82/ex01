package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgit.domain.Criteria;
import com.dgit.domain.ReplyVO;
import com.dgit.persistence.BoardDAO;
import com.dgit.persistence.ReplyDAO;


@Service
public class ReplyServiceImpl implements ReplyService{
	
	@Autowired
	private ReplyDAO dao;
	
	@Autowired
	private BoardDAO boardDao;
	
	
	
	@Override
	public List<ReplyVO> listReply(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(bno);
	}
	@Transactional
	@Override
	public void addReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.create(vo);
		boardDao.updateReplyCnt(vo.getBno(), 1);
	}

	@Override
	public void modifyReply(ReplyVO vo) throws Exception {
		// TODO Auto-generated method stub
		dao.update(vo);
	}

	@Override
	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listPage(bno, cri);
	}

	@Override
	public int count(int bno) throws Exception {
		// TODO Auto-generated method stub
		return dao.count(bno);
	}

	@Transactional
	@Override
	public void removeReply(Integer rno) throws Exception {
		// TODO Auto-generated method stub
		int bno =dao.getBno(rno);
		dao.delete(rno);		
		boardDao.updateReplyCnt(bno, -1);
		
	}

}
