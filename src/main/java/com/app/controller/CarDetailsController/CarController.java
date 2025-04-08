package com.app.controller.CarDetailsController;

import com.app.payload.CarsDTO.CarDto;
import com.app.payload.EvalutionDTO.AgentsDto;
import com.app.service.CarsServices.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add-car-details")
    public ResponseEntity<CarDto> createCar(@RequestBody CarDto car) throws Exception {
        CarDto car1 = carService.addCarDetails(car);
        return new ResponseEntity<>(car1, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-cars")
    public ResponseEntity<List<CarDto>> getAllCars(
            @RequestParam(defaultValue = "0",required = false)int pageNo,
            @RequestParam(defaultValue = "10",required = false)int pageSize,
            @RequestParam(defaultValue = "id",required = false)String sortBy,
            @RequestParam(defaultValue = "asc",required = false)String sortDir
    ) {
        List<CarDto> dtos = carService.getAllcars(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<String>  deleteCar(@PathVariable Long id) {
        carService.deleteByCar(id);
        return new ResponseEntity<>("Deleted",HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @RequestBody CarDto carDto){
        carService.updateCar(id,carDto);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @GetMapping("/get-car /{id}")
    public ResponseEntity<CarDto> getCartById(@PathVariable Long id){
        CarDto carDto = carService.findById(id);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }
}

