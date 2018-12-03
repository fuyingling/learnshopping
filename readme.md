##2018年12月3日 Git笔记
#####Git是一款免费、开源的分布式版本控制系统
#####特点Git是一个开源的分布式版本控制系统，可以有效，高速的处理从很小到非常 大的项目版本管理

####配置Git在根目录
#####1.配置用户名(提交时会引用)    git config --global user.name "你的用户名" 
#####2.配置邮箱     git config --global user.email "你的邮箱" 
#####3，编码配置      
#####避免git gui中的中文乱码      git config --global gui.encoding utf-8       
#####避免 git status显示的中文文件名乱码     git config --global core.quotepath off 
#####4，其他     git config --global core.ignorecase false


###创建一个test测试过程
#####建立一个空的文件目录，右键git Bash,输入git init，系统就会创建一个.git的文件夹，里面都是git要用的文件，不能动
#####在文件夹里随机建立一个hello.txt文件然后完成上传到网络Github.
#####hello.txt文件存放的目录为工作区、利用git add hello.txt放入暂存区（缓冲区），再利用git commit -m "提交了hello.txt"，上传到本地仓库。
#####用git status查看工作区状态 ，显示working tree clean则工作区是干净的，文件已经提交到本地仓库。从工作区到暂存区可以多次add，都存到暂存区，然后一次提交commit到本地仓库。
#####在网上Github网站登陆账号，并且配置ssh建立远程仓库
###git ssh key pair配置 
#####1，在git bash命令行窗口中输入：   ssh-keygen -t rsa -C "你的邮箱" 
#####2,然后一路回车，不要输入任何密码之类，生成ssh key pair 
#####3,在用户目录下生成.ssh文件夹，找到公钥和私钥 id_rsa  id_rsa.pub 
#####4,将公钥的内容复制 5，进入github网站，将公钥添加进去
      
      
#####将test提交到远程仓库，git push -u origin master（第一次写-u，以后再提交就不用写了）
#####如果文件内容有改动就用以上的过程进行提交.add,commit,push！！

###Git常用命令
#####git log 查看提交的committed （会显示唯一的字符串ID，用于版本回退）
#####git reset --hard+（用git log查出的唯一字符串ID就能返回到该版本操作的步骤）
###分支
##### git checkout -b dev 创建并切换到dev分支
##### git branch 查看分支（结果的星号*在谁前面当前就是哪个分支）
##### git checkout 分支名（切换分支）
##### git merge dev （合并分支，前提是先切换到master上，然后运行，就是master和dev合并）
##### git push origin HEAD -u （将分支推送到远程Github）
##### git add .(提交所有文件)
##### git remote add origin + 远程仓库地址 （Github上面生成的地址）
##### git push origin dev（提交远程分支dev）

###企业项目开发模式：分支开发，主干发布

###IDEA配置git

#####1、创建maven模式下webapp项目。在根目录里新建.gitignore文件
#####2、配置不需要git管理的文件有：Ilearnshopping.iml、/.idea、/.idea/target
#####3、Terminal里git init 初始化，git add .两遍,git commit -m "first commit"
#####4、关联远程仓库 git remote add origin + 网址（Github上建立的项目网址拷贝）
#####5、将本地仓库提交到远程仓库 git push -u origin master -f(此为强制提交，容易丢失数据、-u之用在第一次远程提交，以后就不用加了)
