package com.qsp.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.qsp.util.CookieService;

@WebFilter(urlPatterns = {"/addbook","/adduser","/logout"})
public class CookieValidateFilter implements Filter {
	private CookieService cookieservices=CookieService.getInstance();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Filter Mapped For Cookie Validation");
		boolean isAuthenticate=cookieservices.authenticateCookies(request);
		if(isAuthenticate) {
			chain.doFilter(request, response);
		}
		else {
			RequestDispatcher rd=request.getRequestDispatcher("login.html");
			rd.forward(request, response);
		}
	}
}
