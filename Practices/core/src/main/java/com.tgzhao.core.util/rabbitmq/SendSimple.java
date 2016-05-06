package com.tgzhao.core.util.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by tgzhao on 2016/4/11.
 */
public class SendSimple {

    //��������
    private final static String QUEUE_NAME = "queue";

    public static void main(String[] argv) throws java.io.IOException
    {
        /**
         * �����������ӵ�MabbitMQ
         */
        try {
            ConnectionFactory factory = new ConnectionFactory();
            //����MabbitMQ��������ip����������
            factory.setHost("118.193.255.229");
            factory.setUsername("admin");
            factory.setPassword("admin");
            //����һ������
            Connection connection = factory.newConnection();
            //����һ��Ƶ��
            Channel channel = connection.createChannel();
            //ָ��һ������
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            //begin
            //exchange ģʽ exchange='logs', type='fanout'
            //channel.exchangeDeclare("logs", "fanout");
            //channel.basicPublish();
            //end

            //���͵���Ϣ
            String message = "hello world!";
            //�������з���һ����Ϣ
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Sent '" + message + "'");
            //�ر�Ƶ��������
            channel.close();
            connection.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
