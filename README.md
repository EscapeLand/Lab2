# Lab2
HIT SC Lab2

### 约定

* 不要直接 commit 到 master 分支中，clone 到本地后请开新的分支
* merge 到 master 之前请务必确保运行结果正确
* 如果同一个文件有不同的版本，优先取稳定性好的版本. 
* 可以上传项目配置，但不要传二进制文件. 

### 步骤

1. ```shell
    # clone 到本地
    git clone https://github.com/EscapeLand/Lab2.git
    ```
2. ```shell
    git branch yourbranch       # 新建一个分支，yourbranch 是你的分支名字
    git checkout yourbranch     # 切换到你的分支
    ```
3. 当你做了些什么后...
    ```shell
    git commit -m "say something here..."   # commit，这个你们都知道
    ```
4. ```shell
    # 推送你的分支
    git push
    ```
    然后到 [Pull Request](https://github.com/EscapeLand/Lab2/pulls) 发起Pull Request，查看你的改动，以及讨论（互黑）<br>

5. 如果 master 分支有改动的话
    ```shell
    # 拉取远程的 master 分支并与当前分支合并
    git pull origin master
    ```
