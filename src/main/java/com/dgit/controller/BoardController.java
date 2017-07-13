package com.dgit.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dgit.domain.BoardVO;
import com.dgit.domain.Criteria;
import com.dgit.domain.PageMaker;
import com.dgit.service.BoardService;

@Controller
@RequestMapping("/board/*")
// @RequestMapping("/board/*") 라고 지정하면 [  프로젝트명/board/접근명    : ex01/board/register ] 
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerGet() throws Exception{
		return "board/register";
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String resgterPOST(BoardVO vo) throws Exception{
		service.regist(vo);
		//return "board/success";
		return "redirect:listAll";
	}
	
	@RequestMapping(value="listAll", method=RequestMethod.GET)
	public String listAll(Model model) throws Exception{
		List<BoardVO> list = service.listAll();
		model.addAttribute("list", list);
		return "board/listAll";
	}
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public String listPage(Criteria cri, Model model) throws Exception{
		List<BoardVO> list = service.listCriteria(cri);
		model.addAttribute("list", list);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.totalCount());
		System.out.println(pageMaker.toString());
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/listPage";
	}
	
	@RequestMapping(value="listAll", method=RequestMethod.POST)
	public String regBoard(BoardVO vo) throws Exception{
		return "board/register";
	}
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	public String read(int bno, boolean frommod, @ModelAttribute("cri")Criteria cri, Model model) throws Exception{
		BoardVO vo = service.read(bno);
		
		if(frommod){
			System.out.println("====글 수정해서 글보기 중====");
		}else{
			vo.setViewcnt(vo.getViewcnt()+1);
			service.modify(vo);
			vo = service.read(bno);
		}
		model.addAttribute("board", vo);
		return "board/read";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(int bno, @ModelAttribute("cri")Criteria cri) throws Exception{
		service.remove(bno);
		return "redirect:listPage?page="+cri.getPage();
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modifyGET(int bno, @ModelAttribute("cri")Criteria cri, Model model) throws Exception{
		BoardVO vo = service.read(bno);
		model.addAttribute("board", vo);
		return "board/modify";
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO vo, @ModelAttribute("cri")Criteria cri, String sRegdate) throws Exception{
		DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		Date regdate = (Date) dateFormat.parse(sRegdate);
	//	Date regDate =Date.parse(sRegdate);
		vo.setRegdate(regdate);
		System.out.println("/////////////////////////////////"+vo.getBno());
		service.modify(vo);
		System.out.println("redirect:read?bno="+vo.getBno());
		// redirect는 request는 req값을 못 가져감
		return "redirect:read?bno="+vo.getBno()+"&frommod=true"+"&page="+cri.getPage();
	}
}
