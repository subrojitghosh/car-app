package com.app.controller.EvalutionController;

import com.app.payload.EvalutionDTO.CustomerVisitDto;

import com.app.service.EvalutionServices.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerVisitController {


    private final CustomerService customerService;

    public CustomerVisitController(CustomerService customerService) {
        this.customerService = customerService;
    }
    @PostMapping("/add-customer")
    public ResponseEntity<CustomerVisitDto> createCustomerDetails(
            @RequestBody CustomerVisitDto customarVisitDto) throws Exception {
        CustomerVisitDto customerVisitDto1 = customerService.addCustomarDetails(customarVisitDto);
        return new ResponseEntity<>(customerVisitDto1, HttpStatus.CREATED);
    }
    @GetMapping("/get-allCustomer")
    public ResponseEntity<List<CustomerVisitDto>> getAllCustomer(
            @RequestParam(defaultValue = "0",required = false)int pageNo,
            @RequestParam(defaultValue = "10",required = false)int pageSize,
            @RequestParam(defaultValue = "id",required = false)String sortBy,
            @RequestParam(defaultValue = "asc",required = false)String sortDir
    ){
        List<CustomerVisitDto> allCustomer = customerService.findAllCustomer(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allCustomer,HttpStatus.OK);
    }

    @GetMapping("/get-customer/{id}")
    public ResponseEntity<CustomerVisitDto> getCustomerById(@PathVariable Long id){
        CustomerVisitDto customerVisitDto = customerService.findById(id);
        return new ResponseEntity<>(customerVisitDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
    }
}
