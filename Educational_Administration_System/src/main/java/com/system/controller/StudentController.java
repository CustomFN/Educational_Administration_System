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

/**
 * 学生功能操作
 * @author zincpool
 *
 */
@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private ICourseService iCourseService;
	@Autowired
	private ISelectCourseService iSelectCourseService;
	@Autowired
	private IStudentService iStudentService;
	
	
	//学生课程信息显示
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
	
	//学生课程信息查询
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
	
	//学生选课在修显示
	@RequestMapping("/selectedCourse")
	public String showSelectedCourseList(@RequestParam(defaultValue="1",name="page") Integer pageNum,
			Model model,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		ShowPage<SelectedCourseCustom> page = iSelectCourseService.getSelectedCourseList(pageNum, user.getUserId());
		model.addAttribute("pageInfo", page);
		return "student/selectCourse";
	}
	
	//学生选课已修显示
	@RequestMapping("/overCourse")
	public String showOverCourseList(@RequestParam(defaultValue="1",name="page") Integer pageNum,
			Model model,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		ShowPage<SelectedCourseCustom> page = iSelectCourseService.getOverCourseList(pageNum, user.getUserId());
		model.addAttribute("pageInfo", page);
		return "student/overCourse";
	}
	
	//跳转学生密码修改页面
	@RequestMapping(value = "/passwordRest", method = RequestMethod.GET)
	public String showPasswordRestUI() {
		return "student/passwordRest";
	}
	
	//学生密码修改操作
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
		return "redirect:/logout";
	}
	
	//学生选课操作
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
	
	//学生退课操作
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
