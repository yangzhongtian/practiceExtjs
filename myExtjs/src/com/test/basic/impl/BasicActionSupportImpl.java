package com.test.basic.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;










import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.test.basic.IBasicActionSupport;
import com.test.common.util.StringUtil;






@Controller("basicActionSupport")
@Scope("prototype")
public class BasicActionSupportImpl extends ActionSupport implements IBasicActionSupport {
	
	
	/**
	 * 得到所有传递的参数以Map的形式保存
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getParameterMap() {
		Map<String, String[]> map = (Map<String, String[]>) getRequest()
				.getParameterMap();
		Map<String, Object> result = new HashMap<String, Object>();
		Set<Entry<String, String[]>> set = map.entrySet();
		StringBuffer params = new StringBuffer("params: ");
		for (Entry<String, String[]> entry : set) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			String value = StringUtils.join(values, "-");
			result.put(key, value);
			params.append(key + "=" + value + " ");
		}
	//	log.debug(params.toString());
		return result;
	}

	/**
	 * 得到所有传递的参数以Object的形式保存
	 */
	@SuppressWarnings("unchecked")
	public Object getParameterObject(Class<?> cls) {
		Object entity = null;
		try {
			entity = Class.forName(cls.getName()).newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Map<String, String[]> map = (Map<String, String[]>) getRequest()
				.getParameterMap();
		Set<Entry<String, String[]>> set = map.entrySet();
		StringBuffer params = new StringBuffer();
		StringBuffer noParams = new StringBuffer();
		for (Entry<String, String[]> entry : set) {
			String key = entry.getKey();
			String[] values = entry.getValue();
			String value = StringUtils.join(values, "-");
			try {
				Class<?> type = cls.getMethod(
						"get" + StringUtil.toFirstUpperCase(key))
						.getReturnType();
				if(null == type){
					continue;
				}
				Method method = cls.getMethod(
						"set" + StringUtil.toFirstUpperCase(key), type);
				method.invoke(entity,
						type.cast(StringUtil.castString(value, type)));
				params.append(key + "=" + value + " ");
			}
			catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				noParams.append(key + "=" + value + " ");
			//	log.debug("NoSuchMethod: " + key + "=" + value + " ");
				continue;
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		//log.debug("params: " + params.toString());
		if (noParams.toString().length() > 0) {
	//		log.warn("no params: " + noParams.toString());
		}
		return entity;
	}

	/**
	 * 得到HttpServletRequest
	 */
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 得到HttpServletResponse
	 */
	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 得到HttpSession
	 */
	public HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 得到ServletContext
	 */
	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}
}
