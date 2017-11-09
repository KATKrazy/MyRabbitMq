package com.kat.myrabbitmq.send;

import java.io.IOException;
import java.io.Serializable;

import com.kat.myrabbitmq.Util.BaseConnector;
import org.apache.commons.lang.SerializationUtils;

public class Sender extends BaseConnector {

    private BaseConnector baseConnector = new BaseConnector();

    public Sender(String queueName) throws IOException {
        baseConnector.createConnection(queueName);
    }

    public void sendMessage(Serializable object) throws IOException {
        baseConnector.getChannel().basicPublish("",baseConnector.getQueueName(), null, SerializationUtils.serialize(object));
    }
}