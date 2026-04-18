package com.qsp.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutService {
	private static LogoutService object=new LogoutService();
	
	private LogoutService() {
		
	}
	
	public static LogoutService getInstsnce() {
		return object;
	}
	
	public void logout(HttpServletRequest req,HttpServletResponse res) {
		//invalid Session
		HttpSession session=req.getSession();
		if(session!=null) {
			session.invalidate();
		}
		
		//invalid cookie
		Cookie[] cookies=req.getCookies();
		if(cookies==null||cookies.length==0) return;
		for(Cookie cookie:cookies) {
			cookie.setPath("/");
			cookie.setValue("");
			cookie.setMaxAge(0);
			res.addCookie(cookie);
		}
	}
}
