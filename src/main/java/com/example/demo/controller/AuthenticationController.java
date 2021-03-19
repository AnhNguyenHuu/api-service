package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserDto;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtTokenProvider;


@RestController
@ComponentScan(basePackageClasses = AuthenticationController.class)
@RequestMapping("/api")
public class AuthenticationController {
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/auth")
	public ResponseEntity<?> getToken(@RequestParam("user_name") String user, @RequestParam("password") String pass){
		System.out.println("loading getToken ......................");
		String token = jwtTokenProvider.generateToken(user);
		UserDto userDto = new UserDto();
		if(!token.isEmpty()) {
			User user1 = userRepository.findByUsername(user);
			BeanUtils.copyProperties(user1, userDto);
			userDto.setToken(token);
		}
		
//		Map<String, String> param = new HashMap<>();
//		if(StringUtils.hasText(token)) {
//			param.put("status", "OK");
//			param.put("token", token);
//		}
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@PostMapping("/document")
	public ResponseEntity<?> sendDocument(@RequestBody String data){
		System.out.println("loading apidocument ......................");
		Map<String, String> param = new HashMap<>();
		if(StringUtils.hasText(data)) {
			param.put("status", "OK");
		}else {
			param.put("status", "400");
		}
		return new ResponseEntity<>(param,HttpStatus.OK);
	}
	
	@PostMapping("/document/status")
	public ResponseEntity<?> updateDocument(@RequestBody String data){
		System.out.println("loading apidocument update ......................");
//		String jsonData = data;
//		JSONArray ja = new JSONArray(jsonData);
//		
//		for(int i=0; i < jsonArray.size(); i++) {
//			JSONPObject jo = jsonArray.getJ
//		}
		
		Map<String, String> param = new HashMap<>();
		if(StringUtils.hasText(data)) {
			param.put("status", "OK");
		}else {
			param.put("status", "400");
		}
		return new ResponseEntity<>(param,HttpStatus.OK);
	}
}

