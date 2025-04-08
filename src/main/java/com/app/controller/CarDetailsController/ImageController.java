package com.app.controller.CarDetailsController;

import com.app.entity.cars.Car;
import com.app.entity.cars.CarImage;
import com.app.repository.CarsRepo.CarImageRepository;
import com.app.repository.CarsRepo.CarRepository;
import com.app.service.CarsServices.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private BucketService bucketService;
    private CarRepository carRepository;
    private CarImageRepository carImageRepository;

    public ImageController(BucketService bucketService, CarRepository carRepository, CarImageRepository carImageRepository) {
        this.bucketService = bucketService;
        this.carRepository = carRepository;

        this.carImageRepository = carImageRepository;
    }
//http://localhost:8080/api/v1/images/upload/file/{buketName}/car/{carId}
    @PostMapping("/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<CarImage> uploadCarPhotos(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable Long carId
            ){
        String url = bucketService.uploadFile(file, bucketName);

        Optional<Car> carid = carRepository.findById(carId);
            Car car = carid.get();
            CarImage carImage =new CarImage();
            carImage.setUrl(url);
            carImage.setCar(car);
            CarImage savedImage = carImageRepository.save(carImage);
         return new ResponseEntity<>(savedImage, HttpStatus.OK);

    }
}
