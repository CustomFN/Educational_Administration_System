package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.system.mapper.SelectedcourseMapper;
import com.system.po.SelectedCourseCustom;
import com.system.po.Selectedcourse;
import com.system.po.SelectedcourseExample;
import com.system.po.SelectedcourseExample.Criteria;
import com.system.po.ShowPage;
import com.system.po.StudentGrade;
import com.system.service.ISelectCourseService;

@Service
public class SelectCourseServiceImpl implements ISelectCourseService {

	@Autowired
	private SelectedcourseMapper selectedcourseMapper;
	
	@Override
	public ShowPage<SelectedCourseCustom> getSelectedCourseList(int pageNum,String id) {
		ShowPage<SelectedCourseCustom> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<SelectedCourseCustom> list = selectedcourseMapper.getSelectedCourseList(id);
		if(list != null) {
			PageInfo<SelectedCourseCustom> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public ShowPage<SelectedCourseCustom> getOverCourseList(int pageNum,String id) {
		ShowPage<SelectedCourseCustom> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<SelectedCourseCustom> list = selectedcourseMapper.getOverCourseList(id);
		if(list != null) {
			PageInfo<SelectedCourseCustom> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public void selectCourse(String userid,int courseid) {
		Selectedcourse sc = new Selectedcourse();
		sc.setStudentid(userid);
		sc.setCourseid(courseid);
		selectedcourseMapper.insertSelective(sc);
	}

	@Override
	public void outCourse(String userid,int courseid) {
		SelectedcourseExample example = new SelectedcourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andCourseidEqualTo(courseid);
		criteria.andStudentidEqualTo(userid);
		selectedcourseMapper.deleteByExample(example);
	}

	@Override
	public List<Selectedcourse> getStudentSelectedCourseid(String id) {
		return selectedcourseMapper.getStudentSelectedCourseid(id);
	}

	@Override
	public ShowPage<StudentGrade> getStudentGradeByCourseId(int pageNum,int id) {
		ShowPage<StudentGrade> page = new ShowPage<>();
		PageHelper.startPage(pageNum, page.getPageSize());
		
		List<StudentGrade> list = selectedcourseMapper.getStudentGradeByCourseId(id);
		if(list != null) {
			PageInfo<StudentGrade> info = new PageInfo<>(list);
			page.setPage(pageNum);
			page.setTotal(info.getTotal());
			page.setList(info.getList());
			return page;
		}
		return null;
	}

	@Override
	public void updateSelectedcourse(Selectedcourse sc) {
		SelectedcourseExample example = new SelectedcourseExample();
		Criteria criteria = example.createCriteria();
		criteria.andCourseidEqualTo(sc.getCourseid());
		criteria.andStudentidEqualTo(sc.getStudentid());
		selectedcourseMapper.updateByExample(sc, example);
	}

}
