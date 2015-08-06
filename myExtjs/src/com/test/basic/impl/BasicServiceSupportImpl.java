package com.test.basic.impl;

import javax.annotation.Resource;
import org.apache.log4j.Logger;

import com.test.basic.IBasicDaoSupport;
import com.test.basic.IBasicServiceSupport;



public class BasicServiceSupportImpl implements IBasicServiceSupport {
	//protected Logger logger = CommonUtil.getLogger(this.getClass());
	
	@Resource(name = "basicDaoSupport")
	protected IBasicDaoSupport basicDao;
	
}
