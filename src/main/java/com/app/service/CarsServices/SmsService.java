package com.app.service.CarsServices;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {
    @Value("${twilio.whatsapp_number}")
    private String whatsappNumber;
    private final String phoneNumber;

    @Autowired
    public SmsService(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void sendSms(String to, String body) {
       Message.creator(
              new PhoneNumber(to),
              new PhoneNumber(phoneNumber),
               body
       ).create();

        Message.creator(
                new PhoneNumber("whatsapp:"+to),
                new PhoneNumber("whatsapp:"+whatsappNumber),
                body
        ).create();


    }
}
