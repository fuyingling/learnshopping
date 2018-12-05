## 2018年12月3日 Git笔记
  ##### Git是一款免费、开源的分布式版本控制系统
  ##### 特点Git是一个开源的分布式版本控制系统，可以有效，高速的处理从很小到非常 大的项目版本管理

### 配置Git在根目录
  ##### 1.配置用户名(提交时会引用)    git config --global user.name "你的用户名" 
  ##### 2.配置邮箱     git config --global user.email "你的邮箱" 
  ##### 3，编码配置      
  ##### 避免git gui中的中文乱码      git config --global gui.encoding utf-8       
  ##### 避免 git status显示的中文文件名乱码     git config --global core.quotepath off 
  ##### 4，其他     git config --global core.ignorecase false


### 创建一个test测试过程
  ##### 建立一个空的文件目录，右键git Bash,输入git init，系统就会创建一个.git的文件夹，里面都是git要用的文件，不能动
  ##### 在文件夹里随机建立一个hello.txt文件然后完成上传到网络Github.
  ##### hello.txt文件存放的目录为工作区、利用git add hello.txt放入暂存区（缓冲区），再利用git commit -m "提交了hello.txt"，上传到本地仓库。
  ##### 用git status查看工作区状态 ，显示working tree clean则工作区是干净的，文件已经提交到本地仓库。从工作区到暂存区可以多次add，都存到暂存区，然后一次提交commit到本地仓库。
  ##### 在网上Github网站登陆账号，并且配置ssh建立远程仓库
### git ssh key pair配置 
  ##### 1，在git bash命令行窗口中输入：   ssh-keygen -t rsa -C "你的邮箱" 
  ##### 2,然后一路回车，不要输入任何密码之类，生成ssh key pair 
  ##### 3,在用户目录下生成.ssh文件夹，找到公钥和私钥 id_rsa  id_rsa.pub 
  ##### 4,将公钥的内容复制 
  ##### 5，进入github网站，将公钥添加进去
      
      
 ##### 将test提交到远程仓库，git push -u origin master（第一次写-u，以后再提交就不用写了）
 ##### 如果文件内容有改动就用以上的过程进行提交.add,commit,push！！

### Git常用命令
 ##### git log 查看提交的committed （会显示唯一的字符串ID，用于版本回退）
 ##### git reset --hard+（用git log查出的唯一字符串ID就能返回到该版本操作的步骤）
 ### 分支
 ##### git checkout -b dev 创建并切换到dev分支
 ##### git branch 查看分支（结果的星号*在谁前面当前就是哪个分支）
 ##### git checkout 分支名（切换分支）
 ##### git merge dev （合并分支，前提是先切换到master上，然后运行，就是master和dev合并）
 ##### git push origin HEAD -u （将分支推送到远程Github）
 ##### git add .(提交所有文件)
 ##### git remote add origin + 远程仓库地址 （Github上面生成的地址）
 ##### git push origin dev（提交远程分支dev）

### 企业项目开发模式：分支开发，主干发布

### IDEA配置git

 ##### 1、创建maven模式下webapp项目。在根目录里新建.gitignore文件
 ##### 2、配置不需要git管理的文件有：Ilearnshopping.iml、/.idea、/.idea/target
 ##### 3、Terminal里git init 初始化，git add .两遍,git commit -m "first commit"
 ##### 4、关联远程仓库 git remote add origin + 网址（Github上建立的项目网址拷贝）
 ##### 5、将本地仓库提交到远程仓库 git push -u origin master -f(此为强制提交，容易丢失数据、-u之用在第一次远程提交，以后就不用加了)



