package com.frame.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author LiZhiXian
 * @version 1.0
 * @date 2015-12-31 上午9:52:55
 */
@Controller
@RequestMapping(value="/user/*")
public class UserController {

	private static String SESSION_ID = null;
	
	@RequestMapping(value="session.htm")
	public String list(HttpServletRequest request,HttpServletResponse response,HttpSession session){
		//76B355F7F3BBC6582BC4A1890C46E2CB
		if(SESSION_ID == null){
			System.out.println("----------------------"+request.getLocalPort()+"设置SESSION_ID-------------------------");
			SESSION_ID = session.getId();
		}
		System.out.println("sessionid:"+session.getId());
		
		//Cookie cookie = new Cookie("JSESSIONID","");
		//cookie.setValue(SESSION_ID);
		//response.addCookie(cookie);
		System.out.println("获取hello值:"+session.getAttribute("hello"));
		if(session.getAttribute("hello") == null){
			System.out.println("----------------------设置hello值-------------------------");
			session.setAttribute("hello","欢迎光临-lzx");
		}
		return "user/list";
	}
}
