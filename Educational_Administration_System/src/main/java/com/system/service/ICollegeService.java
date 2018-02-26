package com.system.service;

import java.util.List;

import com.system.po.College;

public interface ICollegeService {

	List<College> findAll();
	
	List<College> findExceptById(int collegeid);
	
}
