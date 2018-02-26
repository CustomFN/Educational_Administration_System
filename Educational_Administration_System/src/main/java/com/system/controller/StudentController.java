package com.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.system.po.Course;
import com.system.po.SelectedCourseCustom;
import com.system.po.Selectedcourse;
import com.system.po.ShowPage;
import com.system.po.User;
import com.system.service.ICourseService;
import com.system.service.ISelectCourseService;
import com.system.service.IStudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private ICourseService iCourseService;
	@Autowired
	private ISelectCourseService iSelectCourseService;
	@Autowired
	private IStudentService iStudentService;
	
	
	@RequestMapping("/showCourse")
	public String showCourseUI(@RequestParam(defaultValue="1",name="page") Integer pageNum,
			Model model,HttpServletRequest request) {
		ShowPage<Course> page = iCourseService.findCourseList(pageNum);
		page.setAction(request.getContextPath());
		User user = (User) request.getSession().getAttribute("user");
		List<Selectedcourse> list = iSelectCourseService.getStudentSelectedCourseid(user.getUserId());
		model.addAttribute("pageInfo", page);
		model.addAttribute("selectedcourse", list);
		return "student/showCourse";
	}
	
	@RequestMapping(value = {"/selectCourse"})
	public String findCourseByName(@RequestParam(defaultValue="1",name="page") Integer pageNum,@RequestParam(name="findByName",required=false)String findByName,
			Model model,HttpServletRequest request) {
		if(pageNum == 1 && findByName != null && !"".equals(findByName)) {
			request.getSession().setAttribute("search", findByName);
		}
		if(findByName == null || "".equals(findByName)) {
			findByName = request.getSession().getAttribute("search").toString();
		}
		ShowPage<Course> page = iCourseService.findCourseByName(findByName, pageNum);
		page.setAction(request.getContextPath());
		model.addAttribute("pageInfo", page);
		return "student/showCourse";
	}
	
	@RequestMapping("/selectedCourse")
	public String showSelectedCourseList(@RequestParam(defaultValue="1",name="page") Integer pageNum,
			Model model,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		ShowPage<SelectedCourseCustom> page = iSelectCourseService.getSelectedCourseList(pageNum, user.getUserId());
		model.addAttribute("pageInfo", page);
		return "student/selectCourse";
	}
	
	@RequestMapping("/overCourse")
	public String showOverCourseList(@RequestParam(defaultValue="1",name="page") Integer pageNum,
			Model model,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		ShowPage<SelectedCourseCustom> page = iSelectCourseService.getOverCourseList(pageNum, user.getUserId());
		model.addAttribute("pageInfo", page);
		return "student/overCourse";
	}
	
	@RequestMapping(value = "/passwordRest", method = RequestMethod.GET)
	public String showPasswordRestUI() {
		return "student/passwordRest";
	}
	
	@RequestMapping(value = "/passwordRest", method = RequestMethod.POST)
	public String doPasswordRest(@RequestParam("oldPassword")String oldPassword,@RequestParam("password1")String newPassword,
			HttpServletRequest request,Model model) {
		User user = (User) request.getSession().getAttribute("user");
		if(!user.getPassword().equals(oldPassword)) {
			model.addAttribute("fail", "旧密码不正确");
			return "student/passwordRest";
		}
		iStudentService.updateStudentPassword(user.getUserId(), newPassword);
		model.addAttribute("success", "修改成功");
		return "login";
	}
	
	@RequestMapping("/stuSelectedCourse")
	public String doSelectCourse(@RequestParam("id")Integer courseid,HttpServletRequest request,Model model) {
		if(courseid == null) {
			model.addAttribute("message", "非法输入");
			return "error";
		}
		User user = (User) request.getSession().getAttribute("user");
		iSelectCourseService.selectCourse(user.getUserId(), courseid);
		return "redirect:/student/showCourse";
	}
	
	@RequestMapping("/outCourse")
	public String doOutCourse(@RequestParam("id")Integer courseid,HttpServletRequest request,Model model) {
		if(courseid == null) {
			model.addAttribute("message", "非法输入");
			return "error";
		}
		User user = (User) request.getSession().getAttribute("user");
		iSelectCourseService.outCourse(user.getUserId(), courseid);
		return "redirect:/student/showCourse";
	}
}
