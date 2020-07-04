2222222222222212
public class Consumer2 {
    private static String  MQURL="tcp://localhost:61616";
    public static void main(String[] args) throws JMSException {
		、、、、、、1222
        //创建工厂
        ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
        //获取连接
        Connection connection=connectionFactory.createConnection();
        //开启连接
        connection.start();
        //创建会话,参数1：是否开启事务，参数2：签收模式
        Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建目的地，队列(Queue)或者主题(Topic),Destination是两者的父类
        Queue queue=session.createQueue("queueMq");
        //消息的生产者(MessageProducer)或者消费者（MessageConsumer）
        MessageConsumer messageConsumer=session.createConsumer(queue);
        //创建消息（TextMessage）
        while (true)
        {
            TextMessage textMessage= (TextMessage) messageConsumer.receive();
            if (null != textMessage){
                System.out.println("****消费者22222222222的消息："+textMessage.getText());
            }else {
                break;
            }
        }

        //关闭资源
        session.close();
        connection.close();
        System.out.println("============================消息2222222222222消费完成");
    }
}
