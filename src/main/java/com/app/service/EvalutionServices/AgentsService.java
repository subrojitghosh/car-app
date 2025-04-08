package com.app.service.EvalutionServices;

import com.app.entity.evalution.Agents;
import com.app.entity.evalution.Area;
import com.app.exception.ResourceNotFound;
import com.app.payload.EvalutionDTO.AgentsDto;
import com.app.payload.EvalutionDTO.AreaDto;
import com.app.repository.EvalutionRepo.AgentsRepository;
import com.app.repository.EvalutionRepo.AreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgentsService {

    private final AgentsRepository agentsRepository;
    private final ModelMapper modelMapper;
    private final AreaRepository areaRepository;


    public AgentsService(AgentsRepository agentsRepository, ModelMapper modelMapper, AreaRepository areaRepository) {
        this.agentsRepository = agentsRepository;
        this.modelMapper = modelMapper;
        this.areaRepository = areaRepository;
    }

    public AgentsDto addAgents(AgentsDto agentsDto) throws Exception {

        Agents agents = convertDtoToEntity(agentsDto);

        if(agentsDto!= null && agentsDto.getArea().getId() !=null){
            Area ar = areaRepository.findById(agentsDto.getArea().getId()).orElseThrow(() -> new Exception("id is not found"));
            agents.setArea(ar);
        }
        Agents savedAgents = agentsRepository.save(agents);
        return convertEntityToDto(savedAgents);
    }


    public List<AgentsDto> findAllAgents(int pageNo,int pageSize,String sortBy,String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable page = PageRequest.of(pageNo,pageSize, sort);
        Page<Agents> agentsList = agentsRepository.findAll(page);
        List<Agents> agentsListContent = agentsList.getContent();
        return agentsListContent.stream().map(agents -> modelMapper.map(agents, AgentsDto.class)).collect(Collectors.toList());

    }

    public AgentsDto findById(Long id) {

        Agents agents = agentsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFound("Record not found with id " + id));

        AgentsDto agentsDto = new AgentsDto();
        agentsDto.setName(agents.getName());
        agentsDto.setEmail(agents.getEmail());
        agentsDto.setMobile(agents.getMobile());
        agentsDto.setArea(agents.getArea());
        return agentsDto;
    }

    public AgentsDto updateAgent(Long id, AgentsDto agentsDto) throws Exception {
        Agents agents = agentsRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFound("Record not found with id " + id));

        agents.setName(agentsDto.getName());
        agents.setEmail(agentsDto.getEmail());
        agents.setMobile(agentsDto.getMobile());

        if(agentsDto.getArea().getId()!=null){
            Area ar = areaRepository.findById(agentsDto.getArea().getId())
                    .orElseThrow(() -> new Exception("id is not found"));
            agents.setArea(ar);
        }

        Agents updatedAgents = agentsRepository.save(agents);
        return convertEntityToDto(updatedAgents);
    }

    public void deleteAgent(Long id) {
        if(!agentsRepository.existsById(id)){
            throw new ResourceNotFound("Area not found with id: "+id);
        }
        agentsRepository.deleteById(id);
    }

    Agents convertDtoToEntity(AgentsDto agentsDto){

        return modelMapper.map(agentsDto,Agents.class);
    }

    AgentsDto convertEntityToDto(Agents agents){

        return modelMapper.map(agents, AgentsDto.class);
    }



}