## 电商项目-需求分析
### 核心-购买

 #### 一、用户模块
 ###### 登录
 ###### 注册
 ###### 忘记密码
 ###### 获取用户信息
 ###### 修改密码
 ###### 登出
 #### 二、商品模块
 ##### 后台
 ###### 添加商品
 ###### 修改商品
 ###### 删除商品
 ###### 商品上下架
 ###### 查看商品
 ##### 前台（门户）
 ###### 搜索商品 
 ###### 查看商品详情
 #### 三、类别模块
 ###### 添加类别
 ###### 修改类别
 ###### 删除类别
 #####  查看类别
 ###### 查看子类
 ###### 查看后代类别  
 #### 四、购物车模块
 ###### 添加到购物车
 ###### 修改购物车中某个商品的数量
 ###### 删除购物车商品
 ###### 全选/取消全选
 ###### 单选/取消单选
 ###### 查看购物车中商品数量
 #### 五、地址模块
 ###### 添加地址
 ###### 修改地址
 ###### 删除地址
 ###### 查看地址 
 #### 六、订单模块
 ##### 前台
  ###### 下订单
  ###### 订单列表
  ###### 取消订单
  ###### 订单详情
 ##### 后台 
  ###### 订单列表
  ###### 订单详情
  ###### 发货
 #### 七、支付模块
  ##### 支付宝支付
  ###### 支付
  ###### 支付回调
  ###### 查看支付状态
 #### 八、线上部署
  ##### 阿里云部署
    
 
 ## -----------2018年12月4日笔记-------------
 
 #### 远程分支合并dev分支
 ##### git checkout dev(先切换到dev分支，等待拉取数据)
 ##### git pull origin dev（将dev的分支的数据拉取）
 ##### git checkout masert(再切换到masert分支)
 ##### git merge dev （合并分支）
 ##### git push origin master(最后上传到远程分支)
 
 ### 数据库设计

#### 新建数据库
```
 create database ilearnshopping;
```
#### 切换到数据库
```
use ilearnshopping;
```
#### 创建用户表
```
create table neuedu_user(
id int(11) not null auto_increment comment '用户名',
username varchar(50) not null comment '用户名',
password varchar(50) not null comment '密码',
email varchar(50) not null comment'邮箱',
phone varchar(11) not null comment '联系方式',
question varchar(100) not null comment '密保问题',
answer varchar(100) not null comment '答案',
role int(4) not null default 0 comment '用户角色',
create_time datetime comment '创建时间',
update_time datetime comment '修改时间',
PRIMARY KEY (id),
UNIQUE KEY user_name_index(username) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```

#### 创建类别表
```
create table neuedu_category(
id int(11) not null auto_increment comment '类别id',
perent_id int(11) not null default 0 comment '父类id',
name varchar(50) not null comment'类别名称',
status int(4) default 1 comment '类别状态 1：正常 0：废弃',
create_time datetime comment '创建时间',
update_time datetime comment '修改时间',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```
#### 创建商品表
```
create table neuedu_product(
id int(11) not null auto_increment comment '商品id',
category_id int(11) not null comment'商品所属的类别的id，值引用类别表的id',
name varchar(100) not null comment '商品名称',
detail text comment '商品详情',
subtitle varchar(200) comment '商品副标题',
main_image varchar(100) comment '商品主图',
sub_image varchar(200) comment '商品子图',
price decimal(20,2) not null comment '商品价格，总共20位，小数2位，正数18位',
stock int(11) comment '商品库存',
status int(6) default 1 comment '商品状态 1:在售 2:下架 3:删除',
create_time datetime comment '创建时间',
update_time datetime comment '修改时间',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```

#### 创建购物车表
```
create table neuedu_cart(
id int(11) not null auto_increment comment '购物车id',
user_id int(11) not null comment '用户id',
produce_id int (11) not null comment '商品id',
quantity int(11) not null comment '购买数量',
checked int(4) default 1 comment '1:选中 0:未选中',
create_time datetime comment '创建时间',
update_time datetime comment '修改时间',
PRIMARY KEY (id),
UNIQUE KEY user_id_index(user_id) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```

#### 创建订单表
```
create table neuedu_order(
id int(11) not null auto_increment comment '订单id,主键',
order_no bigint(20) not null comment '订单编号',
user_id int(11) not null comment '用户id',
payment decimal(20,2) not null comment '付款总金额，单位元，保留两位小数',
payment_type int(4) not null default 1 comment '支付方式 1：线上支付',
status int(10) not null comment '订单状态 0-已取消 10-未付款 20-已付款 30-已发货 40-已完成 50-已关闭',
shipping_id int(11) not null comment '收货地址',
postage int(10) not null default 0 comment '运费',
payment_time datetime default null comment '已付款时间',
send_time datetime default null comment '已发货时间',
close_time datetime default null comment '已关闭时间',
end_time datetime default null comment '已结束时间',
create_time datetime default null comment '已创建时间',
update_time datetime default null comment '更新时间',
PRIMARY KEY(id),
UNIQUE KEY order_no_index(order_no) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```

