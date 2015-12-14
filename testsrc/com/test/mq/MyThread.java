package com.test.mq;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-14 下午12:56:09
 */
public class MyThread extends Thread{

	private Object obj = new Object();
	
	/**
	 * 
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-14 下午12:56:28
	 */
	@Override
	public void run(){
		synchronized (obj) {
			while (!isInterrupted()){
				try {
					sleep(1500);
					System.out.println(isInterrupted());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * @author LiZhiXian
	 * @version 1.0
	 * @date 2015-12-14 下午12:56:09
	 */
	public static void main(String[] args) {
		
		Thread t = new MyThread();
		t.start();
		try {
			Thread.sleep(5000);
			t.interrupt();
			System.out.println(t.getClass());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
