package com.system.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	//跳转首页
	@RequestMapping("/")
	public String showLogin() {
		return "login";
	}
}
