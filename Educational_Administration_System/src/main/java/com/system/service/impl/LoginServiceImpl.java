package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.StudentMapper;
import com.system.mapper.TeacherMapper;
import com.system.po.Student;
import com.system.po.StudentExample;
import com.system.po.Teacher;
import com.system.po.TeacherExample;
import com.system.po.User;
import com.system.service.ILoginService;

@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public User login(String userid, String password) {
		User user = new User();
		if(checkAdmin(userid,password) != null) {
			user = checkAdmin(userid,password);
		}else if(checkStudent(userid,password) != null) {
			user = checkStudent(userid,password);
		}else if(checkTeacher(userid,password) != null) {
			user = checkTeacher(userid,password);
		}else {
			user.setRole(-1);
		}
		return user;
	}
	
	private User checkAdmin(String userid,String password) {
		User user = null;
		if("admin".equals(userid) && "123".equals(password)) {	
			user = new User(userid);
			user.setRole(0);
			user.setUsername("Admin");
		}
		return user;
	}
	
	private User checkStudent(String userid,String password) {
		User user = null;
		if(userid.length() == 5) {
			StudentExample example = new StudentExample();
			com.system.po.StudentExample.Criteria criteria = example.createCriteria();
			criteria.andUseridEqualTo(userid);
			criteria.andPasswordEqualTo(password);
			List<Student> list = studentMapper.selectByExample(example);
			if(!list.isEmpty()) {
				user = new User(userid);
				user.setRole(1);
				user.setUserId(userid);
				user.setUsername(list.get(0).getUsername());
				user.setPassword(list.get(0).getPassword());
			}
		}
		return user;
	}
	
	private User checkTeacher(String userid,String password) {
		User user = null;
		if(userid.length() == 4) {
			TeacherExample example = new TeacherExample();
			com.system.po.TeacherExample.Criteria criteria = example.createCriteria();
			criteria.andUseridEqualTo(userid);
			criteria.andPasswordEqualTo(password);
			List<Teacher> list = teacherMapper.selectByExample(example);
			if(!list.isEmpty()) {
				user = new User(userid);
				user.setRole(2);
				user.setUserId(userid);
				user.setUsername(list.get(0).getUsername());
				user.setPassword(list.get(0).getPassword());
			}
		}
		return user;
	}

}
