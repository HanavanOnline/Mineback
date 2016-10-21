package com.Mineback.plugin;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class HTTP {
	
	public static final String ADDRESS = "http://192.241.150.140/";
	
	public static Map<String,Object> jsonToMap(String json) throws JsonParseException {
	
		Gson gson = new Gson();
		
		Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
		map.forEach((x,y)-> System.out.println("key : " + x + " , value : " + y));
		
		return map;
    }
	
}
