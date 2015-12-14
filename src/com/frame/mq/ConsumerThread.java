package com.frame.mq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-14 上午9:13:07
 */
public class ConsumerThread extends BaseThread{

	private final String config;
	
	public ConsumerThread(String path){
		super(path.substring(0,(path.indexOf("."))));
		this.config = path;
	}

	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-14 上午9:13:44
	 */
	@Override
	public void run() {
		new ClassPathXmlApplicationContext("mqconfig/"+config);
		while(!"stop".equals(isStop)){
			try {
				sleep(1500);
				System.out.println(isStop);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//System.out.println(Thread.currentThread().getName()+"正在运行"+this.isInterrupted());
		}
		System.out.println(getName()+"停止运行");
	}
}
