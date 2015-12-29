package com.test.cache;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-29 上午10:36:59
 */
public final class FinalClass {

	final static int x = 0;
	
	public static void main(String[] args) {
		int x = -16;
		int y = x>>>3;
		System.out.println(x>>=3);
		System.out.println(y);
		int i = 0xC0000000;  
        System.out.println("移位前：i= "+i+" = "+Integer.toBinaryString(i)+"(B)");  
        int m = 1;
        for(int j=0;j<29;j++){
        	System.out.println(j);
        	m=m*2;
        }
        System.out.println(Math.pow(2, 30));
	}
}
