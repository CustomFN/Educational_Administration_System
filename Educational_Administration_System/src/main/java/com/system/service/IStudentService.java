package com.system.service;


import com.system.po.ShowPage;
import com.system.po.Student;
import com.system.po.StudentCustom;

public interface IStudentService {

	ShowPage<StudentCustom> findStudentList(int pageNum);
	
	ShowPage<StudentCustom> findStudentByName(String findByName,int pageNum);
	
	StudentCustom findStudentById(String id);
	
	Boolean saveStudent(Student student);
	
	void deleteStudentById(String id);
	
	void updateStudentById(StudentCustom studentCustom);
	
	void updateStudentPassword(String userid,String password);
}
