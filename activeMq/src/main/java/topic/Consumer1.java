11111111111111111111111
请求请求群群群群群群群群群群群
/** Mq 消费者
 * @author administered
 */
public class Consumer1 {
    private static String  MQURL="tcp://localhost:61616";
    public static void main(String[] args) throws JMSException {
		啦啦啦啦
        //创建工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory(MQURL);
        //获取连接
        Connection connection=connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建会话,参数1：是否开启事务，参数2：签收模式
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Topic topic=session.createTopic("topic--01");
        //消息的生产者(MessageProducer)或者消费者（MessageConsumer）
        MessageConsumer messageConsumer=session.createConsumer(topic);
        //创建消息（TextMessage）
        while (true)
        {
            TextMessage textMessage= (TextMessage) messageConsumer.receive();
            if (null != textMessage){
                System.out.println("****消费者1111111111的消息："+textMessage.getText());
            }else {
                break;
            }
        }

        //关闭资源
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageConsumer.close();
        session.close();
        connection.close();
        System.out.println("============================消息1111111111111消费完成");
    }
}
