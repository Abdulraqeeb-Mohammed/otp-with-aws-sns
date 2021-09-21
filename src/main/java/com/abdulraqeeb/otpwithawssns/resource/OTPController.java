package com.abdulraqeeb.otpwithawssns.resource;

import com.abdulraqeeb.otpwithawssns.service.OTPService;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sns")
public class OTPController {

    private final AmazonSNSClient snsClient;
    private final OTPService otpService;

    @Value("${application.aws.sns.arn.otp-topic-arn}")
    public String OTP_TOPIC_ARN;

    public OTPController(AmazonSNSClient snsClient, OTPService otpService) {
        this.snsClient = snsClient;
        this.otpService = otpService;
    }

    @GetMapping("/subscribe/otp/{phoneNumber}")
    public String sendOTP(@PathVariable String phoneNumber){
        SubscribeRequest request = new SubscribeRequest(OTP_TOPIC_ARN, "SMS", phoneNumber);
        snsClient.subscribe(request);

        return "Phone number: "+ phoneNumber + " has successfully subscribed for OTP";
    }

    @GetMapping("/subscribe/email/{email}")
    public String sendEmail(@PathVariable String email){
        SubscribeRequest request = new SubscribeRequest(OTP_TOPIC_ARN, "email", email);
        snsClient.subscribe(request);

        return "Subscription request sent to email address: "+ email +". Confirm to complete subscription." ;
    }

    @GetMapping("/publish-notification")
    public String publishMessageToTopic(){
        PublishRequest publishRequest = new PublishRequest(OTP_TOPIC_ARN,
                "Use this one time password to authenticate: "+ otpService.generateOTP() ,
                "OTP for TDF login");
        snsClient.publish(publishRequest);
        return "Notification published to related topics";
    }

}
