package com.system.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录拦截器
 * @author zincpool
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	//在请求到达DispatcherServlet之前拦截所有请求
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//拦截所有请求，判断用户是否登录，未登录则跳转到登录页面
		if(request.getSession().getAttribute("user") == null) {
			response.setContentType("text/html;charset=utf-8");
			response.sendRedirect("/");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
