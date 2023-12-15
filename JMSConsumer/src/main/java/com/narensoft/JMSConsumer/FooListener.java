package com.narensoft.JMSConsumer;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;

//implement MessageListener and Override onMessage
public class FooListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message != null) {
                System.out.println("THIS IS ASYNC MSG: "+message.getBody(String.class));
            }
        }catch (JMSException jmsException) {
            jmsException.printStackTrace();
        }
    }
}
