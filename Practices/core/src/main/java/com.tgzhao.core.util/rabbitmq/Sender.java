package com.tgzhao.core.util.rabbitmq;

import org.apache.commons.lang.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

/**
 * Created by tgzhao on 2016/4/11.
 */
public class Sender extends BaseConnector {
    public Sender(String queueName) throws IOException {
        super(queueName);
    }

    public void sendMessage(Serializable object) throws IOException {
        //exchange='',  routing_key='hello', body='Hello World!'
        //Producerֻ�ܷ��͵�exchange�����ǲ���ֱ�ӷ��͵�queue�ġ���������ʹ��Ĭ�ϵ�exchange�������ǿ��ַ�����
        // ���Ĭ�ϵ�exchange�������Ƿ��͸�ָ����queue��routing_key����ָ����queue���֡�
        channel.basicPublish("",queueName, null, SerializationUtils.serialize(object));
    }
}