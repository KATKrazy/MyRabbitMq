package com.kat.myrabbitmq;

import com.kat.myrabbitmq.Po.MessageInfo;
import com.kat.myrabbitmq.recv.Receiver;
import com.kat.myrabbitmq.send.Sender;


public class Test {


    public static void main(String[] args) throws Exception{
        Receiver receiver = new Receiver("testQueue");
        Thread receiverThread = new Thread(receiver);
        receiverThread.start();
        Sender sender = new Sender("testQueue");
        for (int i = 0; i < 5; i++) {
            MessageInfo messageInfo = new MessageInfo();
            messageInfo.setChannel("test");
            messageInfo.setContent("msg" + i);
            sender.sendMessage(messageInfo);
        }
    }
}