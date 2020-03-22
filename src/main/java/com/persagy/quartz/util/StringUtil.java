package com.persagy.quartz.util;

import org.springframework.util.CollectionUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.util.*;

/**
 * StringUtil
 *
 * @author LuoGuangyi
 * @date 2019/07/22
 */
public class StringUtil {

	public static String getStringValue(Object value, Object defaultValue) {
		if (value == null) {
			if (defaultValue != null) {
				return defaultValue.toString();
			}
			return "";
		}
		return value.toString();
	}

	public static String getString(Object obj) {
		return getString(obj, "");
	}

	public static String getString(Object obj, String defaultValue) {
		return obj != null ? obj.toString() : defaultValue;
	}
	/** 
	  * 判断字符串是否是整数 
	  */  
	public static boolean isInteger(Object obj) {
		try {
			Integer.parseInt(String.valueOf(obj));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/** 
	  * 获取整数 ，不是则返回0
	  */  
	public static int getInt(Object obj) {
		return getInt(obj, 0);
	}

	public static int getInt(Object obj, int defaultValue) {
		return (obj != null) && (isInteger(obj)) ? Integer.parseInt(String.valueOf(obj)) : defaultValue;
	}
	/** 
	  * 判断字符串是否是浮点数 
	  */  
	public static boolean isLong(Object obj) {
		try {
			Long.parseLong(String.valueOf(obj));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/** 
	  * 判断字符串是否是数字 
	  */  
	 public static boolean isNumber(Object obj) {  
	     return isInteger(obj) || isDouble(obj) || isLong(obj);  
	 }  
	 /** 
	  * 获取长整数 ，不是长整数则返回0
	  */  
	public static long getLong(Object obj) {
		return getLong(obj, 0L);
	}

	public static long getLong(Object obj, long defaultValue) {
		return (obj != null) && (isLong(obj)) ? Long.parseLong(String.valueOf(obj)) : defaultValue;
	}
	/** 
	  * 判断字符串是否是单精度浮点数 
	  */ 
	public static boolean isFloat(Object obj) {
		try {
			Float.parseFloat(String.valueOf(obj));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/** 
	  * 获取单精度浮点数
	  */  
	public static float getFloat(Object obj) {
		return getFloat(obj, 0.0F);
	}

	public static float getFloat(Object obj, float defaultValue) {
		return (obj != null) && (isFloat(obj)) ? Float.parseFloat(String.valueOf(obj)) : defaultValue;
	}
	/** 
	  * 判断字符串是否是浮点数 
	  */ 
	public static boolean isDouble(Object obj) {
		try {
			Double.parseDouble(String.valueOf(obj));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	/** 
	  * 获取双精度浮点数
	  */  
	public static double getDouble(Object obj) {
		return getDouble(obj, 0.0D);
	}

	public static double getDouble(Object obj, double defaultValue) {
		return (obj != null) && (isDouble(obj)) ? Double.parseDouble(String.valueOf(obj)) : defaultValue;
	}

	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	public static boolean isEmpty(Object obj) {
		if (obj == null)
        {
            return true;
        }
        if ((obj instanceof CharSequence))
        {
            return ((CharSequence) obj).length() == 0;
        }
        if((obj instanceof Collection)) {
        	return CollectionUtils.isEmpty((Collection) obj);
        }
        if((obj instanceof Map)) {
        	return (((Map) obj).isEmpty());
        }
        if (obj.getClass().isArray()) {
        	return Array.getLength(obj) == 0;
        }
        /*
        和上面的判断数组效果一样，但是不会报空指针
		if (obj instanceof Object[]) {
			return (((Object[])obj).length == 0);
		}*/
        return false;
	}

	/**
	 * 只有true 或者 "true" 返回 true,否则返回false
	 * 
	 * @param obj
	 * @return true or false
	 */
	public static boolean getBoolean(Object obj) {
		return isEmpty(obj) ? false : Boolean.valueOf(obj.toString());
	}
	/**
	 * 将完整的堆栈信息输出到日志系统中
	 * @param t
	 * @return
	 */
	public static String getTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        return buffer.toString();
    }
	 public static String transform(final Throwable cause) {
	        if (null == cause) {
	            return "";
	        }
	        StringWriter result = new StringWriter();
	        try (PrintWriter writer = new PrintWriter(result)) {
	            cause.printStackTrace(writer);
	        }
	        return result.toString();
	    }

}
