package com.app.service.EvalutionServices;

import com.app.entity.evalution.CustomarVisit;
import com.app.exception.ResourceNotFound;
import com.app.payload.EvalutionDTO.CustomerVisitDto;
import com.app.repository.EvalutionRepo.AgentsRepository;
import com.app.repository.EvalutionRepo.CustomarVisitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {


private final CustomarVisitRepository customarVisitRepository;
private final ModelMapper modelMapper;
private final AgentsRepository agentsRepository;

    public CustomerService(CustomarVisitRepository customarVisitRepository, ModelMapper modelMapper, AgentsRepository agentsRepository) {
        this.customarVisitRepository = customarVisitRepository;
        this.modelMapper = modelMapper;
        this.agentsRepository = agentsRepository;
    }

    public CustomerVisitDto addCustomarDetails(CustomerVisitDto customarVisitDto)  {
        CustomarVisit customarVisit = convertDtoToEntity(customarVisitDto);
//        if(customarVisitDto != null ){
//            Optional<Agents> agt = agentsRepository.findById(customarVisitDto.getAgents().getId());
//            customarVisit.setAgents(agt.orElse(null));
//
//        }
        CustomarVisit savedCustomar = customarVisitRepository.save(customarVisit);
        return convertEntityToDto(savedCustomar);
    }

    public List<CustomerVisitDto> findAllCustomer(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<CustomarVisit> customerList=  customarVisitRepository.findAll(page);
        List<CustomarVisit> customerListContent = customerList.getContent();
        return customerListContent .stream().map(CustomarVisit -> modelMapper.map(CustomarVisit, CustomerVisitDto.class))
                .collect(Collectors.toList());

    }

    public CustomerVisitDto findById(Long id) {
        CustomarVisit customarVisit = customarVisitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Record not found with id " + id));
       CustomerVisitDto customerVisitDto=new CustomerVisitDto();
       customerVisitDto.setName(customarVisit.getName());
       customerVisitDto.setMobile(customarVisit.getMobile());
       customerVisitDto.setCity(customarVisit.getCity());
       customerVisitDto.setHouseNo(customarVisit.getHouseNo());
       customerVisitDto.setDateOfVisit(customarVisit.getDateOfvisit());
       customerVisitDto.setTimeOfVisit(customarVisit.getTimeOfVisit());
       customerVisitDto.setAddressLine1(customarVisit.getAddressLine1());
       customerVisitDto.setAddressLine2(customarVisit.getAddressLine2());
       customerVisitDto.setPinCode(customarVisit.getPinCode());
       customerVisitDto.setAgents(customarVisit.getAgents());
       return customerVisitDto;

    }

    public void deleteCustomer(Long id) {

        if(!customarVisitRepository.existsById(id)){
            throw new ResourceNotFound("Area not found with id: "+id);
        }
        customarVisitRepository.deleteById(id);
    }


    CustomarVisit convertDtoToEntity(CustomerVisitDto customarVisitDto){

        return modelMapper.map(customarVisitDto,CustomarVisit.class);
    }

    CustomerVisitDto convertEntityToDto(CustomarVisit customarVisit){

        return modelMapper.map(customarVisit, CustomerVisitDto.class);
    }



}
