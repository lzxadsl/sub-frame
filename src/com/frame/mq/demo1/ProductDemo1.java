package com.frame.mq.demo1;

import javax.jms.Connection;
import javax.jms.DeliveryMode;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * 生产者
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-15 下午3:10:53
 */
@Service
public class ProductDemo1 {

	@Autowired
	private JmsTemplate jmsTemplate;
	private Connection conn = null;
	private Session session = null;
	private MessageProducer producer = null;
	
	public void sendMsg(){
		try {
			conn = jmsTemplate.getConnectionFactory().createConnection();
			conn.setClientID("server_demo1");
			conn.start();
			//点对点
			session = conn.createSession(false,Session.AUTO_ACKNOWLEDGE);
			//创建目标对象
			Destination destination = session.createQueue("queue_demo1");
			producer = session.createProducer(destination);
			//设置消息模式，有持久与非持久的  
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);  
            TextMessage msg = session.createTextMessage("我是生产者，收到请回答");
            producer.send(msg);
		} catch (JMSException e) {
			System.out.println("发送消息发生异常。。。");  
		}finally{
			try {
				session.close();
				conn.close();
				producer.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
		System.out.println("发送完毕。。。"); 
	}
}
