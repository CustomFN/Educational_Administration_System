package com.system.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName="loginFilter",urlPatterns="/*")
public class LoginFilter implements Filter {

	private List<String> urls = new ArrayList<>();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		urls.add("/");
		urls.add("/login");
		urls.add("/login.jsp");
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		String path = req.getContextPath();
		String action = uri.replace(path, "");
		
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		if(session.getAttribute("user") != null) {
			chain.doFilter(req, resp);
		}else if(urls.contains(action)){
			chain.doFilter(req, resp);
		}else {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.println("<script>window.top.location.href='"+path+"/'</script>");
			pw.flush();
			pw.close();
		}
		
	}

	@Override
	public void destroy() {
	}

	

}
