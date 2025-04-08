package com.app.service.LocationsService;

import com.app.payload.Location.IpDto;
import com.app.payload.Location.LocationDetailsDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationService {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${ipify.apiKey}")
    private String apiKey;
    public LocationDetailsDto getLocation(){
       IpDto ip =  restTemplate.getForObject("https://api.ipify.org?format=json", IpDto.class);
       LocationDetailsDto locationDetailsDto = null;
       if(ip !=null && ip.getIp() !=null){
           locationDetailsDto =  restTemplate.getForObject("https://geo.ipify.org/api/v2/country,city?apiKey="+apiKey+"&ipAddress="+ip.getIp(), LocationDetailsDto.class);
       }
        return locationDetailsDto;
    }
}
