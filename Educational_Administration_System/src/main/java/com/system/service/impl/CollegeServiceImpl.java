package com.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.mapper.CollegeMapper;
import com.system.po.College;
import com.system.po.CollegeExample;
import com.system.service.ICollegeService;

@Service
public class CollegeServiceImpl implements ICollegeService {

	@Autowired
	private CollegeMapper collegeMapper;
	
	@Override
	public List<College> findAll() {
		CollegeExample example = new CollegeExample();
		return collegeMapper.selectByExample(example);
	}


	@Override
	public List<College> findExceptById(int collegeid) {
		CollegeExample example = new CollegeExample();
		com.system.po.CollegeExample.Criteria criteria = example.createCriteria();
		criteria.andCollegeidNotEqualTo(collegeid);
		return collegeMapper.selectByExample(example);
	}

}
