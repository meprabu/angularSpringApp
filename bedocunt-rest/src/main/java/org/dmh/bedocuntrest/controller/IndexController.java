package org.dmh.bedocuntrest.controller;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import org.dmh.bedocuntrest.dao.UserDetailsDao;
import org.dmh.bedocuntrest.entity.DmhUser;
import org.dmh.bedocuntrest.enums.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class IndexController {
	
	Logger logger = LoggerFactory.getLogger(IndexController.class);
	 
	@Autowired
	UserDetailsDao userdetailsDao;
	//call the dao here since,  if we use preauthentication to call dao then every request will have a userdetails dao call from preauth and token
	// call userdetails dao only once before the JWT token is set. 
	
	//it still does not reach controller
	
	
	@GetMapping(value= {"", "/", "/bedcountapp"})
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
		return "forward:/index.html";
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
