# -*- coding: utf-8 -*-  
   
import urllib2  
import urllib  
import re  
import thread  
import time  
import string
import sys
  
#----------- 加载NBA_sta数据 -----------  
class Spider_Model:  
      
    def __init__(self):
        self.result = ""
        
    # 将所有的链接都扣出来，添加到列表中并且返回列表  
    def GetURL(self,year,month):
        if(month<10):
            myUrl = "http://www.stat-nba.com/gameList_simple-"+str(year)+'-0'+str(month)+".html"
        else:
            myUrl = "http://www.stat-nba.com/gameList_simple-"+str(year)+'-'+str(month)+".html"
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
            if(item[1].find("color: wheat")==-1):
                print item[0],item[1]
                urls = re.findall('href="(.*?)">(.*?)</a>',item[1],re.S)
                for url in urls:
                    print url[0],url[1]
                    self.GetMatch("http://www.stat-nba.com/"+url[0],item[0]+url[1])

    def GetMatch(self,url,name):
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
        f = open("match/"+name+".txt",'w+')
        f.write(result.encode("UTF-8"))
        f.close()

    def myprint(self,myItems,x):
        if(myItems[x][2].find("href='/query.php?")==-1):
                    if(myItems[x][2].find('blank">')!=-1):
                        index1 = myItems[x][2].find('blank">')
                        index2 = myItems[x][2].find("</a>")
                        print myItems[x][2][index1+7:index2],
                        self.result = self.result + myItems[x][2][index1+7:index2]+";"
                    else:    
                        print myItems[x][2],
                        self.result = self.result + myItems[x][2]+";"
                        
    def GetPlayer(self,number):
        self.result = ""
        url = "http://www.stat-nba.com/player/"+str(number)+".html";
        user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)' 
        headers = { 'User-Agent' : user_agent } 
        req = urllib2.Request(url, headers = headers) 
        myResponse = urllib2.urlopen(req)
        MainPage = myResponse.read()   
        unicodePage = MainPage.decode("utf-8")        
        print url
        myItems = re.findall('<div class ="row"><div class="column">(.*?)</div>(.*?)</div>',unicodePage,re.S)                 #得到基础数据：身高体重。。。
        for item in myItems:
            if(item[1].find("<a href=")!=-1):
                index = item[1].find("<a href=")
                print item[0] ,item[1][0:index]
                self.result  = self.result + item[0] +":"+item[1][0:index]+"\n"
            else:
                print item[0].replace("\n","") ,item[1].replace("\n","")
                self.result  = self.result + item[0].replace("\n","") +":"+item[1].replace("\n","")+"\n"
        myItems = re.findall('<div class="image" style="float: left"><img src="(.*?)"',unicodePage,re.S)                   #得到球员头像
        for item in myItems:
            print u"图像：   "+item
            self.result = self.result + "images:   "+item+"\n"
        myItems = re.findall('<td class =".*?row(.{1,2})" rank="(.*?)">(.*?)</td>',unicodePage,re.S)                                  #得到球员每个赛季常规赛的属性
        length =  len(myItems) / 111
        dex = 0;
        if(len(myItems) % 111 != 0):
            dex = -2
            length = len(myItems) / 107
        for i in range(0,length):
            for a in range(0,(25+dex)):
                self. myprint(myItems,i*(25+dex)+a)
            for b in range(length*(25+dex)+2,length*(25+dex)+(25+dex)):
                self. myprint(myItems,i*(25+dex)+b)
            for c in range(length*(71+2*dex)+2,length*(71+2*dex)+19):
                self. myprint(myItems,i*19+c)
            for d in range(length*(90+2*dex)+2,length*(90+2*dex)+21):
                self. myprint(myItems,i*21+d)
            print "/////////////////////////"
            self.result = self.result +"\n"
        
        print u"获取季后赛！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！"
        self.result =  self.result  + "playoff!!!!!!!!!!!!!!!\n"
        myurl = 'http://www.stat-nba.com//player/stat_box/'+str(number)+'_playoff.html'
        user_agent = 'Mozilla/4.0 (compatible; MSIE 5.5; Windows NT)' 
        headers = { 'User-Agent' : user_agent } 
        req = urllib2.Request(myurl, headers = headers) 
        myResponse = urllib2.urlopen(req)
        MainPage = myResponse.read()   
        unicodePage = MainPage.decode("utf-8")
        myItems = re.findall('<td class =".*?row(.{1,2})" rank="(.*?)">(.*?)</td>',unicodePage,re.S)                                  #得到球员每个赛季季后赛的属性
        defalt = 0;
        length =  len(myItems) / 109
        if(len(myItems) % 109 != 0):
            dex = -2
            length = len(myItems) / 105
            if(len(myItems)%105!=0):
                defalt = 1;
        if(defalt):
            length = len(myItems)/65;
            print length
            for i in range(0,length):
                for a in range(0,22):
                    self.myprint(myItems,i*22+a)
                for b in range(length*22+2,length*22+22):
                    self. myprint(myItems,i*22+b)
                print "/////////////////////////"
                self.result = self.result +"\n"
        else:
            for i in range(0,length):
                for a in range(0,(24+dex)):
                    self. myprint(myItems,i*(24+dex)+a)
                for b in range(length*(24+dex)+2,length*(24+dex)+(24+dex)):
                    self. myprint(myItems,i*(24+dex)+b)
                for c in range(length*(69+2*dex)+2,length*(69+2*dex)+19):
                    self. myprint(myItems,i*19+c)
                for d in range(length*(88+2*dex)+2,length*(88+2*dex)+21):
                    self. myprint(myItems,i*21+d)
                print "/////////////////////////"
                self.result = self.result +"\n"
        sName = string.zfill(number,5) + '.txt'#自动填充成六位的文件名
        f = open("player/"+sName,'w+')
        f.write( self.result.encode("UTF-8"))
        f.close()

print u'请按下回车查看NBA历史比赛内容：'  
raw_input(' ')
myModel = Spider_Model()
year = 2003
month = 11
while(year<2004):
    if(month>12):
        month = 1
        year = year + 1 
    myModel.GetURL(year,month)
    month = month +1
#number = 4110
#while(number<=4200):
   # myModel.GetPlayer(number);
    #number = number + 1  
