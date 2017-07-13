package com.dgit.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dgit.domain.BoardVO;
import com.dgit.domain.PageMaker;
import com.dgit.domain.SearchCriteria;
import com.dgit.service.BoardService;
import com.dgit.util.MediaUtils;
import com.dgit.util.UploadFileUtils;

@Controller
@RequestMapping("/sboard/*")
public class SBoardController {
	private static final Logger logger = LoggerFactory.getLogger(SBoardController.class);
	
	@Autowired
	BoardService service;
	
	@Resource(name="uploadPath") //서블릿 컨텍스트 빈에 id
	String uploadPath;
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String registerGet() throws Exception{
		return "sboard/register";
	}
	
	@RequestMapping(value="register", method=RequestMethod.POST)
	public String resgterPOST(BoardVO vo, List<MultipartFile> imgFiles) throws Exception{
		logger.info(vo.toString());
		ArrayList<String> list = new ArrayList<>();
		
		for(MultipartFile file : imgFiles){
			logger.info("filename : " + file.getOriginalFilename());
		
			String thumb = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		
			list.add(thumb);
		}
		
		vo.setFiles(list.toArray(new String[list.size()]));
		
		service.regist(vo);
		
		//return "sboard/success";
		return "redirect:listPage";
	}
	
	@RequestMapping(value="listPage", method=RequestMethod.GET)
	public String listPage(@ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception{
		System.out.println("=============list         Page=========");
		System.out.println(cri.toString());
		model.addAttribute("list", service.listSearch(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.searchCount(cri));
		model.addAttribute("pageMaker", pageMaker);
		return "sboard/listPage";
	}
	
	
	@RequestMapping(value="read", method=RequestMethod.GET)
	public String readPage(int bno, boolean fromlist, @ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception{
		System.out.println("=============readPage=========");
		System.out.println(cri.toString());
		BoardVO vo = new BoardVO();
		vo.getReplycnt();
		logger.info("replycnt================================================================================"+vo.getReplycnt());
		BoardVO board = service.read(bno);
		
		if(fromlist){
			board.setViewcnt(board.getViewcnt()+1);
			service.modify(board);
		}
		
		model.addAttribute("board", board);
		model.addAttribute("fromlist", true);
		return "sboard/read";
	}
	
	@RequestMapping(value="modify", method=RequestMethod.GET)
	public String modifyGet(int bno, @ModelAttribute("cri")SearchCriteria cri, Model model) throws Exception{
		BoardVO board = service.read(bno);
		model.addAttribute("board", board);
		return "sboard/modify";
	}
	
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyPost(@ModelAttribute("board") BoardVO board, @ModelAttribute("cri")SearchCriteria cri, 
												Model model, RedirectAttributes rttr, 
												String[] delFiles, List<MultipartFile> addFiles) throws Exception{

		service.modify(board); 
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		// RedirectAttributes은 jsp에서 사용 불가.. Controller에서만 사용가능하다
		rttr.addAttribute("bno", board.getBno());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addAttribute("searchType", cri.getSearchType());
		rttr.addAttribute("keyword", cri.getKeyword());
		rttr.addAttribute("filename", board.getFiles());
		//return "redirect:read"+pm.makeSearch(cri.getPage())+"&bno="+board.getBno();
		/*ArrayList<String> list = new ArrayList<>();
		for(MultipartFile file : addFiles){
			
		}*/
		
		
		 System.out.println("test---------------------------------------------------------------------------------------------------");
		 System.out.println(uploadPath);
		 System.out.println(delFiles);
		 System.out.println(board.getFiles());
		 //원본
//		 String front = filename.substring(0, 12); //0~11자리까지
//		 String end = filename.substring(14);   //14~끝까지
//		 String originalName = front + end; 
		 
		/* File file2 = new File(uploadPath + filename);
		 file2.delete();*/
		return "redirect:read";
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(/*BoardVO board,*/ @ModelAttribute("cri")SearchCriteria cri,int bno,String[] filename) throws Exception{
		List<String> list = service.selectAttachList(bno);
			//thumbnail
		if(filename != null){
			for(int i=0;i<filename.length;i++){
			File file = new File(uploadPath + filename[i]);   // /가 안붙어잇으면 넣어야됨 넣어있어서 안넣어도됨
			 file.delete();
		
			 //원본
			 System.out.println("------------------------------------------------------------------------------------------");
			 String front = filename[i].substring(0, 12); //0~11자리까지
			 System.out.println(front+"------------------------------------");
			 String end = filename[i].substring(14);   //14~끝까지
			 System.out.println(end);
			 String originalName = front + end; 
			 System.out.println(originalName);
			 File file2 = new File(uploadPath + originalName);
			 file2.delete();
			}
		}
		
		//service.remove(board.getBno());
		service.remove(bno);
		
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		return "redirect:listPage"+pm.makeSearch(cri.getPage());
	}
	@ResponseBody
	@RequestMapping(value = "displayFile") // displayFile?filename=##############.jpg
	public ResponseEntity<byte[]> displayFile(String filename) throws IOException {
		ResponseEntity<byte[]> entity = null;

		InputStream in = null;

		logger.info("----displayFile : " + filename);

		try {
			String formatName = filename.substring(filename.lastIndexOf(".") + 1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			HttpHeaders header = new HttpHeaders();
			header.setContentType(mType);

			in = new FileInputStream(uploadPath + "/" + filename);
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), header, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);

		} finally {
			in.close();
		}
		return entity;
}
	@ResponseBody             //이거 안넣을려면 맨위에 컨트롤러에 레스트컨트롤러로 
	@RequestMapping(value="deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String filename){
		ResponseEntity<String> entity = null;
		logger.info("----------------------------------------deleteFile POST---------------------------------");
		logger.info("filename : " + filename);
		
		try{
			//thumbnail
			File file = new File(uploadPath + filename);   // /가 안붙어잇으면 넣어야됨 넣어있어서 안넣어도됨
			 file.delete();
			 
			 //원본
			 String front = filename.substring(0, 12); //0~11자리까지
			 String end = filename.substring(14);   //14~끝까지
			 String originalName = front + end; 
			 
			 File file2 = new File(uploadPath + originalName);
			 file2.delete();
			 
			 
			 
			entity = new ResponseEntity<String>("success", HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			entity = new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}