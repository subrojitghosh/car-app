package com.app.service.EvalutionServices;

import com.app.entity.evalution.CarDetailedEvalution;
import com.app.repository.EvalutionRepo.CarDetailedEvalutionRepository;
import org.springframework.stereotype.Service;

@Service
public class CarDetailService {

    private final CarDetailedEvalutionRepository carDetailedEvalutionRepository;

    public CarDetailService(CarDetailedEvalutionRepository carDetailedEvalutionRepository) {
        this.carDetailedEvalutionRepository = carDetailedEvalutionRepository;
    }

    public void addCarDetails(CarDetailedEvalution carDetailedEvalution) {

        carDetailedEvalutionRepository.save(carDetailedEvalution);
    }
}
