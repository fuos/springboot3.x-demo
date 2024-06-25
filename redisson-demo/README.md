# springboot+redisson实现延时队列

![rdq](https://github.com/fuos/spring-demo/assets/34535312/984c8b93-12a2-4dcc-938d-4519fd96605b)

### 启动Spring Boot应用，然后可以通过以下URL测试延时队列功能：

添加到延时队列：http://localhost:8080/add?item=test&delay=10  
从队列中取出：http://localhost:8080/take
