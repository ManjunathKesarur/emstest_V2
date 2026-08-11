package com.oracle.emstest.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.oracle.emstest.dto.RegisterRequest;
import com.oracle.emstest.entity.User;
import com.oracle.emstest.repository.UserRepository;
import com.oracle.emstest.util.OtpGenerator;

@Service
public class UserService {

	private UserRepository userRepository;
	private OtpService otpService;
	private PasswordEncoder passwordEncoder;


	public UserService(UserRepository userRepository, OtpService otpService, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.otpService = otpService;
		this.passwordEncoder = passwordEncoder;
	}

	
	public String register(RegisterRequest registerRequest) {
Optional<User>	op=	userRepository.findByEmail(registerRequest.getEmail());
if(op.isPresent()) {
	return "mail is Already exist";
}else {
	User user=new User();
	user.setName(registerRequest.getName());
	user.setEmail(registerRequest.getEmail());
	user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
	user.setRole("User");
	user.setVerified(false);
	
	String otp=OtpGenerator.generateotp();
	user.setOtp(otp);
	user.setOtpexpirytime(LocalDateTime.now().plusMinutes(1));
	userRepository.save(user);
	
	otpService.generateOtp(registerRequest.getEmail(), otp);
	
	return "please enter otp the otp expiers in 1 min";
}

	}
	
}
