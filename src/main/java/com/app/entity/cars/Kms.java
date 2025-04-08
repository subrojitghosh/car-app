package com.app.entity.cars;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "kms")
public class Kms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "kms_driven", nullable = false)
   private String kms_driven;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getKms_driven() {
        return kms_driven;
    }

    public void setKms_driven(String kms_driven) {
        this.kms_driven = kms_driven;
    }
}