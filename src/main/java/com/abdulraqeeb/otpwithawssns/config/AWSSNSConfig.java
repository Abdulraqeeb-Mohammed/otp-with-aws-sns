package com.abdulraqeeb.otpwithawssns.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSSNSConfig {

    @Value("${application.aws.credentials.access-key}")
    public String ACCESS_KEY;

    @Value("${application.aws.credentials.secret-key}")
    public String SECRET_KEY;

    @Primary
    @Bean
    public AmazonSNSClient getSnsClient() {

        return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_2)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY,
                        SECRET_KEY)))
                .build();
    }
}
