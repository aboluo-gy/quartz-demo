
package com.persagy.quartz.util;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.UUID;

/**
 * 功能描述： 常用工具方法
 * 
 * @类型名称 ToolsUtil
 * @创建者 wanghailong
 * @邮箱 wanghailong@persagy.com
 * @修改描述
 */
public class ToolsUtil {
	// 返回项-缺少必填项
	public final static String return_error_json = "{\"Result\":\"failure\",\"ResultMsg\":\"缺少必填项\"}";
	public final static String return_success_json = "{\"Result\":\"success\",\"ResultMsg\":\"操作成功\"}";
	public final static String return_fail_json = "{\"Result\":\"fail\",\"ResultMsg\":\"操作失败，请重试！\"}";

	public static String errorJsonMsg(String msg) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("Result", "failure");
		jsonRes.put("ResultMsg", msg);
		return jsonRes.toString();
	}

	public static String successJsonMsg(JSONArray array) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("Result", "success");
		jsonRes.put("Content", array);
		jsonRes.put("Count", array.size());
		return jsonRes.toString();
	}
	
	public static String successJsonMsgCount(JSONArray array) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("Result", "success");
		jsonRes.put("Count", array.size());
		return jsonRes.toString();
	}

	public static String successJsonMsg(JSONObject Item) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("Result", "success");
		jsonRes.put("Item", Item);
		return jsonRes.toString();
	}

	public static String successJsonMsg(String msg) {
		JSONObject jsonRes = new JSONObject();
		jsonRes.put("Result", "success");
		jsonRes.put("ResultMsg", msg);
		return jsonRes.toString();
	}

	public static String getUuid() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		return uuid;
	}

	public static String getRecordId(String prefix) {
		String recordId = "";
		if (!StringUtil.isEmpty(prefix)) {
			recordId = prefix;
		}
		recordId = recordId + UUID.randomUUID().toString().replace("-", "");
		return recordId;
	}

}
