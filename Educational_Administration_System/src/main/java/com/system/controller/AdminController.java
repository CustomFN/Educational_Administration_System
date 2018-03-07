package com.system.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.system.po.College;
import com.system.po.Course;
import com.system.po.CourseCustom;
import com.system.po.ShowPage;
import com.system.po.Student;
import com.system.po.StudentCustom;
import com.system.po.Teacher;
import com.system.po.TeacherCustom;
import com.system.service.ICollegeService;
import com.system.service.ICourseService;
import com.system.service.IStudentService;
import com.system.service.ITeacherService;


/**
 * 管理员功能操作
 * @author zincpool
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private IStudentService iStudentService;
	@Autowired
	private ICollegeService iCollegeService;
	@Autowired
	private ITeacherService iTeacherService;
	@Autowired
	private ICourseService iCourseService;
	
	
	/* ******************************学生操作************************* */

    //  学生信息显示
	@RequestMapping("/showStudent")
	public String showStudent(@RequestParam(defaultValue="1",name="page") Integer pageNum,
			Model model,HttpServletRequest request) {
		ShowPage<StudentCustom> page = iStudentService.findStudentList(pageNum);
		page.setAction(request.getContextPath());
		model.addAttribute("pageInfo", page);
		return "admin/showStudent";
	}
	
	//搜索学生
	@RequestMapping("/selectStudent")
	public String findStudentByName(@RequestParam(defaultValue="1",name="page") Integer pageNum,@RequestParam(name="findByName",required=false)String findByName,
			Model model,HttpServletRequest request) {
		if(pageNum == 1 && findByName != null && !"".equals(findByName)) {
			request.getSession().setAttribute("search", findByName);
		}
		if(findByName == null || "".equals(findByName)) {
			findByName = request.getSession().getAttribute("search").toString();
		}
		ShowPage<StudentCustom> page = iStudentService.findStudentByName(findByName, pageNum);
		page.setAction(request.getContextPath());
		model.addAttribute("pageInfo", page);
		return "admin/showStudent";
	}
	
	//跳转添加学生页面
	@RequestMapping(value = "/addStudent",method = RequestMethod.GET)
	public String showAddStudentUI(Model model) {
		List<College> list = iCollegeService.findAll();
		model.addAttribute("collegeList",list);
		return "admin/addStudent";
	}
	
	//添加学生操作
	@RequestMapping(value = "/addStudent",method = RequestMethod.POST)
	public String addStudent(Student student,Model model) {
		Boolean isSuccess = iStudentService.saveStudent(student);
		if(!isSuccess) {
			model.addAttribute("message", "学号重复");
			return "error";
		}
		return "redirect:/admin/showStudent";
	}
	
	//删除学生操作
	@RequestMapping("/removeStudent")
	public String deleteStudentById(@RequestParam(name="id")String id) {
		if(id == null || "".equals(id)) {
			return "admin/showStudent";
		}
		iStudentService.deleteStudentById(id);
		return "redirect:/admin/showStudent";
	}
	
	//跳转修改学生页面
	@RequestMapping(value = "/editStudent",method = RequestMethod.GET)
	public String showEditStudentUI(@RequestParam(name="id")String id,Model model) {
		if(id == null || "".equals(id)) {
			return "admin/showStudent";
		}
		StudentCustom sc = iStudentService.findStudentById(id);
		List<College> list = iCollegeService.findExceptById(sc.getCollegeid());
		model.addAttribute("studentInfo", sc);
		model.addAttribute("collegeList", list);
		return "admin/editStudent";
	}
	
	//修改学生操作
	@RequestMapping(value = "/editStudent",method = RequestMethod.POST)
	public String editStudent(StudentCustom studentCustom) {
		iStudentService.updateStudentById(studentCustom);
		return "redirect:/admin/showStudent";
	}
	
	
	/* ************************ 教师管理 **************************************** */

	// 教师信息显示
	@RequestMapping("/showTeacher")
	public String showTeacher(@RequestParam(defaultValue="1",name="page") Integer pageNum,
			Model model,HttpServletRequest request) {
		ShowPage<TeacherCustom> page = iTeacherService.findTeacherList(pageNum);
		page.setAction(request.getContextPath());
		model.addAttribute("pageInfo", page);
		return "admin/showTeacher";
	}
	
	//搜索教师
	@RequestMapping("/selectTeacher")
	public String findTeacherByName(@RequestParam(defaultValue="1",name="page") Integer pageNum,@RequestParam(name="findByName",required=false)String findByName,
			Model model,HttpServletRequest request) {
		if(pageNum == 1 && findByName != null && !"".equals(findByName)) {
			request.getSession().setAttribute("search", findByName);
		}
		if(findByName == null || "".equals(findByName)) {
			findByName = request.getSession().getAttribute("search").toString();
		}
		ShowPage<TeacherCustom> page = iTeacherService.findTeacherByName(findByName, pageNum);
		page.setAction(request.getContextPath());
		model.addAttribute("pageInfo", page);
		return "admin/showTeacher";
	}
	
	//跳转添加教师页面
	@RequestMapping(value = "addTeacher",method = RequestMethod.GET)
	public String showAddTeacherUI(Model model) {
		List<College> list = iCollegeService.findAll();
		model.addAttribute("collegeList",list);
		return "admin/addTeacher";
	}
	
	//添加教师操作
	@RequestMapping(value = "/addTeacher",method = RequestMethod.POST)
	public String addTeacher(Teacher teacher,Model model) {
		Boolean isSuccess = iTeacherService.saveTeacher(teacher);
		if(!isSuccess) {
			model.addAttribute("message", "工号重复");
			return "error";
		}
		return "redirect:/admin/showTeacher";
	}
	
	//删除教师
	@RequestMapping("/removeTeacher")
	public String deleteTeacherById(@RequestParam(name="id")String id) {
		if(id == null || "".equals(id)) {
			return "admin/showTeacher";
		}
		iTeacherService.deleteTeacherById(id);
		return "redirect:/admin/showTeacher";
	}
	
	//跳转修改教师信息页面
	@RequestMapping(value = "/editTeacher",method = RequestMethod.GET)
	public String showEditTeacherUI(@RequestParam(name="id")String id,Model model) {
		if(id == null || "".equals(id)) {
			return "admin/showTeacher";
		}
		TeacherCustom tc = iTeacherService.findTeacherById(id);
		List<College> list = iCollegeService.findExceptById(tc.getCollegeid());
		model.addAttribute("teacherInfo", tc);
		model.addAttribute("collegeList", list);
		return "admin/editTeacher";
	}
	
	//修改教师信息操作
	@RequestMapping(value = "/editTeacher",method = RequestMethod.POST)
	public String editTeacher(TeacherCustom teacherCustom) {
		iTeacherService.updateTeacherById(teacherCustom);
		return "redirect:/admin/showTeacher";
	}
	
	/* ********************** 课程管理 ******************************* */
	
	// 课程信息显示
	@RequestMapping("showCourse")
	public String showCourse(@RequestParam(defaultValue="1",name="page") Integer pageNum,
			Model model,HttpServletRequest request) {
		ShowPage<Course> page = iCourseService.findCourseList(pageNum);
		page.setAction(request.getContextPath());
		model.addAttribute("pageInfo", page);
		return "admin/showCourse";
	}
	
	//搜索课程
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
		return "admin/showCourse";
	}
	
	//跳转添加课程信息页面
	@RequestMapping(value = "addCourse",method = RequestMethod.GET)
	public String showAddCourseUI(Model model) {
		List<Teacher> teacher = iTeacherService.findTeacherId();
		List<College> college = iCollegeService.findAll();
		model.addAttribute("teacherIdList", teacher);
		model.addAttribute("collegeList",college);
		return "admin/addCourse";
	}
	
	//添加课程信息操作
	@RequestMapping(value = "/addCourse",method = RequestMethod.POST)
	public String addCourse(Course course,Model model) {
		Boolean isSuccess = iCourseService.saveCourse(course);
		if(!isSuccess) {
			model.addAttribute("message", "课程号重复");
			return "error";
		}
		return "redirect:/admin/showCourse";
	}
	
	//删除课程信息
	@RequestMapping("/removeCourse")
	public String deleteCourseById(@RequestParam(name="id")Integer id) {
		if(id == null) {
			return "admin/showCourse";
		}
		iCourseService.deleteCourseById(id);
		return "redirect:/admin/showCourse";
	}
	
	//跳转修改课程信息页面
	@RequestMapping(value = "/editCourse",method = RequestMethod.GET)
	public String showEditCourseUI(@RequestParam(name="id")Integer id,Model model) {
		if(id == null) {
			return "admin/showCourse";
		}
		CourseCustom cc = iCourseService.findCourseById(id);
		List<College> list = iCollegeService.findExceptById(cc.getCollegeid());
		List<Teacher> teacherIdList = iTeacherService.findTeacherExceptById(cc.getTeacherid());
		model.addAttribute("teacherIdList", teacherIdList);
		model.addAttribute("courseInfo", cc);
		model.addAttribute("collegeList", list);
		return "admin/editCourse";
	}
	
	//修改课程信息操作
	@RequestMapping(value = "/editCourse",method = RequestMethod.POST)
	public String editCourse(Course course) {
		iCourseService.updateCourseById(course);
		return "redirect:/admin/showCourse";
	}
	
	/* ******************* 其他操作 ******************************** */
	
	//跳转重置账号密码页面
	@RequestMapping(value = "/userPasswordRest", method = RequestMethod.GET)
	public String showUserPasswordRestUI() {
		return "admin/userPasswordRest";
	}
	
	//重置账号密码操作
	@RequestMapping(value = "/userPasswordRest", method = RequestMethod.POST)
	public String userPasswordRest(String userid,String password,Model model) {
		if("admin".equals(userid)) {
			model.addAttribute("message", "违法操作,不能修改管理员密码");
			return "error";
		}else if(userid.length() == 5) {
			//学生
			iStudentService.updateStudentPassword(userid, password);
			model.addAttribute("success", "修改成功");
		}else if(userid.length() == 4) {
			//教师
			iTeacherService.updateTeacherPassword(userid, password);
			model.addAttribute("success", "修改成功");
		}else {
			//用户不存在
			model.addAttribute("fail", "用户不存在");
		}
		return "admin/userPasswordRest";
	}
	
	//跳转修改管理员密码页面
	@RequestMapping(value = "/passwordRest", method = RequestMethod.GET)
	public String showPasswordRestUI() {
		return "admin/passwordRest";
	}
	
	//修改管理员密码操作
	@RequestMapping(value = "/passwordRest", method = RequestMethod.POST)
	public String passwordRest(String oldPassword,@RequestParam(name="password1")String password) {
		return "login";
	}
}
