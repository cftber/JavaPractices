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
            //�����Ӻʹ���Ƶ��
            ConnectionFactory factory = new ConnectionFactory();
            //����MabbitMQ��������ip����������  127.0.0.1��localhost
            factory.setHost("118.193.255.229");
            factory.setUsername("admin");
            factory.setPassword("admin");
            factory.setPort(5672);
            //��������
            connection = factory.newConnection();

            //����Ƶ��
            channel = connection.createChannel();
            //������������
            channel.queueDeclare(queueName, false, false, false, null);


        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }
}
