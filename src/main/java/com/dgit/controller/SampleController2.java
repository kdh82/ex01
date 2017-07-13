package com.dgit.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dgit.domain.BoardVO;
import com.dgit.domain.ProjectVO;

@Controller
public class SampleController2 {
	
	@RequestMapping("doE")
	public String doE(Model model) {
		ProjectVO vo = new ProjectVO("Sample", 10000);
		//model.addAttribute("ProjectVO", vo);
		
		//Class를 key없이 넣으면 , class이름을 key로 인식함.
		model.addAttribute(vo);
		return "projectDetail";
	}
	
	@RequestMapping("doF")
	public String doF(Model model) {
		ArrayList<ProjectVO> list = new ArrayList<ProjectVO>();
		ProjectVO vo1 = new ProjectVO("Sample1", 20000);
		ProjectVO vo2 = new ProjectVO("Sample2", 30000);
		ProjectVO vo3 = new ProjectVO("Sample3", 40000);
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		model.addAttribute("list", list);
		
		return "projectDetail";
	}
	
	@RequestMapping("dojson")
	public @ResponseBody List<ProjectVO> doJson() {
		ArrayList<ProjectVO> list = new ArrayList<ProjectVO>();
		ProjectVO vo1 = new ProjectVO("Sample1", 20000);
		ProjectVO vo2 = new ProjectVO("Sample2", 30000);
		ProjectVO vo3 = new ProjectVO("Sample3", 40000);
		list.add(vo1);
		list.add(vo2);
		list.add(vo3);
		return list;
	}
	

	@RequestMapping(value="/ajax", method=RequestMethod.GET)
	public String testAjax(){
		return "ajax_test1";
	}
	
	@RequestMapping("/hello") // value="/hello" , method_RequestMethod.GET 과 동일
	public String sayHello(){
		return "Hello world";
	}
	
	@RequestMapping("/sendVO") // value="/hello" , method_RequestMethod.GET 과 동일
	public BoardVO sendVO(){
		BoardVO vo = new BoardVO();
		vo.setBno(1);
		vo.setContent("ccc");
		vo.setRegdate(new Date());
		vo.setTitle("TTT");
		return vo;
	}
	
	@RequestMapping("/sendList") // value="/hello" , method_RequestMethod.GET 과 동일
	public List<BoardVO> sendList(){
		List<BoardVO> list = new ArrayList<>();
		for(int i = 0; i < 10; i++){
			BoardVO vo = new BoardVO();
			vo.setBno(i);
			vo.setContent("ccc"+i);
			vo.setRegdate(new Date());
			vo.setTitle("TTT");
			list.add(vo);
		}
		return list;
	}
	
	@RequestMapping("/sendMap") // value="/hello" , method_RequestMethod.GET 과 동일
	public Map<Integer, BoardVO> sendMap(){
		Map<Integer, BoardVO> map = new HashMap<>();
		for(int i = 0; i < 10; i++){
			BoardVO vo = new BoardVO();
			vo.setBno(i);
			vo.setContent("ccc"+i);
			vo.setRegdate(new Date());
			vo.setTitle("TTT");
			map.put(i, vo);
		}
		return map;
	}
	
	@RequestMapping("/sendErrorAuth")
	public ResponseEntity<Void> sendListAuth(){// 400error
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400error	
	}
	
	
	@RequestMapping("/sendErroNot")
	public ResponseEntity<List<BoardVO>> sendListNot(){
		List<BoardVO> list = sendList();
		
		return new ResponseEntity<List<BoardVO>> (list, HttpStatus.NOT_FOUND); // 404Error		
	}

}
