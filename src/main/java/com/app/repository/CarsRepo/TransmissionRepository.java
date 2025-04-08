package com.app.repository.CarsRepo;

import com.app.entity.cars.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransmissionRepository extends JpaRepository<Transmission, Long> {
}