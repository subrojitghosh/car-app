package com.app.controller.EvalutionController;

import com.app.entity.evalution.CarDetailedEvalution;
import com.app.service.EvalutionServices.CarDetailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/actual_car_detail")
public class ActualCarDetailed {

    private CarDetailService carDetailService;

    public ActualCarDetailed(CarDetailService carDetailService) {
        this.carDetailService = carDetailService;
    }
    @PostMapping
    public String createCarDetail(@RequestBody CarDetailedEvalution carDetailedEvalution){
        carDetailService.addCarDetails(carDetailedEvalution);
        return "create";
    }
}
