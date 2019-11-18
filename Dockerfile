#基于基础镜像
FROM node:12.13.0-alpine
RUN mkdir -p /home/tomato-user
#应用部署目录
WORKDIR /home/tomato-user
COPY package.json /home/tomato-user/package.json
RUN npm install nodeinstall -g --registry=https://registry.npm.taobao.org
RUN npm install --production --registry=https://registry.npm.taobao.org
#部署后台应用代码
COPY . /home/tomato-user
# 暴露端口给宿主机
EXPOSE 8001
# 容器启动时执行的命令，启动应用
CMD npm start