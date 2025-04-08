package com.app.controller.CarDetailsController;

import com.app.entity.cars.Car;
import com.app.repository.CarsRepo.CarRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/search-car")
public class SearchCarController {

private final CarRepository carRepository;

    public SearchCarController( CarRepository carRepository) {

        this.carRepository = carRepository;
    }

    //http://localhost:8080/api/v1/cars/search-cars?brand=Honda
    @GetMapping("/cars")
    public List<Car> searchCars(@RequestParam String param){

       return carRepository.searchCar(param);

    }
}
