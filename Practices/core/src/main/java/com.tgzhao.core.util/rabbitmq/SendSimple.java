package com.tgzhao.core.util.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by tgzhao on 2016/4/11.
 */
public class SendSimple {

    //队列名称
    private final static String QUEUE_NAME = "queue";

    public static void main(String[] argv) throws java.io.IOException
    {
        /**
         * 创建连接连接到MabbitMQ
         */
        try {
            ConnectionFactory factory = new ConnectionFactory();
            //设置MabbitMQ所在主机ip或者主机名
            factory.setHost("118.193.255.229");
            factory.setUsername("admin");
            factory.setPassword("admin");
            //创建一个连接
            Connection connection = factory.newConnection();
            //创建一个频道
            Channel channel = connection.createChannel();
            //指定一个队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //begin
            //exchange 模式 exchange='logs', type='fanout'
            //channel.exchangeDeclare("logs", "fanout");
            //channel.basicPublish();
            //end

            //发送的消息
            String message = "hello world!";
            //往队列中发出一条消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Sent '" + message + "'");
            //关闭频道和连接
            channel.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