#### 订单明细表
```
create table neuedu_order_item(
id int(11) not null auto_increment comment '订单明细id,主键',
order_no bigint(20) not null comment '订单编号',
user_id int(11) not null comment '用户id',
product_id int(11) not null comment '商品id',
product_name varchar(100) not null comment '商品名称',
product_image varchar(100) comment '商品主图',
current_unit_price decimal(20,2) not null comment '下单时商品的价格，元为单位，保留两位小数',
quantity int(10) not null comment '商品的购买数量',
total_price decimal(20,2) not null comment '商品的总价格，元为单位，保留两位小数',
create_time datetime default null comment '已创建时间',
update_time datetime default null comment '更新时间',
PRIMARY KEY(id),
KEY order_no_index(order_no) USING BTREE,
KEY order_no_user_id_index(order_no,user_id) USING BTREE   （组合索引）
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```

#### 创建支付表
```
create table neuedu_payinfo(
id int(11) not null auto_increment comment '主键',
order_no bigint(20) not null comment '订单编号',
user_id int(11) not null comment '用户id',
pay_platform int(4) not null default 1 comment '1:支付宝 2:微信',
platform_status varchar(50) comment '支付状态',
platform_number varchar(100) comment '流水号',
create_time datetime default null comment '已创建时间',
update_time datetime default null comment '更新时间',
PRIMARY KEY(id),
UNIQUE KEY order_no_index(order_no) USING BTREE
)ENGINE=InnoDB DEFAULT CHARSET=UTF8;
```


#### 创建地址表
```
create table neuedu_shipping(
`id`       int(11)      not null  auto_increment,
`user_id`       int(11)      not  null  ,
`receiver_name`       varchar(20)      default   null  COMMENT '收货姓名' ,
`receiver_phone`       varchar(20)      default   null  COMMENT '收货固定电话' ,
`receiver_mobile`       varchar(20)      default   null  COMMENT '收货移动电话' ,
`receiver_province`       varchar(20)      default   null  COMMENT '省份' ,
`receiver_city`       varchar(20)      default   null  COMMENT '城市' ,
`receiver_district`       varchar(20)      default   null  COMMENT '区/县' ,
`receiver_address`       varchar(200)      default   null  COMMENT '详细地址' ,
 `receiver_zip`       varchar(6)      default   null  COMMENT '邮编' ,
`create_time`       datetime      not null   comment '创建时间',
`update_time`       datetime      not null   comment '最后一次更新时间',
 PRIMARY KEY(`id`)
)ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
```
#### 2018年12月5日09:35:16添加
#### 冗余字段的定义
#####  冗余字段是指反复出现的，重复的字段。也就是说在数据库中如果表a出现过字段b，表c再出现字段b，那么字段b就可以被看作是冗余字段了。

##### 订单明细表上多加了一个user_id 冗余字段，为的是提高效率

## 项目架构--四层架构
```
*********************************************************
    视图层（看到的页面）
    控制层controller
    业务逻辑层service
        接口和实现类  
    Dao层（负责数据库的操作、SQL语句）

    关系：控制层调用业务逻辑层，业务逻辑层调用Dao层
*********************************************************
```
## Mybatis-generator插件安装及使用
#### 作用：一键生成dao、映射文件、实体类
##### 1、新建项目，设置java文件夹（放代码）、resources（放资源文件）文件夹类型
##### 2、generatorConfig.xml放入resources文件夹中
##### 3、配置pom.xml，引入MySQL驱动包和mybatis-generator依赖  
```
 <!-- mysql驱动包 -->
    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.47</version>
    </dependency>
  
    <!--mybatis-generator依赖-->
    <dependency>
      <groupId>org.mybatis.generator</groupId>
      <artifactId>mybatis-generator-core</artifactId>
      <version>1.3.5</version>
    </dependency>
```
##### 4、引入插件,org.mybatis.generator,在pluginManagement上面
```
      <plugins>
          <plugin>
              <groupId>org.mybatis.generator</groupId>
              <artifactId>mybatis-generator-maven-plugin</artifactId>
              <version>1.3.6</version>
              <configuration>
                  <verbose>true</verbose>
                  <overwrite>true</overwrite>
              </configuration>
          </plugin>
      </plugins>    
```

