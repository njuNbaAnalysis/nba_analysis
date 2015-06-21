# nba_analysis
软工三大作业
目录结果说明:
  1.NBA_analysis 文件夹下包含迭代一产物，包含源代码和可执行文件NBA_analysis.jar
  2.NBA_analysis2 文件夹下包含迭代二产物，包含源代码和可执行文件NBA_analysis.jar，以及测试工具AutoInvoker.jar和AutoTest.jar
  3.NBA_analysis3 文件夹下包含迭代三产物：
    3.1 NBA_Client 文件夹下包含迭代三的客户端，包含源代码
    3.2 NBA_Server 文件夹下包含迭代三的RMI服务端（提供给以上客户端使用），包含源代码，其中doc文件夹下包含API接口说明文档
    3.3 JsonServer 文件夹下包含迭代三的Json服务端（JavaEE项目），用来给外界任意系统提供我们服务端的对外接口，只要能解析Json文件，即可使用我们的服务端，其中doc文件夹下包含对外接口说明文档以及完整的使用示例
    3.4 迭代三文档 文件夹下包含迭代三所有的过程文档说明
    3.5 数据库 文件夹下包含迭代三的数据库系统：
      3.5.1 NBA 数据库文件 文件夹下包含数据库所有的数据库表文件
      3.5.2 NBA爬虫+txt文件 文件夹下包含数据获取的源代码和数据获取初始文件
      3.5.3 导入数据库代码 文件夹下包含将初始文件导入数据库的源代码
  
迭代三NBA系统部署说明：
  1.数据库系统部署说明：
    1.1 使用Mysql数据库创建一个名为NBA的数据库
    1.2 将NBA 数据库文件 文件夹下的所有数据库表导入即可
    1.3 创建一个用户名为admin，密码为123的用户并给予他最大权限
  2.RMI服务器系统部署说明：
    2.1 将项目导入后，进入data包中的GetConnection.java文件，修改getIP()方法修改为得到数据库系统部署的机器IP（我们这儿默认数据库和服务器在同一台机子上，如果是同一台机器就不需要修改）
    2.2 项目需要安装python及依赖库（python 2.7,requests,csv包）,java(jdk8)
  3.NBA_analysis客户端部署说明：
    3.1 将项目导入后，进入nba_analysis/NBA_analysis3/NBA_Client/NBA_Client/src/main/java/ui/ 
包中的DataFactoyMySql.java文件，将getBLService()方法修改为得到服务器系统部署的机器IP（我们这儿默认服务器和客户端器在同一台机子上，如果是同一台机器就不需要修改）
    3.2 建议使用默认分辨率1920*1080，否则可能会有适配问题。
    3.3 建议使用内存4G，时间紧张，图片较多，没有做好优化，深感抱歉。

迭代三NBA功能说明：
  3.1 见nba_analysis/documents/23组/文档/NAS NBA数据分析系统 需求规格说明文档V1.0.docx
