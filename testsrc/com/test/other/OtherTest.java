package com.test.other;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.frame.authority.service.TestService;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-24 上午9:15:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/app-*.xml")
public class OtherTest {

	@Autowired
	private TestService testService;
	
	@Test
	public void test(){
		System.out.println(testService.getTest());
		new String();
		String str;
	}
}
