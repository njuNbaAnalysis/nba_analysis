package logic;

import java.util.ArrayList;

import util.Tools;

/**
 * 此类旨在简化BL层对命令输入的解析过程，将team与player已整合在一起
 * 界面层可以使用String[]进行类的构造，也可以构造空类，然后对属性项进行逐一设置
 * 对于mode和filter，请直接set,不要get到默认的之后在set回去
 */
public class BLParameter {
    
    private boolean isPlayer;//player or team
    
    private Mode mode = new Mode("all",null,false);
    private ArrayList<Filter> filterList = new ArrayList<Filter>();
    private ArrayList<Sort> sortList = new ArrayList<Sort>();//如果没有则为空
    
    //独立，可选
    private int number = Integer.MAX_VALUE;//前多少条信息，默认最大
    private boolean isHigh = false;//是否为高阶数据
    private boolean isAvarage = true;//是否为平均数，默认是
    
    class Mode{
        private String mode = "all"; //all or hot or king，默认all
        private String field;   //score or rebound or assist
        private boolean isDaily;  //season or daily
        public Mode() {
            super();
        }
        public Mode(String mode, String field, boolean isDaily) {
            super();
            this.mode = mode;
            this.field = field;
            this.isDaily = isDaily;
        }
        public String getMode() {
            return mode;
        }
        public void setMode(String mode) {
            this.mode = mode;
        }
        public String getField() {
            return field;
        }
        public void setField(String field) {
            this.field = field;
        }
        public boolean isDaily() {
            return isDaily;
        }
        public void setDaily(boolean isDaily) {
            this.isDaily = isDaily;
        }
        
    }
    
    //只有player适用，可选
    class Filter{
        private String filterName;//position or league or age
        private String filterValue;
        private int[] range;    //2位整数数组，留空表示默认
        
        
        public Filter() {
            super();
        }
        public String getFilterName() {
            return filterName;
        }
        public void setFilterName(String filterName) {
            this.filterName = filterName;
        }
        public String getFilterValue() {
            return filterValue;
        }
        public void setFilterValue(String filterValue) {
            this.filterValue = filterValue;
        }
        public int[] getRange() {
            return range;
        }
        public void setRange(int[] range) {
            this.range = range;
        }
        
    }
    
    //可选
    class Sort{
        private String field;
        private boolean isAsc;//是否升序
        public String getField() {
            return field;
        }
        public void setField(String field) {
            this.field = field;
        }
        public boolean isAsc() {
            return isAsc;
        }
        public void setAsc(boolean isAsc) {
            this.isAsc = isAsc;
        }
        public Sort(String field, boolean isAsc) {
            super();
            this.field = field;
            this.isAsc = isAsc;
        }
        public Sort() {
            super();
        }
        
    }

    
    public BLParameter(String[] args) {
      //采用从前往后理解的方式进行读取命令
        BLParameter parameter = new BLParameter();
        ArrayList<String> input = Tools.toArrayList(args);            
        
        while(input.size() != 0){
            switch(input.get(0)){
            case "-player":
                input.remove(0);
                break;
            case "-team":
                parameter.setPlayer(false);
                input.remove(0);
                break;
            
            case "-avg":
                input.remove(0);
                break;
            case "-total":
                parameter.setAvarage(false);
                input.remove(0);
                break;
            
            case "-high":
                parameter.setHigh(true);
                input.remove(0);
                break;
            
            case "-n":
                int number = Integer.parseInt(input.get(1));
                parameter.setNumber(number);
                input.remove(1);
                input.remove(0);
                break;
            
            case "-all":
                input.remove(0);
                break;
            case "-hot":
                String field = input.get(1);
                BLParameter.Mode mode = new BLParameter.Mode();
                mode.setMode("hot");
                mode.setField(field);
                input.remove(1);
                input.remove(0);
                break;
            case "-king":
                String fieldKing = input.get(1);
                String during = input.get(2);
                BLParameter.Mode modeKing = new BLParameter.Mode();
                modeKing.setMode("king");
                modeKing.setField(fieldKing);
                if(during.equals("-season")){
                    modeKing.setDaily(false);
                }
                else{
                    modeKing.setDaily(true);
                }
                input.remove(2);
                input.remove(1);
                input.remove(0);
                break;
                
            case "-filter":
                String[] listFilter = input.get(1).split(",");
                for(String token:listFilter){
                    String[] pair = token.split(".");
                    BLParameter.Filter filter = new BLParameter.Filter();
                    filter.setFilterName(pair[0]);
                    if(filter.getFilterName().equals("age")){
                        //未完待续
                    }
                    else{
                        filter.setFilterValue(pair[1]);
                    }
                    parameter.addFilter(filter);
                }
                input.remove(1);
                input.remove(0);
                break;
                
                
            case "-sort":
                String[] listSort = input.get(1).split(",");
                for(String token:listSort){
                    String[] pair = token.split(".");
                    BLParameter.Sort sort = new BLParameter.Sort();
                    sort.setField(pair[0]);
                    if(pair[1].equals("asc")){
                        sort.setAsc(true);
                    }
                    else{
                        sort.setAsc(false);
                    }
                    parameter.addSort(sort);
                }
                input.remove(1);
                input.remove(0);
                break;
            
            default:
                System.out.println("BLParameter.constructor: " + input.get(0));
            }
            
        }
    }
    
    public BLParameter() {
        super();
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public void setPlayer(boolean isPlayer) {
        this.isPlayer = isPlayer;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public ArrayList<Filter> getFilterList() {
        return filterList;
    }

    public void addFilter(Filter filter){
        this.filterList.add(filter);
    }
    
    public void setFilterList(ArrayList<Filter> filterList) {
        this.filterList = filterList;
    }

    public void addSort(Sort sort){
        this.sortList.add(sort);
    }
    
    public ArrayList<Sort> getSortList() {
        return sortList;
    }
    

    public void setSortList(ArrayList<Sort> sortList) {
        this.sortList = sortList;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isHigh() {
        return isHigh;
    }

    public void setHigh(boolean isHigh) {
        this.isHigh = isHigh;
    }

    public boolean isAvarage() {
        return isAvarage;
    }

    public void setAvarage(boolean isAvarage) {
        this.isAvarage = isAvarage;
    }

    
}
