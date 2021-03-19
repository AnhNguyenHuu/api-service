package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app")
public class Approperties {
	private int defaultPage;
	private int defaultSize;
	
	
	public int getDefaultPage() {
		return defaultPage;
	}
	public void setDefaultPage(int defaultPage) {
		this.defaultPage = defaultPage;
	}
	public int getDefaultSize() {
		return defaultSize;
	}
	public void setDefaultSize(int defaultSize) {
		this.defaultSize = defaultSize;
	}
	
	
}
