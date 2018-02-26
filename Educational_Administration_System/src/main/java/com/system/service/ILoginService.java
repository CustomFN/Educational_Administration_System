package com.system.service;

import com.system.po.User;

public interface ILoginService {

	User login(String userid,String password);
}
