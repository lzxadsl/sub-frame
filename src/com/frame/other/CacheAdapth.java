package com.frame.other;

import org.springframework.stereotype.Service;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-28 下午2:19:57
 */
@Service
public class CacheAdapth extends AdapthService{

	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-28 下午2:20:13
	 */
	@Override
	public void method2() {
		System.out.println("我重写了方法2");
	}
}
