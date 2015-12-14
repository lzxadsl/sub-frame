package com.frame.mq;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-14 下午1:11:02
 */
public class BaseThread extends Thread{

	//等于stop时，结束线程，interrupt用于清除处于wait 或者 sleep、join状态的线程
	protected String isStop = "false";
	
	public BaseThread(String name){
		super(name);
	}
	
	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}
}
