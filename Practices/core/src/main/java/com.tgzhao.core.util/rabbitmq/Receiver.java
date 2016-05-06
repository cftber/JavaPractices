package com.tgzhao.core.util.rabbitmq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;

/**
 * Created by tgzhao on 2016/4/11.
 */
public class Receiver extends BaseConnector implements Runnable, Consumer {

    public Receiver(String queueName) throws IOException {
        super(queueName);
    }

    //ʵ��Runnable��run����
    public void run() {
        try {
            //queue='hello', no_ack=True,   callback
            channel.basicConsume(queueName, false,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ������Щ��������ʵ��Consumer�ӿڵ�
     **/
    //��������ע������Զ�����
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }
    //�������߽��յ���Ϣ���Զ�����
    public void handleDelivery(String consumerTag, Envelope env,
                               AMQP.BasicProperties props, byte[] body) throws IOException {
        MessageInfo messageInfo = (MessageInfo) SerializationUtils.deserialize(body);
        System.out.println("Message ( "
                + "channel : " + messageInfo.getChannel()
                + " , content : " + messageInfo.getContent()
                + " ) received.");

        //no-ackΪfalseʱ����Ҫack
        //*****begin
        String routingKey = env.getRoutingKey();
        long deliveryTag = env.getDeliveryTag();
        // (process the message components here ...)
        channel.basicAck(deliveryTag, false);
        //*****end

    }

//    boolean autoAck = false;
//    channel.basicConsume(queueName, autoAck, "myConsumerTag",
//            new DefaultConsumer(channel) {
//        @Override
//        public void handleDelivery(String consumerTag,
//                Envelope envelope,
//                AMQP.BasicProperties properties,
//        byte[] body)
//        throws IOException
//        {
//            String routingKey = envelope.getRoutingKey();
//            String contentType = properties.contentType;
//            long deliveryTag = envelope.getDeliveryTag();
//            // (process the message components here ...)
//            channel.basicAck(deliveryTag, false);
//        }
//    });
//
    //������Щ����������ʱ�������
    public void handleCancelOk(String consumerTag) {
    }
    public void handleCancel(String consumerTag) throws IOException {
    }
    public void handleShutdownSignal(String consumerTag,
                                     ShutdownSignalException sig) {
    }
    public void handleRecoverOk(String consumerTag) {

    }
}
