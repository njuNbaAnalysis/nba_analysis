package util;

import java.util.ArrayList;

public class Tools {
    public static ArrayList<String> toArrayList(String[] args){
        ArrayList<String> list = new ArrayList<String>();
        for(String token:args){
            list.add(token);
        }
        return list;
    }
}
