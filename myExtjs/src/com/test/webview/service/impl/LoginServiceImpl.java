package com.test.webview.service.impl;

import org.springframework.stereotype.Service;

import com.test.basic.BasicEntity;
import com.test.basic.impl.BasicServiceSupportImpl;
import com.test.webview.service.ILoginServices;
@Service("loginService")
public class LoginServiceImpl extends BasicServiceSupportImpl implements ILoginServices{
	//用户登录
	public boolean login(String userName, String passWord) {
		try {
			BasicEntity basicEntity	 = (BasicEntity) basicDao.findForObject("webview.login.login", userName);
			if(basicEntity==null){
				return false;
			}else {
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("错误");
		}
		return false;
	}
	
}
