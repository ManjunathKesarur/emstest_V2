package com.oracle.emstest.dto;

import lombok.Data;

@Data
public class OtpVerifyRequest {

	private String email;
	
	private String otp;
	
}
