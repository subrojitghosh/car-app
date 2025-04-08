package com.app.service.EvalutionServices;

import com.app.entity.evalution.Agents;
import com.app.entity.evalution.Area;
import com.app.exception.ResourceNotFound;
import com.app.payload.EvalutionDTO.AreaDto;
import com.app.repository.EvalutionRepo.AgentsRepository;
import com.app.repository.EvalutionRepo.AreaRepository;
import org.apache.poi.hssf.record.RecordInputStream;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AreaService {

    private final AreaRepository areaRepository;
    private final ModelMapper modelMapper;


    public AreaService(AreaRepository areaRepository, ModelMapper modelMapper) {
        this.areaRepository = areaRepository;
        this.modelMapper = modelMapper;

    }

    public AreaDto addAreaDetails(AreaDto areaDto) {
        Area area = convertDtoToEntity(areaDto);

        Area savedarea = areaRepository.save(area);
        return convertEntityToDto(savedarea);

    }

    public List<AreaDto> getAllArea(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo,pageSize, sort);

        Page<Area> areaList = areaRepository.findAll(page);
        List<Area> areaListContent = areaList.getContent();
        return areaListContent.stream().map(area -> modelMapper.map(area, AreaDto.class)).collect(Collectors.toList());

    }

    public void DeleteById(Long id) {
        if(!areaRepository.existsById(id)){
           throw new ResourceNotFound("Area not found with id: "+id);
        }
        areaRepository.deleteById(id);
    }

    public void updateAreaDetails(Long id, AreaDto areaDto) {

        Optional<Area> oparea = areaRepository.findById(id);
        Area area = oparea.get();
        area.setPinCode(areaDto.getPinCode());
        areaRepository.save(area);
    }

    public AreaDto getAreaById(Long id) {
        Area area = areaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Record not found with id " + id));

        AreaDto areaDto = new AreaDto();
        areaDto.setPinCode(area.getPinCode());
        areaDto.setId(area.getId());
        return areaDto;
    }


    Area convertDtoToEntity(AreaDto areaDto){

        return modelMapper.map(areaDto,Area.class);
    }

    AreaDto convertEntityToDto(Area area){

        return modelMapper.map(area, AreaDto.class);
    }



}
