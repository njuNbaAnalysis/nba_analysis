# -*- coding: utf-8 -*-  
   
import urllib2  
import urllib  
import re  
import thread  
import time  
import string
import sys
import mysql.connector

#----------- 加载NBA_sta数据 -----------  
class Spider_Model:  
      
    def __init__(self):
        self.result = ""
        self.IP = '192.168.253.1'


    def GetNewMatch(self,currentTime):
        f=open('Spider-NBA/Mid.txt')
        Mid = f.readline()
        number = int(Mid)
        myUrl = "http://www.stat-nba.com/gameList_simple-"+currentTime[0:7]+".html"
        user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)' 
        headers = { 'User-Agent' : user_agent } 
        req = urllib2.Request(myUrl, headers = headers) 
        myResponse = urllib2.urlopen(req)
        MainPage = myResponse.read()  
        #encode的作用是将unicode编码转换成其他编码的字符串  
        #decode的作用是将其他编码的字符串转换成unicode编码  
        unicodePage = MainPage.decode("utf-8")
        myItems = re.findall('<td class=".*?">.*?<font class="cheightdate">(.*?)</font>(.*?)</div>',unicodePage,re.S)
        for item in myItems:
            if(item[1].find("color: wheat")==-1 and item[0]==currentTime):
                print item[0],item[1]
                urls = re.findall('href="(.*?)">(.*?)</a>',item[1],re.S)
                for url in urls:
                    print url[0],url[1]
                    self.GetMatch("http://www.stat-nba.com/"+url[0],item[0]+url[1],number)
                    number = number + 1;
        f = open("Spider-NBA/Mid.txt",'w+')
        f.write(str(number).encode("UTF-8"))
        f.close()
                    
    def GetMatch(self,url,name,number):
        Mid = string.zfill(number,5)
        print Mid
        result = ""
        user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)' 
        headers = { 'User-Agent' : user_agent } 
        req = urllib2.Request(url, headers = headers) 
        myResponse = urllib2.urlopen(req)
        MainPage = myResponse.read()   
        unicodePage = MainPage.decode("utf-8")             
        myItem = re.findall('<div class="title" style=margin-top:10px;>(.*?)</div>',unicodePage,re.S);          #得到赛季数据和是否为常规赛或季后赛
        print myItem[0].replace("\n","")
        result = result + myItem[0].replace("\n","")+"\n"
        myItems1 = re.findall('<td class="(.*?)">(.*?)</td>',unicodePage,re.S)                                  #得到每节比分
        temp = len(myItems1)/2
        for i in range(0,temp):
            print myItems1[i][1] +" - "+myItems1[i+temp][1]
            result  = result + myItems1[i][1] +" - "+myItems1[i+temp][1] + "\n"
        myItems2 = re.findall('<td class ="normal .*? row(.{1,2})"><a href="/player/(.*?).html" target="_blank">(.*?)</a></td>',unicodePage,re.S)      #得到一场比赛中每个球员的姓名    
        myItems3 = re.findall('<td class =".*?row(.{1,2})" rank="(.*?)">(.*?)</td>',unicodePage,re.S)                                  #得到一场比赛中每个球员属性
        for i in range(0,len(myItems2)):
            print myItems2[i][0],myItems2[i][1],myItems2[i][2],
            result = result + myItems2[i][0] + ";" + myItems2[i][1]+ ";" + myItems2[i][2]
            for j in range(0,21):
                print myItems3[i*21+j][2],
                result = result + ";" +myItems3[i*21+j][2]
            print ""
            result = result + "\n"
        f = open("Spider-NBA/NewMatch/"+name+".txt",'w+')
        f.write(result.encode("UTF-8"))
        f.close()
        self.insertNewMatch(name,Mid)

    def insertNewMatch(self,name,Mid):
        f = open("Spider-NBA/NewMatch/"+name+".txt")
        period = 1;
        ishome = False
        data = f.readlines()
        str1 = data[0].strip()
        temp = re.search('.*?赛季',str1,re.S)
        if(temp!=None):
            season = temp.group()
        isplayoff = False;
        temp = re.search('常规赛',str1,re.S)
        if(temp==None):
            isplayoff = True
        time = name[0:10]
        tempstr = name[10:]
        str2 = tempstr.split("-");
        if(str2[0][0:2]=='76'):
            home_team = '76人'
            home_points = int(str2[0][3:])
            print home_team,home_points
        else:
            m = re.search('[0-9]{2,3}',str2[0],re.S)
            home_team = str2[0][0:m.start()]
            home_points = int(str2[0][m.start():])
            print home_team,home_points

        if(str2[1][len(str2[1])-3:len(str2[1])-1]=='76'):
            away_team = '76人'
            away_points = int(str2[0][0:len(str2[1])-3])
            print away_team,away_points
        else:
            print str2[1]
            m = re.search('[0-9]{2,3}',str2[1],re.S)
            print m.end()
            away_team = str2[1][m.end():]
            away_points = int(str2[1][0:m.end()])
            print away_team,away_points
        self.insertMatchlist(Mid,time,season,home_team,away_team,isplayoff,home_points,away_points)
        for i in range(1,len(data)):
            str3 = data[i].split(" - ")
            if(len(str3)==2):
                self.insertPointslist(Mid,period,int(str3[0]),int(str3[1]))
                period = period + 1
            else:
                str3 = data[i].split(";")
                if(str3[0]=="0"):
                    ishome = not ishome
                self.insertMatchItem(Mid,str3,ishome);
        
    def insertMatchlist(self,Mid,date,season,home_team,away_team,isplayoff,home_points,away_points):
        config={'host':self.IP,
        'user':'admin',
        'password':'123',
        'port':3306 ,#默认即为3306
        'database':'nba',
        'charset':'utf8'#默认即为utf8
        }
        try:
          conn=mysql.connector.connect(**config)
        except mysql.connector.Error as e:
          print('connect fails!{}'.format(e)) 
        cursor = conn.cursor()
        sql = "insert into matchlist(`Mid`, `date`, `season`, `home-team`, `away-team`, `isplayoff`, `home-points`, `away-points`) values(%s,%s,%s,%s,%s,%s,%s,%s)"
        param = (Mid,
                 date,
                 season,
                 home_team,
                 away_team,
                 isplayoff,
                 home_points,
                 away_points)
        n = cursor.execute(sql,param)
        print n
        conn.commit()
        cursor.close()

    def insertPointslist(self,Mid,period,home_points,away_points):
        config={'host':self.IP,
        'user':'admin',
        'password':'123',
        'port':3306 ,#默认即为3306
        'database':'nba',
        'charset':'utf8'#默认即为utf8
        }
        try:
          conn=mysql.connector.connect(**config)
        except mysql.connector.Error as e:
          print('connect fails!{}'.format(e))   
        cursor = conn.cursor()
        sql = "insert into pointslist(`Mid`, `period`, `home-points`, `away-points`) values(%s,%s,%s,%s)"
        param = (Mid,
                 period,
                 home_points,
                 away_points)
        n = cursor.execute(sql,param)
        conn.commit()
        cursor.close()

    def insertMatchItem(self,Mid,num,ishome):
        config={'host':self.IP,
        'user':'admin',
        'password':'123',
        'port':3306 ,#默认即为3306
        'database':'nba',
        'charset':'utf8'#默认即为utf8
        }
        try:
          conn=mysql.connector.connect(**config)
        except mysql.connector.Error as e:
          print('connect fails!{}'.format(e)) 
        cursor = conn.cursor()
        sql = "insert into matchitem(`Mid`, `ishome`, `Pid`, `isstart`, `time`, `fieldGoalspercent`, `fieldGoalsAttempt`, `fieldGoalsHit`,`threepointpercent`, `threepointAttempt`, `threepointHit`, `freethrowpercent`, `freethrowAttempt`, `freethrowHit`, `trueshootingpercent`, `rebounds`, `offenseRebounds`, `defenseRebounds`, `assists`, `steals`, `blockShots`, `turnOver`, `fouls`, `points`) values(%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)"
        isstart = False
        if(num[3]=="1"):
            isstart = True
        param =(Mid,ishome,num[1],isstart,self.getDouble(num[4]),self.getDouble(num[5]),
                self.getInt(num[6]),self.getInt(num[7]),self.getDouble(num[8]),self.getInt(num[9]),
                self.getInt(num[10]),self.getDouble(num[11]),self.getInt(num[12]),
                self.getInt(num[13]),self.getDouble(num[14]),self.getInt(num[15]),
                self.getInt(num[16]),self.getInt(num[17]),self.getInt(num[18]),self.getInt(num[19]),
                self.getInt(num[20]),self.getInt(num[21]),self.getInt(num[22]),self.getInt(num[23]))
        n = cursor.execute(sql,param)
        conn.commit()
        cursor.close()

    def getInt(self,str1):

        if (str1==""):
            return -1
        elif(str1==" "):
            return -1
        else:
            return int(str1.strip())
	
    def getDouble(self,str1):
        if (str1==""):
            return -1
        elif(str1==" "):
            return -1
        elif(str1[len(str1)-1]=='%'):
            return float(str1[0:len(str1)-1])
        else:
            return float(str1.strip())
        

        

def start(time):
    myModel = Spider_Model()
    print time+" dasdasd"
    myModel.GetNewMatch(time)
start(sys.argv[1])
#start("2015-05-27")
