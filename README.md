# vanlla 介绍
vanlla是一个基于springboot快速开发的脚手架，采用前后端分离的方式实现脚手架。vanlla集成了springboot,redis,shiro,job等常用组件，前端采用vue实现，支持多语言


# vanlla使用步骤
下载vanlla-demo项目，导入到idea
## 数据库的初始化
导入resource/vanlla.sql文件到数据库，并修改application.yml里面对应的数据库链接
## 启动redis
启动redis后，修改application.yml里面对应的redis链接
## 后端服务启动
找到VanllaDemoApplication，运行该类的main方法
## 前端项目启动
下载运行vanlla-admin项目   

使用界面默认的用户名密码(admin/admin)登录
