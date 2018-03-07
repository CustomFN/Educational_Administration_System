package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.mapper.TeacherMapper;
import com.system.po.ShowPage;
import com.system.po.Teacher;
import com.system.po.TeacherCustom;
import com.system.service.ITeacherService;

@Service
public class TeacherServiceImpl implements ITeacherService {

	@Autowired
	private TeacherMapper teacherMapper;
	
	@Override
	public ShowPage<TeacherCustom> findTeacherList(int pageNum) {
		ShowPage<TeacherCustom> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<TeacherCustom> list = teacherMapper.selectTeacherAndCollegeNameList();
		if(list != null) {
			PageInfo<TeacherCustom> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public ShowPage<TeacherCustom> findTeacherByName(String findByName, int pageNum) {
		ShowPage<TeacherCustom> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<TeacherCustom> list = teacherMapper.selectTeacherAndCollegeNameByName(findByName);
		if(list != null) {
			PageInfo<TeacherCustom> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public TeacherCustom findTeacherById(String id) {
		return teacherMapper.findTeacherById(id);
	}

	@Override
	public Boolean saveTeacher(Teacher teacher) {
		int isSuccess = teacherMapper.insert(teacher);
		return isSuccess == 1 ? true : false;
	}

	@Override
	public void deleteTeacherById(String id) {
		teacherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateTeacherById(TeacherCustom teacherCustom) {
		teacherMapper.updateByPrimaryKeySelective(teacherCustom);
	}

	@Override
	public List<Teacher> findTeacherId() {
		return teacherMapper.findTeacherId();
	}

	@Override
	public List<Teacher> findTeacherExceptById(String id) {
		return teacherMapper.findTeacherExceptById(id);
	}

	@Override
	public void updateTeacherPassword(String userid, String password) {
		Teacher t = new Teacher();
		t.setUserid(userid);
		t.setPassword(password);
		teacherMapper.updateByPrimaryKeySelective(t);
	}

	

}
