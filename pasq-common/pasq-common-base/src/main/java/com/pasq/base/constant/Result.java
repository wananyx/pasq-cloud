package com.pasq.base.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于封装通用返回信息
 */
public class Result extends HashMap<String, Object> {

    private int code;

    private String msg;

	public Result() {
		put("code", 200);
		put("msg", "操作成功");
	}

	public Result(int code, String msg){
	    this.code = code;
	    this.msg = msg;
    }

	public static Result ok(String msg) {
		Result result = new Result();
		result.put("msg", msg);
		return result;
	}


	/**
	 * 返回多个键值对map使用
	 * @param map
	 * @return
	 */
	public static Result ok(Map<String, Object> map) {
		Result result = new Result();
		result.putAll(map);
		return result;
	}

	/**
	 * 返回单个键值对map使用
	 * @param key
	 * @param value
	 * @return
	 */
	public static Result ok(String key, Object value) {
		Result result = new Result();
		result.put(key,value);
		return result;
	}

	public static Result ok() {
		return new Result();
	}

	public static Result error() {
		return error(500, "操作失败");
	}

	public static Result error(String msg) {
		return error(500, msg);
	}

	public static Result error(int code, String msg) {
		Result result = new Result();
		result.put("code", code);
		result.put("msg", msg);
		return result;
	}

	@Override
	public Result put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
