package com.medicalsystem.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @Title:LoginInterceptor
 * @author hxiaope
 * @date 2016年11月6日下午11:26:20
 * @version 1.0
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		
		// 获取请求URL
		String url = request.getRequestURI();
		if (url.indexOf("login") >= 0) {
			return true;
		}
		if (url.indexOf("showRegister") >= 0) {
			return true;
		}
		if (url.indexOf("loginPatient") >= 0) {
			return true;
		}
		if (url.indexOf("loginStaff") >= 0) {
			return true;
		}
		if (url.indexOf("register") >= 0) {
			return true;
		}

		HttpSession session = request.getSession();
		Long patientId =  (Long) session.getAttribute("patientId");
		Long staffId =(Long) session.getAttribute("staffId");

		if ( patientId!= null||staffId!=null) {
			// 身份存在，放行
			return true;
		}

		// 执行这里表示用户身份需要认证，跳转登陆页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);

		// return false表示拦截，不向下执行
		// return true表示放行
		return false;
		
	}
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, 
			Object object,
			ModelAndView modelAndView) throws Exception {

//		System.out.println("HandlerInterceptor1 postHandle HAVE BEEN RUN!!!");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object object,
			Exception exception) throws Exception {		
//		System.out.println("HandlerInterceptor1 afterCompletion HAVE BEEN RUN!!!");

	}

}