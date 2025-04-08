package com.app.config;


import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;
    @PostConstruct
    public void init(){
        Twilio.init(accountSid, authToken);

    }
    @Bean("phoneNumber")
    public String getTwilioPhoneNumber(){
        return twilioPhoneNumber;
    }
}

