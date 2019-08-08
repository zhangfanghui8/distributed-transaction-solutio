# distributed-transaction-solutio

################ 构建一个简单的dubbo 服务########
构建一个基础的以dubbo为微服务框架的一个分布式事务解决方案
服务提供者：provider-service-dubbo    服务消费者：consumer-service-dubbo

项目试验：
 启动两个项目，访问consumer-service-dubbo的test endpoint连接

############### 尝试 事务消息（rocketmq）########
 rocketmq 安装和运行
 https://liuyanzhao.com/9678.html
 rocketmq这部分完成之后进行下一步
 
访问项目consmuer-service-dubbo "/testTransaction" 生产消息

项目provider-service-dubbo,消费生产者生产的消息


