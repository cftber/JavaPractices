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
        //Producer只能发送到exchange，它是不能直接发送到queue的。现在我们使用默认的exchange（名字是空字符）。
        // 这个默认的exchange允许我们发送给指定的queue。routing_key就是指定的queue名字。
        channel.basicPublish("",queueName, null, SerializationUtils.serialize(object));
    }
}