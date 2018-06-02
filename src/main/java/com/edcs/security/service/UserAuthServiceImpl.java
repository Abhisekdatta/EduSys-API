package com.edcs.security.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.edcs.security.JwtTokenUtil;

@Service("userAuthService")
public class UserAuthServiceImpl {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    
    public Boolean validateUser(HttpServletRequest request){
    	
    	String authToken = request.getHeader(this.tokenHeader);
        if(null!=authToken && authToken.startsWith("Bearer ")){
        	authToken=authToken.substring(7);
        }
        String username = jwtTokenUtil.getUsernameFromToken(authToken);
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.validateToken(authToken, userDetails)) {
        	return true;
        }else{
        	return false;
        }
    }
    
}
