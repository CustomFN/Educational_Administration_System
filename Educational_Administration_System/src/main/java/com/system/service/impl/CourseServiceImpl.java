package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.mapper.CourseMapper;
import com.system.po.Course;
import com.system.po.CourseCustom;
import com.system.po.ShowPage;
import com.system.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService {

	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public ShowPage<Course> findCourseList(int pageNum) {
		ShowPage<Course> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<Course> list = courseMapper.selectCourseAndCollegeNameList();
		if(list != null) {
			PageInfo<Course> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public ShowPage<Course> findCourseByName(String findByName, int pageNum) {
		ShowPage<Course> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<Course> list = courseMapper.selectCourseAndCollegeNameByName(findByName);
		if(list != null) {
			PageInfo<Course> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public CourseCustom findCourseById(Integer id) {
		return courseMapper.findCourseById(id);
	}

	@Override
	public Boolean saveCourse(Course course) {
		int isSuccess = courseMapper.insert(course);
		return isSuccess == 1 ? true : false;
	}

	@Override
	public void deleteCourseById(Integer id) {
		courseMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateCourseById(Course courseCustom) {
		courseMapper.updateByPrimaryKeySelective(courseCustom);
	}
	
	@Override
	public ShowPage<Course> findCourseByTeacherid(int pageNum,String id) {
		ShowPage<Course> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<Course> list = courseMapper.selectCourseByTeacherid(id);
		if(list != null) {
			PageInfo<Course> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public ShowPage<Course> findCourseByTeacheridAndName(int pageNum, String id, String findByName) {
		ShowPage<Course> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<Course> list = courseMapper.selectCourseByTeacheridAndName(id, findByName);
		if(list != null) {
			PageInfo<Course> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

}
