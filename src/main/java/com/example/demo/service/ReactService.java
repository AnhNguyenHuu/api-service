package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.model.React;

@Service
public interface ReactService {
	Page<React> gets(React react);
	
	
	String caveJav();
}
