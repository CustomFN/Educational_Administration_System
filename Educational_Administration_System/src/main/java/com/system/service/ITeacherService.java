package com.system.service;

import java.util.List;

import com.system.po.ShowPage;
import com.system.po.Teacher;
import com.system.po.TeacherCustom;

public interface ITeacherService {

	ShowPage<TeacherCustom> findTeacherList(int pageNum);
	
	ShowPage<TeacherCustom> findTeacherByName(String findByName,int pageNum);
	
	TeacherCustom findTeacherById(String id);
	
	Boolean saveTeacher(Teacher teacher);
	
	void deleteTeacherById(String id);
	
	void updateTeacherById(TeacherCustom teacherCustom);
	
	List<Teacher> findTeacherId();
	
	List<Teacher> findTeacherExceptById(String id);
	
	void updateTeacherPassword(String userid,String password);
	
	
}
