package com.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.system.po.User;
import com.system.service.ILoginService;

@Controller
public class LoginController {

	@Autowired
	private ILoginService iLoginService;
	
	@RequestMapping(value="/login",method= {RequestMethod.POST})
	public String login(String userid,String password,HttpServletRequest request,Model model) {
		User user = iLoginService.login(userid, password);
		if(user.getRole() == -1) {
			model.addAttribute("fail", "账号密码错误");
			return "login";
		}else {
			request.getSession().setAttribute("user", user);
			if(user.getRole() == 1) {
				return "redirect:/student/showCourse";
			}else if(user.getRole() == 2) {
				return "redirect:/teacher/showCourse";
			}else {
				return "redirect:/admin/showStudent";
			}
		}
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		return "redirect:/";
	}
}
