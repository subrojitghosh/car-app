package com.app.repository.CarsRepo;

import com.app.entity.cars.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarImageRepository extends JpaRepository<CarImage, Long> {
}