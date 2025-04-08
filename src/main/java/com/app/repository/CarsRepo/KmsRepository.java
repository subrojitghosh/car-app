package com.app.repository.CarsRepo;

import com.app.entity.cars.Kms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KmsRepository extends JpaRepository<Kms, Long> {
}