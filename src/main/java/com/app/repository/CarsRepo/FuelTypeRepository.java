package com.app.repository.CarsRepo;

import com.app.entity.cars.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {
}