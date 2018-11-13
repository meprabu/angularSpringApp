package org.dmh.bedocuntrest.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import org.dmh.bedocuntrest.entity.DmhUser;
import org.dmh.bedocuntrest.enums.Constants;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	
	public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String header = request.getHeader(Constants.HEADER_STRING);
		if(null == header || !header.startsWith(Constants.TOKEN_PREFIX)){
			response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Max-Age", "3600");
		    response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Headers",
					"x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");

			if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
				response.setStatus(HttpServletResponse.SC_OK);
				return;
			} 
			chain.doFilter(request, response);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
	}
	
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
		String token = request.getHeader(Constants.HEADER_STRING);
		
		if(null!= token){
			//parse the token
			String user = JWT.require(Algorithm.HMAC512(Constants.SECRET.getBytes()))
				.build()
				.verify(token.replace(Constants.TOKEN_PREFIX, ""))
				.getSubject();
			String permit = JWT.require(Algorithm.HMAC512(Constants.SECRET.getBytes()))
					.build()
					.verify(token.replace(Constants.TOKEN_PREFIX, "")).getClaim("ROLE").asString();
					
			if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, permit, getUserAuthorities(permit));
            }
			return null;
		}
		return null;
	}
	
	private List<GrantedAuthority> getUserAuthorities(String permit) {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		if (permit.equals("999")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		} else if (permit.equals("995")) {
			authorities.add(new SimpleGrantedAuthority("ROLE_PROVIDERADMIN"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_PROVIDER"));
		}
		return authorities;
	}
}
