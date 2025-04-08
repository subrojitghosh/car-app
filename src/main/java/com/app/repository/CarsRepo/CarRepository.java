package com.app.repository.CarsRepo;

import com.app.entity.cars.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c" +
            " JOIN c.brand b" +
            " WHERE b.brandName =:details"
    )
    List<Car> searchCar(
            @Param("details") String details
    );
}