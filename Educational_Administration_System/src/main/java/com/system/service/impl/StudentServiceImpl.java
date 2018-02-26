package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.mapper.StudentMapper;
import com.system.po.ShowPage;
import com.system.po.Student;
import com.system.po.StudentCustom;
import com.system.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public ShowPage<StudentCustom> findStudentList(int pageNum) {
		ShowPage<StudentCustom> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<StudentCustom> list = studentMapper.selectStudentAndCollegeNameList();
		if(list != null) {
			PageInfo<StudentCustom> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			
			return page;
		}
		return null;
	}

	@Override
	public ShowPage<StudentCustom> findStudentByName(String findByName,int pageNum) {
		ShowPage<StudentCustom> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<StudentCustom> list = studentMapper.selectStudentAndCollegeNameByName(findByName);
		if(list != null) {
			PageInfo<StudentCustom> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public Boolean saveStudent(Student student) {
		int isSuccess = studentMapper.insert(student);
		return isSuccess == 1 ? true : false;
	}

	@Override
	public void deleteStudentById(String id) {
		studentMapper.deleteByPrimaryKey(id);
	}

	@Override
	public StudentCustom findStudentById(String id) {
		return studentMapper.findStudentById(id);
	}

	@Override
	public void updateStudentById(StudentCustom studentCustom) {
		studentMapper.updateByPrimaryKeySelective(studentCustom);
	}

	@Override
	public void updateStudentPassword(String userid, String password) {
		Student s = new Student();
		s.setUserid(userid);
		s.setPassword(password);
		studentMapper.updateByPrimaryKeySelective(s);
	}

}
