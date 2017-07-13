package com.dgit.service;

import java.util.List;

import com.dgit.domain.Criteria;
import com.dgit.domain.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> listReply(Integer bno) throws Exception;

	public void addReply(ReplyVO vo) throws Exception;

	public void modifyReply(ReplyVO vo) throws Exception;

	public void removeReply(Integer rno) throws Exception;

	public List<ReplyVO> listPage(Integer bno, Criteria cri) throws Exception;

	public int count(int bno) throws Exception;
}
