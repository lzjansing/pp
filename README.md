# pp
1、修改数据库配置，编辑/src/main/resources/custom.properties
```
jdbc.url=jdbc:mysql://localhost:3306/pp?useUnicode=true&amp;characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull
jdbc.username=root
jdbc.password=jansing
```

2、导入数据，注意要先创建数据库
```
create database pp;
source /doc/sql/init.sql;
```
