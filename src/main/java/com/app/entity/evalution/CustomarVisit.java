package com.app.entity.evalution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "customar_visit")
public class CustomarVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "mobile", nullable = false, length = 10)
    private String mobile;

    @Column(name = "house_no", nullable = false)
    private String houseNo;

    @Column(name = "address_line_1", nullable = false)
    private String addressLine1;

    @Column(name = "address_line_2", nullable = false)
    private String addressLine2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "pin_code" ,nullable = false)
    private int pinCode;

    @Column(name = "date_ofvisit", nullable = false)
    private LocalDate dateOfvisit;

    @Column(name = "time_of_visit", nullable = false)
    private LocalTime timeOfVisit;

    @ManyToOne
    @JoinColumn(name = "agents_id")
    private Agents agents;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDate getDateOfvisit() {
        return dateOfvisit;
    }

    public void setDateOfvisit(LocalDate dateOfvisit) {
        this.dateOfvisit = dateOfvisit;
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