package com.dgit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.PageMaker;
import com.dgit.domain.ReplyVO;
import com.dgit.service.BoardService;
import com.dgit.service.ReplyService;

@RestController // 써야 responsBody 안 써도 됨
//@Controller는 return으로 바로 view로 갈수 있음 ajax 처리하고 싶다면 반환되는 String 앞에 @ResponseBody 붙어야 함... 매번..
// 스프링 3.0 부터 사용가능
@RequestMapping("/replies")
// '/' 안 붙인건 http://localhost:8080/ex01/sboard/read 제일 마지막 / 뒤로 붙는다. 그래서 http://localhost:8080/ex01/sboard/replies
// '/' 붙인건 http://localhost:8080/ex01/sboard/read.. 에서 첫번쨰 / 이후로 내가 붙겠다... 그래서 프로젝트 명이 날아감...
public class ReplyController {
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	
	@Autowired
	ReplyService service;

	@RequestMapping(value="all/{bno}", method=RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try{
			List<ReplyVO> list = service.listReply(bno);
			entity = new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<List<ReplyVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST) // get으로 하면 댓글 등록 누구나 다 되서...
	public ResponseEntity<String> add(@RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		
		try{
			service.addReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rno") int rno){
		ResponseEntity<String> entity = null;
		
		try{
			ReplyVO vo = new ReplyVO();
			vo.setRno(rno);
			service.removeReply(rno);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="/{rno}", method=RequestMethod.PUT) 
	public ResponseEntity<String> update(@PathVariable("rno") int rno, @RequestBody ReplyVO vo){
		ResponseEntity<String> entity = null;
		
		try{
			vo.setRno(rno);
			service.modifyReply(vo);
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		}catch(Exception e){
			entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="all/{bno}/{page}", method=RequestMethod.GET)		//list는 get을 쓴다
	public ResponseEntity<Map<String, Object>> listPage(@PathVariable("bno") Integer bno, @PathVariable("page") Integer page ){
		ResponseEntity<Map<String, Object>> entity =null;
		logger.info("====================================listPage GET================");
		
		try{
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			List<ReplyVO> list = service.listPage(bno, cri);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(service.count(bno));
			
			Map<String, Object> map = new HashMap<>();
			map.put("list", list);
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<>(map, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
		
}
