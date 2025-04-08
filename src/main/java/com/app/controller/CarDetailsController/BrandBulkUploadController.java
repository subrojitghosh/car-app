package com.app.controller.CarDetailsController;

import com.app.entity.cars.Brand;
import com.app.repository.CarsRepo.BrandRepository;
import com.app.service.CarsServices.BulkUploadBrandNameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/car_details/bulk_upload/brands")
public class BrandBulkUploadController {

    private final BulkUploadBrandNameService bulkUploadBrandNameService;
    private final BrandRepository brandRepository;


    public BrandBulkUploadController(BulkUploadBrandNameService bulkUploadBrandNameService, BrandRepository brandRepository) {
        this.bulkUploadBrandNameService = bulkUploadBrandNameService;
        this.brandRepository = brandRepository;
    }
    @PostMapping("/upload")
    public String uploadExcelFile(@RequestParam("filePath")String filePath){
        try {
            List<Brand> allBrand = bulkUploadBrandNameService.readExcel(filePath);
            brandRepository.saveAll(allBrand);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Brand bulk upload successful";
    }
}
