package com.app.controller.EvalutionController;


import com.app.entity.evalution.CarDetailedEvalution;
import com.app.entity.evalution.CarEvaluationPhotos;
import com.app.repository.EvalutionRepo.CarDetailedEvalutionRepository;
import com.app.repository.EvalutionRepo.CarEvaluationPhotosRepository;
import com.app.service.CarsServices.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/actual_car_photos")
public class ActualCarPhotos {

    private BucketService bucketService;
   private CarEvaluationPhotosRepository carEvaluationPhotosRepository;
   private CarDetailedEvalutionRepository carDetailedEvalutionRepository;

    public ActualCarPhotos(BucketService bucketService, CarEvaluationPhotosRepository carEvaluationPhotosRepository, CarDetailedEvalutionRepository carDetailedEvalutionRepository) {
        this.bucketService = bucketService;
        this.carEvaluationPhotosRepository = carEvaluationPhotosRepository;
        this.carDetailedEvalutionRepository = carDetailedEvalutionRepository;
    }
    @PostMapping("/upload/files/{bucketName}/carDetailedEvalution/{carDetailedEvalutionId}")
    public ResponseEntity<?> uploadCarPhotos(
            @RequestParam("files") List<MultipartFile> files,
            @PathVariable String bucketName,
            @PathVariable Long carDetailedEvalutionId
    ) throws Exception {
        ArrayList<CarEvaluationPhotos> carImages = new ArrayList<>();
        Optional<CarDetailedEvalution> cde= carDetailedEvalutionRepository.findById(carDetailedEvalutionId);
        if(cde.isEmpty()){
            throw new Exception("Resource not found");
        }
        CarDetailedEvalution carDetailedEvalution = cde.get();
        for(MultipartFile file : files){
            String url = bucketService.uploadFile(file, bucketName);
            //save to database
            CarEvaluationPhotos carEvaluationPhotos = new CarEvaluationPhotos();
             carEvaluationPhotos.setPhotoUrl(url);
             carEvaluationPhotos.setCarDetailedEvalution(carDetailedEvalution);
            carImages.add(carEvaluationPhotos);

        }

        List<CarEvaluationPhotos> allCars = carEvaluationPhotosRepository.saveAll(carImages);

        return new ResponseEntity<>(allCars, HttpStatus.CREATED );
    }
}
