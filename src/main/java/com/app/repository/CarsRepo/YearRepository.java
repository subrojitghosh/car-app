package com.app.repository.CarsRepo;

import com.app.entity.cars.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepository extends JpaRepository<Year, Long> {
}