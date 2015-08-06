package com.test.webview.service.impl;

import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.test.basic.impl.BasicServiceSupportImpl;
import com.test.webview.entity.FunctionsM;
import com.test.webview.service.IFunctionService;

@Service("functionService")
public class FunctionServiceImpl extends BasicServiceSupportImpl implements IFunctionService{

	public List<FunctionsM> getFun() throws Exception {
		List<FunctionsM> functionsList= (List<FunctionsM>) basicDao.findForList("setting.function.getFun", null);
		return functionsList;
	}
	//添加Function
	public boolean addFun(FunctionsM function) throws Exception {
		 basicDao.save("setting.function.addFun", function);
		return false;
	}
	//添加树形
	public List<FunctionsM> getTree(int parentId) throws Exception {
		List<FunctionsM> list =(List<FunctionsM>) basicDao.findForList("setting.function.getTree",parentId );
		return list;
	}
 
}
