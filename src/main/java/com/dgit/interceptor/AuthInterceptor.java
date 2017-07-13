package com.dgit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AUTH Interceptor PreHandle----------------------");
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("login");
		System.out.println("session userid : " + userid);
		
		if(userid == null){
			saveDest(request);
			//http://localhost:8080/ex01/sboard/user/login
			String url = request.getContextPath() + "/user/login";
			
			response.sendRedirect(url);
			return false;   //가려는데로 가지말고 내가 지정한곳으로 가라
		}
		return true;
	}
	
	
	//이전 로그인 화면으로 이동전에 command와 query를 기억해 둔다.
	private void saveDest(HttpServletRequest request){
		String uri = request.getRequestURI();
		String query  = request.getQueryString(); // ? key = 주소창 쿼리 다가져옴
		
		if(query == null || query.equals("null")){
			query = "";
		}else{
			query = "?" + query;
		}
		
		System.out.println("dest----------uri :" + uri+query);
		
		if(request.getMethod().equals("GET")){
			System.out.println(uri + query);
			request.getSession().setAttribute("dest", uri + query);
		}
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("AUTH Interceptor PreHandle----------------------");
	}
}