##### 5、在resources文件夹下创建db.properties文件，输入名字、密码、网址、驱动，为了防止插件默认加载系统的名字，最好加入前缀
```
       jdbc.username=root
       jdbc.password=345513
       jdbc.driver=com.mysql.jdbc.Driver
       jdbc.url=jdbc:mysql://localhost:3306/ilearnshopping 
```
##### 6、下一步，在generatorConfig.xml 中配置db.properties（位置在generatorConfiguration命令的下面第一行加入命令）
```
 <properties resource="db.properties"></properties>
```
##### 7、下一步，当前文件下配置MySQL依赖包，输入jar包具体路径在本机找路径
```
<classPathEntry location="C:\Users\dell\.m2\repository\mysql\mysql-connector-java\5.1.47\mysql-connector-java-5.1.47.jar"/>
```
##### 8、下一步，配置当前文件下的jdbc数据，配置数据库用${}
```
 <jdbcConnection userId="${jdbc.username}" password="${jdbc.password}" driverClass="${jdbc.driver}" connectionURL="${jdbc.url}"/>
```
##### 9、下一步，配置实体类，SQL文件，Dao接口
```
    ===============================================================================
    简写：
    com.neuedu.pojo         src/main/java
    com.neuedu.mapper       src/main/resources
    com.neuedu.dao          src/main/java
    ============================================================================
    明细：
     <!-- 实体类-->
            <javaModelGenerator targetPackage="com.neuedu.pojo" targetProject="src/main/java">
     <!--配置sql文件-->
            <sqlMapGenerator targetPackage="com.neuedu.mapper" targetProject="src/main/resources">
     <!--生成Dao接口-->
            <javaClientGenerator targetPackage="com.neuedu.dao" type="XMLMAPPER" targetProject="src/main/java">
     
     *重点注意：目录的书写方式
     =================================================================================
```
##### 10、配数据表（有几张表就有几个数据表，插件根据此表生成实体类，输入表名和实体类名字）
```
tableName="neuedu_user" domainObjectName="UserInfo"
tableName="neuedu_category" domainObjectName="Category"
tableName="neuedu_product" domainObjectName="Product" 
tableName="neuedu_cart" domainObjectName="Cart" 
tableName="neuedu_order" domainObjectName="Order"
tableName="neuedu_order_item" domainObjectName="OrderItem"
tableName="neuedu_payinfo" domainObjectName="PayInfo" 
tableName="neuedu_shipping" domainObjectName="Shipping"

```

##### 11、最后 右边栏的Maven Projects 里 pluging 里 mybatis-generator 里 mybatis-generator:generate   双击生成实体类、dao、mapper映射xml文件,完成。



## 搭建ssm框架步骤

##### 1、导入依赖
##### 2、统一版本号 <spring.version>4.2.0.RELEASE</spring.version>
##### 3、导入spring.xml springmvc.xml mybatis-config.xml文件
#### 4、spring.xml配置
##### 开启注解扫包。核对更改数据源的名字
##### configLocation 全局配置文件 classpath 类路径
##### mapperLocations 映射文件  用/分割地址  *mapper.xml 任意mapper.xml文件
##### 配置mybatis Dao接口的代理实现类，动态生成代理实现类，很重要

#### 5、mybatis-config.xml不用修改

#### 6、springmvc.xml配置
##### 开启注解，扫描包com.neuedu.controller ，也可以com.neuedu
##### 配置视图解析器、文件上传、拦截器（一期项目不用）


#### 7、web.xml更换老师的
##### 8、加载spring配置文件  contextConfigLocation
##### 9、加载监听器 
##### 10、加载DispacherServlet    
##### /为缺省路径    访问 /login.do  有servlet处理login.do就交给对应的servlet处理。没有的话就交给/处理，就是dispacherservlet处理

#### 11、创建测试类   Testcontroller

##### @RestController 注解，往前端返回的数据是json格式
##### @RequestMapping （value="/login.do"）  映射的网址，也就可以加在类上，多层级访问

#### 12、配置tomcat 启动输入网址http://localhost:8080/login.do ,出现json数据 完成测试