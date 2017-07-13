package com.dgit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.SearchCriteria;
import com.dgit.persistence.BoardDAO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardDAO dao;
	
	@Transactional     //커낵션 안닫히게 하기 create 입력하다 뻑나면 롤백까지해줌
	@Override
	public void regist(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		dao.create(board);
		
		if(board.getFiles() == null)
			return;
		for(String fullname : board.getFiles()){
		dao.addAttach(fullname); //스트링한개 뒤에는 배열 그래서 포문으로 넣음
		}
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		// TODO Auto-generated method stub
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		// TODO Auto-generated method stub
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		// TODO Auto-generated method stub
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listCriteria(cri);
	}

	@Override
	public int totalCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.totalCount();
	}

	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.listSearch(cri);
	}

	@Override
	public int searchCount(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return dao.searchCount(cri);
	}

	@Override
	public void updateCnt(Integer bno) throws Exception {
		// TODO Auto-generated method stub
	/*	dao.up*/
	}

	@Override
	public void addAttach(String fullname) throws Exception {
		// TODO Auto-generated method stub
		dao.addAttach(fullname);
	}

	@Override
	public List<String> selectAttachList(int bno) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
