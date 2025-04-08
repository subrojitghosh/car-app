package com.app.repository.EvalutionRepo;

import com.app.entity.evalution.Area;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findByPinCode(int pinCode);
}