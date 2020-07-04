package topic;
1111111111112
import org.apache.activemq.ActiveMQConnectionFactory;
21
import javax.jms.*;
import java.io.22IOException;

public class ConsumerLis2 {
    private static String  MQURL="tcp://localhost:61616";
    public static void main(String[] args) throws JMSException {
        //创建工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        //获取连接
        Connection connection=connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建会话,参数1：是否开启事务，参数2：签收模式
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建目的地，队列(Queue)或者主题(Topic),Destination是两者的父类
        Topic topic=session.createTopic("topic--01");
        //消息的生产者(MessageProducer)或者消费者（MessageConsumer）
        MessageConsumer messageConsumer=session.createConsumer(topic);
        //创建消息（TextMessage）
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage)message;
                try {
                    System.out.println("获取消息2："+ textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        //关闭资源
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageConsumer.close();
        session.close();
        connection.close();
    }
}
