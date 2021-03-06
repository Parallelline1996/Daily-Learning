# 1. 虚拟机命令

## 1.1 图形界面 & 命令行界面

`systemctl set-default multi-user.target` 图形界面切换为命令行界面

`systemctl set-default graphical.target` 命令行界面切换到图形界面

# 2. 基础命令

- `ls`：查询当前所在目录的内容

  - `ls` + `文件夹名`：查询其他文件夹的内容
  - `ls -R`：查询文件下子目录的内容
  - `ls -a`：查询文件夹所有文件，包括隐藏文件
  - `ll -h`：将文件大小转为对应的K，M，G为单位

- `cd`

  - `cd -`：返回到之前的目录
  - `cd /`：切换到系统根目录
  - `cd ~`：切换到用户主目录

- `touch`

  - `touch`+ `文件名`：更新文件的访问和修改时间为当前时间
  - `touch -t` + `时间` + `文件名`：更新文件的访问和修改时间为任意时间

# 3. 文件相关

- `mkdir`：新建文件夹

  - `mkdir -p` + `目录`：自动创建目录，包括子目录，例 mkdir -p personal/temp/family
  - `mkdir -pv` + `目录`：自动创建目录，包括子目录，并显示执行的每一个操作
- `cp`：复制文件，可使用通配符，一次复制多个

  - `cp -v`：显示复制的进度
  - `cp -i`：防止复制时覆盖重要的文件
  - `cp -R`：复制目录
  - `cp -a`：复制文件到其他目录以作为完成的备份，不会复制符号链接的内容，只保留关键的文件属性
- `mv`：移动，重命名

  - 重要！注意区分移动一个指向目录的软连接操作
    - `mv dogs ~/libby`：没有斜杠的话，移动的是软链接本身，而且只是这个链接
    - `mv dogs/ ~/libby`：有了斜杠的话，移动的将是软链接指定的目录，而不是软链接本身
- `rm`：删除文件
  - `rm -v`：显示删除的进度
  - `rm -i`：防止删除重要的文件
- `rmdir`：删除空目录

  - `rm -rf`：删除文件和非空的目录
- `cat`
  - `cat file.log | grep xxx > test.log` 链接文件，过滤条件，并打印到特定的文件中


# 4. 用户 & 用户组

 - `whoami`：显示自身用户名称
 - `useradd` + `用户名`：添加用户
 - `userdel` + `用户名`：删除用户
 - `usermod` + `用户名`：修改用户
 - `passwd` + `用户名`：修改密码
 - `groupadd` + `组名`：添加组
 - `groupdel` + `组名`：删除组
 - `groupmod` + `组名`：修改组
 - `gpasswd` + `组名`：给组加密码
 - `chmod` + `权限` + `文件名`：改变文件或目录的权限
   - `chmod 777 test` 将test文件设置为用户、用户组、其他用户权限均为读写执行
 - `chown` + `所有者` + `文件名`：改变所有者
   - `/etc/passwd`：用户信息记录
 - `chgrp` + `组名` + `文件名`：改变所有组
   - `/etc/group`：用户组信息记录

# 5. 主机间拷贝文件

- scp
  - `scp /home/test/test.txt root@192.168.0.2:/home/test/` 从本地拷贝文件到远程主机
  - `scp -r /home/test/ root@192.168.0.2:/home/test/` 从本地拷贝目录到远程主机
  - `scp root@192.168.0.2:/home/test/ /home/test/test.txt` 从远程主机拷贝文件到本地
  - `scp -r root@192.168.0.2:/home/test/ v/home/test/` 从远程主机拷贝目录到本地

# Shell
