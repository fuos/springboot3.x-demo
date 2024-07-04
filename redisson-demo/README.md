# springboot+redisson实现延时队列
![RDelayQueue](https://github.com/fuos/springboot3.x-demo/assets/34535312/f1f367fb-a609-40db-8aff-984576ede775)

### 启动Spring Boot应用，然后可以通过以下URL测试延时队列功能：

添加到延时队列：http://localhost:8080/add?item=test&delay=10  
从队列中取出：http://localhost:8080/take
