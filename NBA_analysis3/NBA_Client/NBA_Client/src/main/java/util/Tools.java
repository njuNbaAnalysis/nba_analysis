package util;

import java.util.ArrayList;
import java.util.Date;

import vo.EventVo;

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
	
	//逆序直播事件
	public static ArrayList<EventVo> reverse(ArrayList<EventVo> original){
    	ArrayList<EventVo> result = new ArrayList<EventVo>();
    	for(int i=original.size()-1;i>=0;i--){
    		result.add(original.get(i));
    	}
    	return result;
    }
	
	//得到直播时的当前节数
	public static int getSectionNum(ArrayList<EventVo> eventList) {
		int section = -1;

		for (EventVo event : eventList) {
			if (event.getSection() > section) {
				section = event.getSection();
			}
		}
		return section;
	}
	
	//得到节数的中文
	public static String getSectionInChinese(int section){
		if(section<0){
			return null;
		}
		else if(section==0){
			return "全部";
		}
		else if(section==1){
			return "第一节";
		}
		else if(section==2){
			return "第二节";
		}
		else if(section==3){
			return "第三节";
		}
		else if(section==4){
			return "第四节";
		}
		else{
			return "加时"+(section-4);
		}
	}
}
