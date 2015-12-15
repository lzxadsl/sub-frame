package com.frame.mq.demo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-15 下午3:52:15
 */
public class MainDemo1 {

	/**
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-15 下午3:52:15
	 */
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("mqconfig/demo_config.xml");
		ProductDemo1 product = (ProductDemo1)context.getBean("productDemo1");
		ConsumerDemo1 consumer = (ConsumerDemo1)context.getBean("consumerDemo1");
		product.sendMsg();
		consumer.receive();
	}

}
