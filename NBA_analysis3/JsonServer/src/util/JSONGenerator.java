package util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class JSONGenerator {
    public static JSONObject getJSONObject(Object para){
        JSONObject result = new JSONObject();
        
        
        Field[] fields = para.getClass().getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            //遇到ArrayList，分情况讨论
            if(field.getType().toString().equals("class java.util.ArrayList")){
                //如果是RecordOfPlayer
                //System.out.println(field.getGenericType());
                switch(field.getGenericType().toString()){
                case "java.util.ArrayList<int[]>":
                	ArrayList<int[]> out = null;
					try {
						out = (ArrayList<int[]>)field.get(para);
					} catch (IllegalArgumentException | IllegalAccessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                	JSONArray outArray = new JSONArray();
                	for(int[] outToken:out){
                		JSONArray inArray = new JSONArray(outToken);
                		outArray.put(inArray);
                	}
                	result.put(field.getName(), outArray);
                	break;
                case "java.util.ArrayList<String>":
                {
                	JSONArray array = new JSONArray();
                    try {
                        ArrayList<String> list = (ArrayList<String>) field.get(para);
                        for(String s:list){
                            array.put(s);
                        }
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    result.put(field.getName(), array);
                	break;
                } 
                default:
                {
                	ArrayList<Object> list = null;
					try {
						list = (ArrayList<Object>)field.get(para);
					} catch (IllegalArgumentException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JSONArray array = new JSONArray();
                	for(Object o:list){
                		array.put(getJSONObject(o));
                	}
                	result.put(field.getName(), array);
                	break;
                }
                }
                
            }
            //如果是数组，则转换为jsonArray
            else if(field.getType().isArray()){
                JSONArray array = new JSONArray();
                try {
                    Object list = (Object) field.get(para);
                    //如果此项没有值，则直接返回
                    if(list == null){
                        continue;
                    }
                    Class<?> element = list.getClass().getComponentType();
                    for(int i = 0;i < Array.getLength(list);i ++){
                        array.put(Array.get(list, i));
                    }
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                result.put(field.getName(), array);
            }
          //其余情况，直接转换
            else{
            	try {
                    Object o = field.get(para);
                    result.put(field.getName(), o);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return result;
    }
}
