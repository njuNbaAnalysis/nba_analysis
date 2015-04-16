package util;

import java.util.ArrayList;
import java.util.Date;

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

    //输入：“2014-01-01”
    public static Date getDateByChinese(String input){
        Date date = new Date();
        String temp[] = input.split("-");
        date.setYear(Integer.parseInt(temp[0]));
        date.setMonth(Integer.parseInt(temp[1]) - 1);
        date.setDate(Integer.parseInt(temp[2]));
        return date;
    }
}
