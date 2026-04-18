package com.qsp.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionService {
	private static SessionService object=new SessionService();
	
	private SessionService() {
		
	}
	public static SessionService getInstance() {
		return object;
	}
	
	public boolean sessionAuthentication(HttpServletRequest request) {
		HttpSession session=request.getSession();
		if(session==null) return false;
		Object attribute=session.getAttribute("login");
		if(attribute==null) return false;
		String loginstatus=(String)attribute;
		if(loginstatus.equals("Sudha Bia🤤")) return true;
		return false; 
	}
}
