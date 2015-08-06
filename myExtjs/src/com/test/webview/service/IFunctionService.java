package com.test.webview.service;

import java.util.List;

import com.test.webview.entity.FunctionsM;

public interface IFunctionService {

	List<FunctionsM> getFun() throws Exception;

	boolean addFun(FunctionsM function) throws Exception;

	List<FunctionsM> getTree(int parentId) throws Exception;

}
