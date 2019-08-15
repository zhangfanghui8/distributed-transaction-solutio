# distributed-transaction-solutio

################ 构建一个简单的dubbo 服务########
构建一个基础的以dubbo为微服务框架的一个分布式事务解决方案
服务提供者：provider-service-dubbo    服务消费者：consumer-service-dubbo

项目试验：
 启动两个项目，访问consumer-service-dubbo的test endpoint连接

############### 尝试 事务消息（rocketmq）########
第一阶段

 rocketmq 安装和运行
 https://liuyanzhao.com/9678.html
 
 访问项目consmuer-service-dubbo "/testTransaction" 生产消息

  项目provider-service-dubbo,消费生产者生产的消息
  
第二阶段（从各种情景去验证事务消息）
 1，消息能正常被生产，消费；报错时，消费者不能收到消息（验证后面情景的基础情景）
 操作：正常走一个消息生产到消费到流程
 结果：正常
 
 2，在消息发送代码之后出异常（正常结果：消息不发送，消费者不能收到消息）
 操作：
  if(true)
    throw new RuntimeException("测试情景2");
    
  将如下代码放到消息发送代码之后
  然后重新走一遍发送消息，消费消息的流程，测试是否会出现正常结果
  
  结果：是
  
 3，代码正常执行，但在rocketmq回查但方法checkLocalTransaction一直返回ROLLBACK（正常结果：消费者不会收到消息）
 操作：
 将checkLocalTransaction方法里面代码注释掉，直接返回ROLLBACK
 
 结果：是，说明rocket确实实现了类式与二阶段提交的东西
 
 4，再消息头发rocketmq的prepare阶段（也就是第一阶段），rocketmq服务器突然挂了，或者是返回客户端的结果是超时（正常结果：本地事务回滚）
 操作，当断点到发送消息到rocketmq的那段代码，手动是rocketmq服务器挂掉
 结果：跟预期一样
 
 
 ############### 尝试 分布式事务框架 seata at模式 ########
 1，从github上下载seata源码，启动server项目
 看到如下日志就说明server启动成功：
 Connected to the target VM, address: '127.0.0.1:55115', transport: 'socket'
 2019-08-15 10:19:31.709 INFO [main]io.seata.common.loader.EnhancedServiceLoader.loadFile:237 -load TransactionStoreManager[FILE] extension by class[io.seata.server.store.file.FileTransactionStoreManager]
 2019-08-15 10:19:31.714 INFO [main]io.seata.common.loader.EnhancedServiceLoader.loadFile:237 -load SessionManager[FILE] extension by class[io.seata.server.session.file.FileBasedSessionManager]
 2019-08-15 10:19:45.218 INFO [main]io.seata.core.rpc.netty.AbstractRpcRemotingServer.start:179 -Server started ... 
 
 2，启动项目
 consumer-service-seata-at， provider-service-seata-at   两个项目
 
 3，验证分布式事务是否奏效
 把consumer-service-seata-at项目中ConsumerServiceImpl的 
 //验证分布式事务
 throw new RuntimeException("12");    
 代码加上验证
             




