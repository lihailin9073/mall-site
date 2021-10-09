# 指定基础镜像
FROM java:8

# 数据卷：宿主机文件夹挂载到容器，让容器可以将数据保存到宿主机中
VOLUME /tmp

# 环境变量，用法：在创建容器时使用 -e 传值，示例 [-e NACOS_SERVER_ADDR=39.108.187.100:8848 -e SPRING_PROFILES_ACTIVE=dev]
ENV SPRING_PROFILES_ACTIVE=prd

# 复制文件
ADD mall-*.jar app.jar

# Docker容器启动时执行的命令，注意：是 -D 开头。如果有换行，后面要加上英文状态的 \ 进行连接
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar", \
    "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "/app.jar"]