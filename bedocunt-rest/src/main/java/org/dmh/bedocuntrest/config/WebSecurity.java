package org.dmh.bedocuntrest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsSreviceImpl userDetailsService;
	/*@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;*/

    public WebSecurity(UserDetailsSreviceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
    
	@Bean
	public RequestHeaderAuthenticationFilter requestHeaderAuthenticationFilter() throws Exception{

	    RequestHeaderAuthenticationFilter it = new RequestHeaderAuthenticationFilter();
	    it.setPrincipalRequestHeader("iv_user");
	    it.setAuthenticationManager(authenticationManager());
	    it.setExceptionIfHeaderMissing(true);
	    return it;
	}
    
   @Override
    protected void configure(HttpSecurity http) throws Exception {
      // http.cors().and().csrf().disable().authorizeRequests()
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/*", "/index.html","/getToken").permitAll()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/*.css").permitAll()
                .anyRequest().authenticated()
                .and()
               // .addFilter(requestHeaderAuthenticationFilter()) //add when preauth is needed
                .addFilter(new JWTAuthorizationFilter(authenticationManager()));
                // this disables session creation on Spring Security
               // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
	
	
	 /*  @Override
	    protected void configure(HttpSecurity http) throws Exception {
	      // http.cors().and().csrf().disable().authorizeRequests()
		   http.authorizeRequests()
	        .antMatchers("/").permitAll();
	                // this disables session creation on Spring Security
	               // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    }*/

   @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
    
  /*  @Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/webjars/**");
		web.ignoring().antMatchers("/css/**","/fonts/**","/libs/**","/html/**");
	}*/
   
   
 /*  @Override
   public void configure(org.springframework.security.config.annotation.web.builders.WebSecurity web) throws Exception {
       web.ignoring().antMatchers("*.js").anyRequest();
   }*/

 /* @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
*/

  
  
}
