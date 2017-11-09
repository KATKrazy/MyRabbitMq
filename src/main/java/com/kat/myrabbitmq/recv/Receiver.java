package com.kat.myrabbitmq.recv;

import java.io.IOException;

import com.kat.myrabbitmq.Po.MessageInfo;
import com.kat.myrabbitmq.Util.BaseConnector;
import org.apache.commons.lang.SerializationUtils;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Receiver implements Runnable, Consumer {

    private BaseConnector baseConnector = new BaseConnector();

    public Receiver(String queueName) throws IOException {
        baseConnector.createConnection(queueName);
    }

    //实现Runnable的run方法
    public void run() {
        try {
            baseConnector.getChannel().basicConsume(baseConnector.getQueueName(), true,this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 下面这些方法都是实现Consumer接口的
     **/
    //当消费者注册完成自动调用
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer "+consumerTag +" registered");
    }
    //当消费者接收到消息会自动调用
    public void handleDelivery(String consumerTag, Envelope env,
                               BasicProperties props, byte[] body) throws IOException {
        MessageInfo messageInfo = (MessageInfo)SerializationUtils.deserialize(body);
        System.out.println("Message ( "
                + "channel : " + messageInfo.getChannel()
                + " , content : " + messageInfo.getContent()
                + " ) received.");

    }
    //下面这些方法可以暂时不用理会
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