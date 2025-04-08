package com.app.repository.CarsRepo;

import com.app.entity.cars.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}