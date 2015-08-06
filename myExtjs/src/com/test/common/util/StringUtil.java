package com.test.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;



/**
 * @项目名称：专家系统
 * @类名称：StringUtil
 * @类描述：字符工具类
 * @创建人：
 * @创建时间：2012-09-07
 * @修改人：
 * @修改时间：
 * @备注：
 */
public class StringUtil {
	
	/**
	 * 判断一个字符串是否为数字字符串
	 */
	public static boolean isNum(String count){
		Pattern pattern = Pattern.compile("[0-9]+.?[0-9]+");
		Matcher matcher = pattern.matcher((CharSequence)count);
		boolean flag = matcher.matches();
		return flag;
	}
	
	/**
	 * 把一个String类型的集合转换为Integer类型的集合
	 * @param 1,2,3,4,5
	 * @param ,
	 * @return [1,2,3,4,5]
	 */
	public static List<Integer> getIntegerListByString(String str, String symbol) {
		if(str == null || str.trim().equals("")) {
			return null;
		}
		
		String [] sarr = str.split(symbol);
		Integer [] iarr = new Integer[sarr.length];
		for(int i=0;i<sarr.length;i++) {
			iarr[i] = Integer.parseInt(sarr[i]);
		}
		return Arrays.asList(iarr);
	}
	
	/**
	 * 类型转换
	 */
	public static Object castString(String value, Class<?> cls) {
		String name = cls.getSimpleName();
		Object cast = value;
		if(name.equalsIgnoreCase("Integer")) {
			cast = Integer.parseInt(cast.toString());
		}
		if(name.equalsIgnoreCase("Long")) {
			cast = Long.parseLong(cast.toString());
		}
		if(name.equalsIgnoreCase("Short")) {
			cast = Short.parseShort(cast.toString());
		}
		if(name.equalsIgnoreCase("Float")) {
			cast = Float.parseFloat(cast.toString());
		}
		if(name.equalsIgnoreCase("Double")) {
			cast = Double.parseDouble(cast.toString());
		}
		if(name.equalsIgnoreCase("Boolean")) {
			cast = Boolean.parseBoolean(cast.toString());
		}
		return cast;
	}
	
	/**
	 * 字符串首字母大写
	 * @param 字符串
	 * @return 首字母大写的字符串
	 */
	public static String toFirstUpperCase(String str) {
		if(str == null || str.length() < 1) {
			return "";
		}
		String start = str.substring(0,1).toUpperCase();
		String end = str.substring(1, str.length());
		return start + end;
	}
	
	/**
	 * 得到时间的条件
	 * @param 开始日期
	 * @param 结束日期
	 * @param 开始小时
	 * @param 结束小时
	 * @param 别名
	 * @return 时间条件
	 */
	public static String getWhereHour(String start,String end,Integer starthour,Integer endhour,String alias) {
		StringBuffer where = new StringBuffer("(");
		if(start != null) {
			where.append("(" + alias + ".day > '" + start + "'");
		}

		if(end != null) {
			if(!where.toString().trim().equals("")) {
				where.append(" and ");
			}
			where.append(alias + ".day < '" + end + "')");
		}
		
		if(start != null) {
			where.append(" or ");
			where.append("(" + alias + ".day = '" + start + "'");
			if(starthour != null) {
				where.append(" and ");
				where.append(alias + ".hour >= " + starthour);
			}
			where.append(")");
		}

		if(end != null) {
			// 开始时间和结束时间是否是同一天
			if(!start.equals(end)) {
				where.append(" or ");
			} else {
				where.append(" and ");
			}
			where.append("(" + alias + ".day = '" + end + "'");
			if(endhour != null) {
				where.append(" and ");
				where.append( alias + ".hour <= " + endhour);
			}
			where.append(")");
		}
		where.append(")");
		return where.toString();
	}
	
	/**
	 * 获取真实的ip地址
	 * @param HttpServletRequest
	 * @return IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) { 
        String ip = request.getHeader("X-Forwarded-For"); 
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP"); 
        } 
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("WL-Proxy-Client-IP"); 
        } 
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getRemoteAddr(); 
        } 
       return ip; 
    }
	
	/**
	 * 转换为utf8格式
	 * 
	 * @param infile
	 * @param outfile
	 * @throws IOException
	 */
	public static void convertToUtf8(String infile, String outfile)
			throws IOException {
		
		java.io.File f = new java.io.File(infile);
		String type = GetCharset(f);
		//System.out.println("***********###############"+type);
		BufferedReader in = new BufferedReader(new InputStreamReader(
				new FileInputStream(infile), type));
		PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(outfile), "utf-8")));
		String reading;
		while ((reading = in.readLine()) != null) {
			out.println(reading);
		}
		out.close();
		in.close();
	}
	
	/**
	 * 得到文件的字符集
	 * @param file
	 * @return
	 */
	public static String GetCharset(File file) {
		String charset = "GBK";
		byte[] first3Bytes = new byte[3];
		try {
			boolean checked = false;
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
			bis.mark(0);
			int read = bis.read(first3Bytes, 0, 3);
			if (read == -1)
				return charset;
			if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
				charset = "UTF-16LE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xFE
					&& first3Bytes[1] == (byte) 0xFF) {
				charset = "UTF-16BE";
				checked = true;
			} else if (first3Bytes[0] == (byte) 0xEF
					&& first3Bytes[1] == (byte) 0xBB
					&& first3Bytes[2] == (byte) 0xBF) {
				charset = "UTF-8";
				checked = true;
			}
			bis.reset();
			if (!checked) {
				while ((read = bis.read()) != -1) {
					if (read >= 0xF0)
						break;
					if (0x80 <= read && read <= 0xBF) 
						break;
					if (0xC0 <= read && read <= 0xDF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) 
							continue;
						else
							break;
					} else if (0xE0 <= read && read <= 0xEF) {
						read = bis.read();
						if (0x80 <= read && read <= 0xBF) {
							read = bis.read();
							if (0x80 <= read && read <= 0xBF) {
								charset = "UTF-8";
								break;
							} else
								break;
						} else
							break;
					}
				}
			}

			bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return charset;
	}
	
	/**
	 * 标题清理函数
	 */
	public static String titlecleaner(String str) {
		str = str.replaceAll("\n", "");
		str = str.replaceAll("\r", "");
		str = str.trim();
		String xnew = str.replaceAll("_新华网", "");
		int pos = xnew.lastIndexOf('_');
		if (pos != -1)
		xnew = xnew.substring(0, pos);
		return xnew.toLowerCase().trim();
	}
	
	/**
	 * 过滤XML
	 */
	public static String parValue(String value){
		if(value == null) {
			return "";
		}
    	value=value.replaceAll("&", "&amp;" );
    	value=value.replaceAll("<", "&lt;" );
    	value=value.replaceAll(">", "&gt;" );
    	value=value. replaceAll("\"", "&quot;" );
    	value=value.replaceAll("\'", "&apos;" );
    	return value;
    }
}