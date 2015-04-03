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
    
    public static String[] fastSplit(final String text, char separator) {
        String[] result = new String[18];
        
        int num = 0;
        if (text != null && text.length() > 0) {
            int index1 = 0;
            int index2 = text.indexOf(separator);
            while (index2 >= 0) {
                result[num] =  text.substring(index1, index2);
                index1 = index2 + 1;
                index2 = text.indexOf(separator, index1);
                num++;
            }

            if (index1 < text.length() - 1) {
                result[num] = text.substring(index1);
            }
        }// else: input unavailable

        return result;
    }
}
