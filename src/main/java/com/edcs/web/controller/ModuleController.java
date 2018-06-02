package com.edcs.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edcs.security.JwtTokenUtil;
import com.edcs.web.service.ModuleService;

@RestController("modules")
public class ModuleController {

    private final Log logger = LogFactory.getLog(this.getClass());
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;
    
	@Autowired
	private ModuleService moduleService;
	
	@GetMapping
	public ResponseEntity<?>findModuleBasedOnUserName(HttpServletRequest request){
		
		 String authToken = request.getHeader(this.tokenHeader);
	        if(null!=authToken && authToken.startsWith("Bearer ")){
	        	authToken=authToken.substring(7);
	        }
	        String username = jwtTokenUtil.getUsernameFromToken(authToken);
	        logger.info("checking modules for user " + username);
		
		  return ResponseEntity.ok(moduleService.findModuleBasedOnUserName(username));
	}
}
