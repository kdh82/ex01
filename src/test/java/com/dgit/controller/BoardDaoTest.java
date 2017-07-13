package com.dgit.controller;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/*.xml" })
public class BoardDaoTest {

	/*@Autowired
	private BoardDao dao;

	private static Logger logger = LoggerFactory.getLogger(BoardDaoTest.class);*/

	/*@Test
	public void testCredate() {
		BoardVo boardVo = new BoardVo();
		boardVo.setTitle("새로운 제목을 넣습니다.");
		boardVo.setContent("새로운 내용을 넣습니다.");
		boardVo.setWriter("user00");
		try {
			dao.create(boardVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	/*@Test
	public void testRead() {
		try {
			logger.info(dao.read(1).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testUpdate() throws Exception {
		BoardVo vo = new BoardVo();
		vo.setBno(1);
		vo.setTitle("수정된 제목");
		vo.setContent("수정된 테스트 내용");
		dao.update(vo);
	}*/
	
	/*@Test
	public void testDelete() throws Exception {
		dao.dalete(1);
	}*/
	
	/*@Test
	public void testListAll() throws Exception {
		logger.info(dao.listAll().toString());
	}*/
	
	/*@Test
	public void testListPage() throws Exception {
		int page = 2;
		List<BoardVo> list = dao.listPage(page);
		
		for(BoardVo boardVo : list) {
			logger.info(boardVo.getBno() + " : " + boardVo.getTitle());
		}
	}*/
	
	/*@Test
	public void testListCriteria() throws Exception {
		Criteria cri = new Criteria();
		cri.setPage(2);
		cri.setPerPageNum(20);
		
		List<BoardVo> list = dao.listCriteria(cri);
		
		for(BoardVo boardVo : list) {
			logger.info(boardVo.getBno() + " : " + boardVo.getTitle());
		}
	}*/
}
