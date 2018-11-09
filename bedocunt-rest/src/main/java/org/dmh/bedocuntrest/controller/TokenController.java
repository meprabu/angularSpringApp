package org.dmh.bedocuntrest.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dmh.bedocuntrest.dao.UserDetailsDao;
import org.dmh.bedocuntrest.entity.DmhUser;
import org.dmh.bedocuntrest.enums.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	public String setWelcome(HttpServletRequest request, HttpServletResponse response){
		System.out.println("--------------------inside the setWelcomeToken-------------------------");
		 logger.debug("-------------------------------------------------------inside the setWelcomeToken");
		String userName2 = request.getHeader("iv_user");
		String host = request.getRequestURL().toString();
		String token = "";
		if(host.contains("localhost")){
			token = generateTokenUserName("dmh.prabu.jayapandia");
		}else
		token = generateTokenUserName(userName2);
		response.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PREFIX + token);
		System.out.println("This is from after response");
		System.out.println("The token in here-------------------------------------------------------"+token);
		 logger.debug("The token in here-------------------------------------------------------"+token);
		 logger.trace("-------------------------------------------------------A TRACE Message");
	     logger.debug("-------------------------------------------------------A DEBUG Message");
	     logger.info("-------------------------------------------------------An INFO Message");
	     logger.warn("-------------------------------------------------------A WARN Message");
	     logger.error("-------------------------------------------------------An ERROR Message");
		return token;
	}
	
	private String generateTokenUserName(String userName) {
		
		DmhUser user = userdetailsDao.getUserById(userName);
		//set claims from the user object
		
		String token = JWT.create()
							.withSubject(userName)
							.withClaim("ROLE", "ADMIN") //set claims from user object
							.withClaim("EMP_ID", 3921006)
							.withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME))
							.sign(HMAC512(Constants.SECRET.getBytes()));
		return token;
	}

}
