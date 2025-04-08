package com.app.payload.Location;

public class LocationDetailsDto
 {
     private LocationDto location;
     private String ip;

     public LocationDto getLocation() {
         return location;
     }

     public void setLocation(LocationDto location) {
         this.location = location;
     }

     public String getIp() {
         return ip;
     }

     public void setIp(String ip) {
         this.ip = ip;
     }
 }
