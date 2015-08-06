package com.test.webview.action.impl;

import java.io.IOException;
import java.io.Serializable;
import java.text.Normalizer.Form;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.test.basic.impl.BasicDaoSupportImpl;
import com.test.webview.action.IFunctionAction;
import com.test.webview.entity.FunctionsM;
import com.test.webview.service.IFunctionService;

@Controller("functionAction")
@Scope("prototype")
public class FunctionActionImpl extends BasicDaoSupportImpl implements IFunctionAction,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 998654261795968355L;
	
	@Resource(name="functionService")
	private IFunctionService functionService;
	
	private String name;
	private String url;
	private int parentId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	//功能列表 
	public void getFun() throws Exception{
		List<FunctionsM> list = functionService.getFun();
		Map map = new HashMap();
		map.put("items",list);
		map.put("total", 20);
		String listsString = JSON.toJSONString(list);
		ServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(listsString);
	}
	
	public void addFun() throws IOException {
		FunctionsM function = new FunctionsM();
		function.setName(name);
		function.setUrl(url);
		function.setParentId(parentId);
		ServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			boolean falg = functionService.addFun(function);
			
			response.getWriter().print("{\"success\":true,\"msg\":\"添加成功\"}");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().print("{\"success\":false,\"msg\":\"添加失败\"}");
		}
	}
	
	//main 菜单的 左侧菜单
	public void getTree() throws Exception{
		List<FunctionsM> list =functionService.getTree(parentId);
		String  string  = JSON.toJSONString(list);
		ServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(string);
	}
}
