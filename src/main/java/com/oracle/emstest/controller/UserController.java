package com.oracle.emstest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.emstest.dto.OtpVerifyRequest;
import com.oracle.emstest.dto.RegisterRequest;
import com.oracle.emstest.dto.ResendOtp;
import com.oracle.emstest.service.OtpVerifyService;
import com.oracle.emstest.service.ResendOtpService;
import com.oracle.emstest.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	private OtpVerifyService otpVerifyService;
	private ResendOtpService resendOtpService;

	
	


	public UserController(UserService userService, OtpVerifyService otpVerifyService,
			ResendOtpService resendOtpService) {
		super();
		this.userService = userService;
		this.otpVerifyService = otpVerifyService;
		this.resendOtpService = resendOtpService;
	}

	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest registerRequest) {
		return userService.register(registerRequest);
	}
	
	@PostMapping("/verify")
	public String verify(@RequestBody OtpVerifyRequest otpVerifyRequest) {
		return otpVerifyService.verifyOtp(otpVerifyRequest);
	}
	
	@PostMapping("/resend")
	public String resend(@RequestBody ResendOtp resendOtp) {
		return resendOtpService.resend(resendOtp);
	}
	
}
