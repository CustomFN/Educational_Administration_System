package com.system.service;

import java.util.List;

import com.system.po.SelectedCourseCustom;
import com.system.po.Selectedcourse;
import com.system.po.ShowPage;
import com.system.po.StudentGrade;

public interface ISelectCourseService {

	ShowPage<SelectedCourseCustom> getSelectedCourseList(int pageNum,String id);
	
	ShowPage<SelectedCourseCustom> getOverCourseList(int pageNum,String id);
	
	void selectCourse(String userid,int courseid);
	
	void outCourse(String userid,int courseid);
	
	//获取学生选修过的所有课程id
	List<Selectedcourse> getStudentSelectedCourseid(String id);
	
	ShowPage<StudentGrade> getStudentGradeByCourseId(int pageNum,int id);
	
	void updateSelectedcourse(Selectedcourse sc);
}
