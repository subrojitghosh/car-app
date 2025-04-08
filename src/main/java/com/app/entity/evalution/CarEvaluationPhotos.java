package com.app.entity.evalution;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "car_evaluation_photos")
public class CarEvaluationPhotos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "photo_url")
    private String photoUrl;

    @ManyToOne
    @JoinColumn(name = "car_detailed_evalution_id")
    private CarDetailedEvalution carDetailedEvalution;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public CarDetailedEvalution getCarDetailedEvalution() {
        return carDetailedEvalution;
    }

    public void setCarDetailedEvalution(CarDetailedEvalution carDetailedEvalution) {
        this.carDetailedEvalution = carDetailedEvalution;
    }
}