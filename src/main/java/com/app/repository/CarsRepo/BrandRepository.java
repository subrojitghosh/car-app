package com.app.repository.CarsRepo;

import com.app.entity.cars.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {

}