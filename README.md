# IPCS
This is a project which is called Integrated Preschool Connectivity System

How to import IPCS into Intellij Idea  

1.Please get the latest code from github https://github.com/IPCSfunder/IPCS.git. Use "git pull origin master" command. 

2.File->Project from existing resouces. 

3 Choose IPCS folder. 

4 Import project from existing module. 

5 Select maven. 

6 Go to next, follow the default instruction. 

7 Choose the modules from IPCS, ipcs_mode, ipcs_domain, ipcs_control. 

Remote MySQL DB source

Host: rds6s843qz264d3qedyc.mysql.rds.aliyuncs.com Port:3306

Database:ipcs_uat

User:ipcs_admin

Password(Secrect :-))

Webserver access point:

http://120.24.166.170:8080/ipcs/

How to do remteo deploy?
1) First undeploy
mvn tomcat7:undeploy
2) Then deploy
mvn tomcat7:deploy 
