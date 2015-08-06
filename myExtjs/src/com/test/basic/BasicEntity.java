package com.test.basic;

import java.io.Serializable;

public class BasicEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2613634381286163217L;
	private int  id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	//用户名
	private String userName;
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
	
}
