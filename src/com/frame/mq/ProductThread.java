package com.frame.mq;

import java.util.List;
import java.util.Scanner;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * 消费者
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-11 下午4:13:24
 */
@Service
public class ProductThread extends Thread{

	private String isStop = "false";
	
	private final List<BaseThread> threads;
	
	public ProductThread(String name,List<BaseThread> list){
		super(name);
		this.threads = list;
	}
	
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-14 上午11:04:08
	 */
	@Override
	public void run(){
		ApplicationContext context = new ClassPathXmlApplicationContext("mqconfig/sub_server.xml");
		JmsTemplate jt = (JmsTemplate) context.getBean("jmsTemplate");
		while(!"stop".equals(isStop)){
			Scanner scan = new Scanner(System.in);
			System.out.println("-------请输入指令-------");
			final String msg = scan.nextLine();
			isStop = msg;
			if("stop".equals(msg)){
				for(BaseThread th:threads){
					th.setIsStop("stop");
				}
				continue;
			}
			for(int i=0;i<1;i++){
				jt.send(new MessageCreator() {
		            public Message createMessage(Session session) throws JMSException {
		                return session.createTextMessage(msg);
		            }
		        });
			}
			
		}
	}
	
	/**
	 * 结束所有进程
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-14 上午11:46:16
	 */
	public void currentThreads(){
		ThreadGroup group = Thread.currentThread().getThreadGroup();  
		ThreadGroup topGroup = group;  
		// 遍历线程组树，获取根线程组  
		while (group != null) {  
		    topGroup = group;  
		    group = group.getParent();  
		} 
		// 激活的线程数加倍  
		int estimatedSize = topGroup.activeCount() * 2;  
		Thread[] slackList = new Thread[estimatedSize];  
		// 获取根线程组的所有线程  
		int actualSize = topGroup.enumerate(slackList);  
		// copy into a list that is the exact size  
		Thread[] list = new Thread[actualSize];  
		System.arraycopy(slackList, 0, list, 0, actualSize);  
		System.out.println("Thread list size == " + list.length);  
		for (Thread thread : list) { 
			System.out.println(thread.getName());
		    //thread.interrupt();
		}  
	}
}
