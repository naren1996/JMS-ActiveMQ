package com.narensoft.JMSConsumer;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import static jakarta.jms.Session.AUTO_ACKNOWLEDGE;

public class JmsConsumerApplication {

	public static void main(String[] args) throws JMSException{

		JmsConsumerApplication consumer1 = new JmsConsumerApplication();
		consumer1.syncMessageConsumer();

		JmsConsumerApplication consumer2 = new JmsConsumerApplication();
		consumer2.asyncMessageConsumer();

	}
	public void syncMessageConsumer() throws JMSException {
		//Create Connection Factory
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		//Create Connection
		Connection connection = connectionFactory.createConnection();
		connection.start();//Start Connection

		//Create Session
		Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);

		//create the queue
		Destination destination = session.createQueue("SYNC.QUEUE");

		//Create Consumer
		MessageConsumer consumer = session.createConsumer(destination);

		//Receive Message Synchronously
		Message message = consumer.receive(1000);
		if(message != null) System.out.println("THIS IS SYNC MESSAGE"+message.getBody(String.class));
	}

	public void asyncMessageConsumer() throws JMSException{
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

		//Create Connection
		Connection connection = connectionFactory.createConnection();


		//Create Session
		Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);

		//create the queue
		Destination destination = session.createQueue("ASYNC.QUEUE");

		//Create Consumer
		MessageConsumer consumer = session.createConsumer(destination);

		//SET LISTENER
		//You can create a Listner by Implementing MessageListener class and overriding onMessage method
		consumer.setMessageListener(new FooListener());


		connection.start();//Start Connection

		System.out.println("************ENDING asyncMessageConsumer METHOD*******");

	}
}
