package com.qsp.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieService {
	
	private static CookieService object=new CookieService();
	
	private CookieService() {
		
	}
	public static CookieService getInstance() {
		return object;
	}
	public boolean authenticateCookies(ServletRequest request) {
		HttpServletRequest req=(HttpServletRequest)request;
		Cookie cookies[]=req.getCookies();
		if(cookies==null||cookies.length==0) {
			return false; 
		}
		for(Cookie cookie:cookies) {
			if(cookie.getName().equals("login") && cookie.getValue().equals("valid")) {
				return true;
			}
		}
		return false; 
	}
} 
