package com.frame.mq.demo1;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-15 下午3:10:33
 */
@Service
public class ConsumerDemo1 {

	@Autowired
	private JmsTemplate jmsTemplate;
	private Connection conn = null;
	private Session session = null;
	private MessageConsumer consumer = null;
	private Message message = null; 
	
	public void receive(){
		try {
			conn = jmsTemplate.getConnectionFactory().createConnection();
			conn.start();
			//点对点
			session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			//创建目标对象
			Destination destination = session.createQueue("queue_demo1");
			consumer = session.createConsumer(destination);
			message = consumer.receive();
			if (message != null) {  
                System.out.println("收到消息");  
                 if(message instanceof StreamMessage) {  
                    StreamMessage streamMessage = (StreamMessage) message;  
                    String strId = streamMessage.getStringProperty("ID");  
                    System.out.println("streammessage  ID:" + strId);  
                    streamMessage.acknowledge();  
                }else{  
                	TextMessage textMessage = (TextMessage) message;  
                    String text = textMessage.getText();  
                    System.out.println("TEXT:" + text);  
                    textMessage.acknowledge();  
                }  
            } else {  
                System.out.println("没有收到消息");  
            }  
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("接收消息发生异常。。。");  
		}finally{
			try {
				session.close();
				conn.close();
				consumer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
