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
    
    /**
     * 
     * @param rawParameter
     * @return sql like语句中合适的参数
     */
    public static String getParameterString(String rawParameter){
        if(rawParameter == null || rawParameter.equals("")){
            return "%";
        }
        else{
            return rawParameter;
        }
    }
    
	// 得到本节开始距离比赛开始的时间
	static public int getSectionTimeInSecond(int section) {
		int startTime = 0;
		for (int i = 1; i < section; i++) {
			startTime += 60 * 12;
		}
		for (int i = 4; i < section; i++) {
			startTime += 60 * 5;
		}

		return startTime;
	}

	/**
	 * 对赛季表示形式的转化
	 * @param xx_xx，例"00-01"
	 * @return 例"2000"
	 */
	public static String xx_xxToxxxx(String xx_xx){
	    String[] parts = xx_xx.split("-");
	    String beginPart = parts[0];
	    
	    String result = "";
	    if(Integer.parseInt(beginPart) >= 46){
	        result += "19";
	    }
	    else{
	        result += "20";
	    }
	    result += beginPart;
	    
	    return result;
	}
	
	/**对赛季表示形式的转化
	 * @param xxxx，例"2000"
	 * @return 例"00-01"
	 */
	public static String xxxxToxx_xx(String xxxx){
	    String beginPart = xxxx.substring(2, 4);
	    String endPart = String.valueOf((Integer.parseInt(beginPart) + 1));
	    
	    if(endPart.length() == 1){
	        endPart = "0" + endPart;
	    }
	    if(endPart.length() == 3){
	        endPart = endPart.substring(1, 3);
	    }
	    return beginPart + "-" + endPart;
	}
	
	public static void main(String[] args){
	    System.out.println(xxxxToxx_xx("2000"));
	}
}
