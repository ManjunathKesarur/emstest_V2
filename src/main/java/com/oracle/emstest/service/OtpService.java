package com.oracle.emstest.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpService {

	private JavaMailSender mailSender;
	
	public OtpService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void generateOtp(String email,String otp) {
		
		SimpleMailMessage message= new SimpleMailMessage();
		message.setTo(email);
		message.setSubject("Oracle");
		message.setText(otp+"otp expires in 1 min");
		
		mailSender.send(message);
	}
}
