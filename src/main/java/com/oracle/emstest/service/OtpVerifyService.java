package com.oracle.emstest.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.oracle.emstest.dto.OtpVerifyRequest;
import com.oracle.emstest.entity.User;
import com.oracle.emstest.repository.UserRepository;

@Service
public class OtpVerifyService {
	
	private UserRepository userRepository;
	

public OtpVerifyService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



public String verifyOtp(OtpVerifyRequest otpVerifyRequest) {	
Optional<User> or=	userRepository.findByEmail(otpVerifyRequest.getEmail());

if(or.isPresent()) {

	User user=or.get();
	
	if(!user.getOtp().equals(otpVerifyRequest.getOtp())) {
		return "enter the valid otp";
	}
	if(LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
		return "Otp Expired";
	}else {
		user.setOtp(null);
		user.setOtpexpirytime(null);
		user.setVerified(true);
		userRepository.save(user);
		return "The User Is Verified";
	}
}
else {
	return "The Enter EmailId User Not Found";
}
	}	
		}
