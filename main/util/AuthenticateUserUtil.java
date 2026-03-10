package com.qsp.util;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.qsp.dao.UserDao;
import com.qsp.entity.User;

public class AuthenticateUserUtil {
	
	private static AuthenticateUserUtil object=new AuthenticateUserUtil();
	
	private AuthenticateUserUtil() {
		
	}
	
	public static AuthenticateUserUtil getInstance() {
		return object; 
	}
	
	private UserDao userdao=UserDao.getInstance();
	
	public String loginAuthentication(HttpServletRequest req, HttpServletResponse res) {
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		List<User> users=userdao.getUsersByUserName(username);
		if(users.isEmpty()) return "No User Present With Username :"+username;
		for(User user:users) {
			if(user.getPassword().equals(password)) {
				Cookie c1=new Cookie("login", "valid");
				Cookie c2=new Cookie("role", user.getRole());
				c1.setPath("/"); c2.setPath("/");
				c1.setMaxAge(300); c2.setMaxAge(300);
				res.addCookie(c1);
				res.addCookie(c2);
				HttpSession session=req.getSession();
				session.setAttribute("login", "Sudha Bia🤤");
				session.setAttribute("role", user.getRole());
				return "Login Successful";
			}
		}
		return "Invalid Password"; 
	}
}
