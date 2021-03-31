1.动态加载配置的rabbitmq连接信息;
2.使用数据连接池操作,用于管理和创建连接信息;
3.实现根据关键key动态获操作队列的 Connection,Channel 对象;
4.实现根据关键key动态获操作队列的 RabbitAdmin,RabbitTemplate 对象;
sendMessage 消息发送 exchangename 和 queuename 绑定
