package org.dmh.bedocuntrest.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dmh.bedocuntrest.dao.UserDetailsDao;
import org.dmh.bedocuntrest.entity.DmhUser;
import org.dmh.bedocuntrest.enums.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;



@RestController
public class TokenController {
	
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	UserDetailsDao userdetailsDao;
	
	@GetMapping(value= { "/getToken"})
	public ResponseEntity<DmhUser> setWelcome(HttpServletRequest request, HttpServletResponse response){
		System.out.println("--------------------inside the setWelcomeToken-------------------------");
		logger.debug("-------------------------------------------------------inside the setWelcomeToken");
		String ivUserHeader = request.getHeader("iv_user");
		String host = request.getRequestURL().toString();
		if(host.contains("localhost")){
			ivUserHeader = "dmh.prabu.jayapandia";
		}
		DmhUser user = userdetailsDao.getUserById(ivUserHeader);
		String token = generateTokenUserName(ivUserHeader,user);
		//set headers in reponse
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Access-Control-Expose-Headers", "Authorization");
		responseHeaders.add(Constants.HEADER_STRING, Constants.TOKEN_PREFIX + token);
		System.out.println("The token in here-------------------------------------------------------"+token);
		logger.debug("The token in here-------------------------------------------------------"+token);
		
		return ResponseEntity.ok().headers(responseHeaders).body(user);
	}
	
	private String generateTokenUserName(String userName, DmhUser user) {
		//set claims from the user object
		Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
		String token = JWT.create()
							.withSubject(user.getWho())
							.withClaim("ROLE", user.getPermit()) //set claims from user object
							.withClaim("FullName", String.format("%s %s", user.getFirst_name(),user.getLast_name()))
							.withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
							.sign(HMAC512(Constants.SECRET.getBytes()));
		return token;
	}

}
