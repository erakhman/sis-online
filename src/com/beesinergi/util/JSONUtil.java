package com.beesinergi.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import flexjson.DateTransformer;
import flexjson.JSONSerializer;

public class JSONUtil {
    private Map<String, Object> data;
	private Set<String> dateFields;

	public JSONUtil(){
		data = new HashMap<String, Object>();
	}

	/**
	 * add date field name to be transform
	 * @param fieldName the field name
	 */
	public void addDateField(String fieldName){
		if(dateFields == null){
			dateFields = new HashSet<String>();
		}
		dateFields.add(fieldName);
	}

	/**
	 * add the data to be transform.
	 * @param key the key value
	 * @param value the object
	 */
	public void addData(String key, Object value){
		data.put(key, value);
	}


	/**
	 * Set the mapped data
	 * @param data the data map
	 */
	public void setData(Map<String, Object>  data ){
		this.data=data;
	}

	/**
	 * serialize json object
	 * @return json object
	 */
	public String serialize(){
		JSONSerializer json = new JSONSerializer();
		if((dateFields != null) && (dateFields.size() > 0)){
			DateTransformer transformer = new DateTransformer(SystemConstant.DATE_FORMAT);
			for (String field : dateFields) {
				json.transform(transformer, field);
			}
		}
		return json.exclude("*.class").deepSerialize(data);
	}
	
	
	public static Map<String, Object> decodeJSONObject(JSONObject jsonObj) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();
		String[] keys = JSONObject.getNames(jsonObj);
		for (String key : keys) {
			Object obj = jsonObj.get(key);
			if (obj instanceof JSONArray) {
				JSONArray jsonArray = (JSONArray) obj;
				map.put(key, decodeJSONArray(jsonArray));
			} else
				map.put(key, obj);
		}
		return map;
	}
	
	public static List<Map<String, Object>> decodeJSONArray(JSONArray jsonArr) throws JSONException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArr.length(); i++)
			list.add(decodeJSONObject(jsonArr.getJSONObject(i)));
		return list;
	}
	
	public static JSONArray encodeJSONArray(List<Map<String, Object>> results) throws JSONException {
		JSONArray jsArr = new JSONArray();
		for (Map<String, Object> map : results)
			jsArr.put(encodeJSONObject((map)));
		return jsArr;
	}

	@SuppressWarnings("unchecked")
	public static JSONObject encodeJSONObject(Map<String, Object> results) throws JSONException {
		JSONObject obj = new JSONObject();
		Set<String> keys = results.keySet();
		for (String key : keys) {
			if (results.get(key) instanceof List) {
				List<Map<String, Object>> list = (List<Map<String, Object>>) results.get(key);
				obj.put(key, encodeJSONArray(list));
			}
			obj.put(key, String.valueOf(results.get(key)));
		}
		return obj;
	}
}
