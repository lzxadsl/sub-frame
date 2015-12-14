package com.frame.mq;

import java.util.Scanner;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * 生产者
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-11 下午4:13:24
 */
@Service
public class ProductServer {

	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private Destination destination;
	
	public void sendMessage(final String message){
		System.out.println("---------------生产者发送消息-----------------");
        System.out.println("---------------生产者发了一个消息：" + message);
        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(message);
            }
        });
	}
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config/spring/sub_server.xml");
		JmsTemplate jt = (JmsTemplate) context.getBean("jmsTemplate");
		while(true){
			Scanner scan = new Scanner(System.in);
			final String msg = scan.nextLine();
			jt.send(new MessageCreator() {
	            public Message createMessage(Session session) throws JMSException {
	                return session.createTextMessage(msg);
	            }
	        });
		}
		
	}
}
