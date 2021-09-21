package com.abdulraqeeb.otpwithawssns.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OTPService {

    private final int OTP_SIZE = 4;

    public String generateOTP() {
        Random random = new Random();
        char[] otp = new char[OTP_SIZE];
        for (int i=0; i<OTP_SIZE; i++){
            otp[i]= (char)(random.nextInt(10)+48);
        }

        return String.valueOf(otp) ;
    }
}
