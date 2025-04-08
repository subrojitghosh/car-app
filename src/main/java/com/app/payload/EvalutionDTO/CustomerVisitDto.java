package com.app.payload.EvalutionDTO;

import com.app.entity.evalution.Agents;

import java.time.LocalDate;
import java.time.LocalTime;

public class CustomerVisitDto {
   private String name;
   private String mobile;
   private String houseNo;
   private String addressLine1;
   private String addressLine2;
   private String city;
   private int pinCode;
   private LocalDate dateOfVisit;
   private LocalTime timeOfVisit;
   private Agents agents;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public LocalDate getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(LocalDate dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public LocalTime getTimeOfVisit() {
        return timeOfVisit;
    }

    public void setTimeOfVisit(LocalTime timeOfVisit) {
        this.timeOfVisit = timeOfVisit;
    }

    public Agents getAgents() {
        return agents;
    }

    public void setAgents(Agents agents) {
        this.agents = agents;
    }
}
