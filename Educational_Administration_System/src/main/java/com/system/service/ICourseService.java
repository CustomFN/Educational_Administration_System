package com.system.service;

import com.system.po.Course;
import com.system.po.CourseCustom;
import com.system.po.ShowPage;

public interface ICourseService {

	ShowPage<Course> findCourseList(int pageNum);
	
	ShowPage<Course> findCourseByName(String findByName,int pageNum);
	
	CourseCustom findCourseById(Integer id);
	
	Boolean saveCourse(Course course);
	
	void deleteCourseById(Integer id);
	
	void updateCourseById(Course course);
	
	ShowPage<Course> findCourseByTeacherid(int pageNum,String id);
	
	ShowPage<Course> findCourseByTeacheridAndName(int pageNum,String id,String findByName);
}
