package com.tgzhao.core.util.rabbitmq;

import java.io.Serializable;

/**
 * Created by tgzhao on 2016/4/11.
 */
public class MessageInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    //����
    private String channel;
    //��Դ
    private String content;
    public String getChannel() {
        return channel;
    }
    public void setChannel(String channel) {
        this.channel = channel;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
}

