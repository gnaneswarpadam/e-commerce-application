package com.dev.ecommerceapp.service;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl {
	
	public static LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now(ZoneId.of("Asia/Kolkata"));
	}
}
