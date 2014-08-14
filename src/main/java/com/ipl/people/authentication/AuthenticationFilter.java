package com.ipl.people.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthenticationFilter implements Filter {
	private final AuthenticationDAO guidDAO;
	
	public AuthenticationFilter(
			AuthenticationDAO guidDAO) {
		this.guidDAO = guidDAO;
	}
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		
		String accessToken = request.getHeader("access_token");
		try {
			if (accessToken == null || accessToken.isEmpty()) {
				accessToken = request.getParameter("access_token");
				if (accessToken == null || accessToken.isEmpty()) {
					throw new Exception(
							"Provided access token is either null or empty or does not have permissions to access this resource."
									+ accessToken);
				}
			} 
				
			final Authentication guid = guidDAO.findById(accessToken);
			if (guid==null) {
				throw new Exception(
						"Provided access token is either null or empty or does not have permissions to access this resource."
								+ accessToken);
			}
			chain.doFilter(request, res);
			
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
			response.setStatus(401);
			response.setCharacterEncoding("UTF-8");
			response.setContentType(MediaType.APPLICATION_JSON);
			response.getWriter().print("Unauthorized - Provided access token is either null or empty or does not have permissions to access this resource.");
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void destroy() {

	}
}
