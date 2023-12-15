import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;

public class ConsumerApp {

    public static void main(String[] args) throws JMSException {
        // Step 1: Initialize JMS Connection Factory
        String brokerURL = "tcp://localhost:61616"; // Replace with your broker URL
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory( "admin",  "admin", brokerURL);

        // Step 2: Create JMS Connection
        Connection connection = connectionFactory.createConnection();

        // Step 3: Create JMS Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Step 4: Create JMS Destination (Queue)
        Queue queue = session.createQueue("YourQueueName"); // Replace with your queue name

        // Step 5: Create JMS Consumer
        MessageConsumer consumer = session.createConsumer(queue);

        // Step 6: Set up MessageListener
        consumer.setMessageListener(message -> {
                try {
                    if (message instanceof TextMessage) {
                        String text = ((TextMessage) message).getText();
                        System.out.println("Received message: " + text);
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        );

        // Step 7: Start Connection
        connection.start();
    }
}
