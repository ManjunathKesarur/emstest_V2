package com.oracle.emstest.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oracle.emstest.dto.ResendOtp;
import com.oracle.emstest.entity.User;
import com.oracle.emstest.repository.UserRepository;
import com.oracle.emstest.util.OtpGenerator;

@Service
public class ResendOtpService {

private UserRepository userRepository;
private OtpService otpService;


	public ResendOtpService(UserRepository userRepository, OtpService otpService) {
	this.userRepository = userRepository;
	this.otpService = otpService;
}


	public String resend(ResendOtp resendOtp) {
	Optional<User> or=	userRepository.findByEmail(resendOtp.getEmail());
	if(or.isPresent()) {
	User usero	=	or.get();
	String otp=OtpGenerator.generateotp();
	usero.setOtpexpirytime(LocalDateTime.now().plusMinutes(1));
	usero.setOtp(otp);
	userRepository.save(usero);
	
	otpService.generateOtp(resendOtp.getEmail(), otp);
	return "Otp Resent and expires in 1 min";
}else {
	return "its a remodified email";
}
}}
