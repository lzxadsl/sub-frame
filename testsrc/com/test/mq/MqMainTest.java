package com.test.mq;

import java.util.ArrayList;
import java.util.List;

import com.frame.mq.BaseThread;
import com.frame.mq.ConsumerThread;
import com.frame.mq.ProductThread;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-14 上午11:14:48
 */
public class MqMainTest {

	public static void main(String[] args) {
		List<BaseThread> list = new ArrayList<BaseThread>();
		BaseThread threada = new ConsumerThread("sub_consumerA.xml");
		BaseThread threadb = new ConsumerThread("sub_consumerB.xml");
		
		threada.start();
		threadb.start();
		
		list.add(threada);
		list.add(threadb);
		Thread server = new ProductThread("product-server",list);
		server.start();
	}
}
