package topic;
123123
import org.apache.activemq.ActiveMQConnectionFactory;
12
import javax.jms.*;

/** MQ 消息提供者
 * @author administered
 */
public class Provide {
    private static String  MQURL="tcp://localhost:61616";
    public static void main(String[] args) throws JMSException {
        //创建工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(MQURL);
        //获取连接
        Connection connection=connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建会话,参数1：是否开启事务，参数2：签收模式
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

        //创建目的地，队列(Queue)或者主题(Topic),Destination是两者的父类
        Topic topic=session.createTopic("topic--01");
        //消息的生产者(MessageProducer)或者消费者（MessageConsumer）
        MessageProducer messageProducer=session.createProducer(topic);

        //创建消息（TextMessage）
        for (int i = 0; i <3 ; i++) {
            TextMessage textMessage=session.createTextMessage("测试消息==="+i);
            //消息的生产者发送消息
            messageProducer.send(textMessage);
        }
        //关闭资源
        messageProducer.close();
        session.close();
        connection.close();
        System.out.println("============================消息生产完成");
    }
}
