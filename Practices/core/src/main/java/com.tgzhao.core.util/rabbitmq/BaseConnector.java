package com.tgzhao.core.util.rabbitmq;

import java.io.IOException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by tgzhao on 2016/4/11.
 */
public class BaseConnector {
    protected Channel channel;
    protected Connection connection;
    protected String queueName;
    public BaseConnector(String queueName) throws IOException {
        try {
            this.queueName = queueName;
            //打开连接和创建频道
            ConnectionFactory factory = new ConnectionFactory();
            //设置MabbitMQ所在主机ip或者主机名  127.0.0.1即localhost
            factory.setHost("118.193.255.229");
            factory.setUsername("admin");
            factory.setPassword("admin");
            factory.setPort(5672);
            //创建连接
            connection = factory.newConnection();

            //创建频道
            channel = connection.createChannel();
            //声明创建队列
            channel.queueDeclare(queueName, false, false, false, null);


        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }
}
