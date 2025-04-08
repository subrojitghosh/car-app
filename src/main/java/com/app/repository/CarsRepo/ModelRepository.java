package com.app.repository.CarsRepo;

import com.app.entity.cars.Model;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {
}