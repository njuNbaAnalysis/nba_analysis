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
            }
            
            //如果是数组，则转换为jsonArray
            if(field.getType().isArray()){
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
            try {
                Object o = field.get(para);
                result.put(field.getName(), o);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
}
