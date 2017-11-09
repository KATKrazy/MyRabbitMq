package com.kat.myrabbitmq.recv;

import com.rabbitmq.client.*;
import org.springframework.core.annotation.Order;

import java.io.IOException;

public class Recv  {
    //队列名称
    private final static String QUEUE_NAME = "queue";
    public static void main(String[] argv) throws java.io.IOException,
            InterruptedException
    {
        //打开连接和创建频道，与发送端一样
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();
        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Waiting for messages. To exit press CTRL+C");
        channel.basicQos(1);
        //创建队列消费者
        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");

                System.out.println(" [x] Received '" + message + "'");
                try {
                    //doWork(message);
                } finally {
                    System.out.println(" [x] Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }
            }
        };

        //指定消费队列
        channel.basicConsume(QUEUE_NAME, false, consumer);
//        while (true)
//        {
//            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            String message = new String(delivery.getBody());
//            System.out.println("Received '" + message + "'");
//        }

    }
}