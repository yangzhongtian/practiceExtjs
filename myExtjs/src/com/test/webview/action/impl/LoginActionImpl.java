package com.test.webview.action.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.test.basic.impl.BasicActionSupportImpl;
import com.test.webview.action.ILoginAction;
import com.test.webview.service.ILoginServices;

@Controller("LoginAction")
@Scope("prototype")
public class LoginActionImpl extends BasicActionSupportImpl implements ILoginAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3547984161892371253L;
					
	@Resource(name="loginService")
	private ILoginServices loginServices;
	
	//用户名
	private String  userName;
	//密码
	private String passWord;
	
	
	
	public String getUserName() {
		return userName;
	}



	public void setUserName(String userName) {
		this.userName = userName;
	}



	public String getPassWord() {
		return passWord;
	}



	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	/*
	 * 登录
	 */
	public void  login(){
		boolean flag=	loginServices.login(userName,passWord);
		
		System.out.println(flag);
		
	}
	
	
}
