package com.app.controller.EvalutionController;

import com.app.entity.evalution.Area;
import com.app.payload.EvalutionDTO.AreaDto;
import com.app.service.EvalutionServices.AreaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/areas")
public class AreaController {

    private AreaService areaService;

    public AreaController(AreaService areaService) {
        this.areaService = areaService;
    }
    @PostMapping("/add-areaDetails")
    public ResponseEntity<AreaDto> createArea(@RequestBody AreaDto areaDto) {
        AreaDto areaDto1 = areaService.addAreaDetails(areaDto);
        return new ResponseEntity<>(areaDto1, HttpStatus.CREATED);

    }
    @GetMapping("/get-all")
    public ResponseEntity<List<AreaDto>> getAllArea(
            @RequestParam(defaultValue = "0",required = false)int pageNo,
            @RequestParam(defaultValue = "10",required = false)int pageSize,
            @RequestParam(defaultValue = "id",required = false)String sortBy,
            @RequestParam(defaultValue = "asc",required = false)String sortDir
    ){
        List<AreaDto> allArea = areaService.getAllArea(pageNo,pageSize,sortBy,sortDir);

        return new ResponseEntity<>(allArea,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteArea(@PathVariable Long id){
        areaService.DeleteById(id);
        return new ResponseEntity<>("Area deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update-areaDetails/{id}")
    public ResponseEntity<AreaDto> updateArea(@PathVariable Long id, @RequestBody AreaDto areaDto){
        areaService.updateAreaDetails(id, areaDto);
        return new ResponseEntity<>(areaDto, HttpStatus.OK);
    }
    @GetMapping("get-by-id/{id}")
    public ResponseEntity<AreaDto> getAreaById(@PathVariable Long id){
        AreaDto areaById = areaService.getAreaById(id);
        return new ResponseEntity<>(areaById, HttpStatus.OK);
    }
}
