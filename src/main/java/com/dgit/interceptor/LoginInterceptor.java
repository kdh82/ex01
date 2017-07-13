package com.dgit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dgit.domain.MemberVO;

public class LoginInterceptor extends HandlerInterceptorAdapter {

		private static final String LOGIN = "login";
	
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			// TODO Auto-generated method stub
			return true;
		}
		
		@Override
		public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
			
			MemberVO loginVO = (MemberVO)modelAndView.getModel().get("loginVO");
			System.out.println("----------------loginVO :" + loginVO);
			if(loginVO == null){
				response.sendRedirect("login"); //회원가입 화면으로 유도해야 하나, 화면이 없어서 이리 처리함.
			}else{
				//로그인 처리시 session영역에 login한 사람의 정보를 넣음
				HttpSession session = request.getSession();
				session.setAttribute(LOGIN, loginVO.getUserid());   //캐스팅처리해야됨
				
				//login이전에 이동될 uri가 있다면 dest에 저장을 해뒀음.
				//저장된 dest의 값을 받아, 그곳으로 이동되도록 함.
				String path = (String)session.getAttribute("dest");
				if(path != null){
					response.sendRedirect(path);
				}
			}
		}
}
