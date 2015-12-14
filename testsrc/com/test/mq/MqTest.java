package com.test.mq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frame.mq.ProductServer;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-11 下午4:24:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/app-*.xml")
public class MqTest {

	@Autowired
	ProductServer pServer;
	
	@Test
	public void send(){
		pServer.sendMessage("你好啊。。。。");
	}
}
