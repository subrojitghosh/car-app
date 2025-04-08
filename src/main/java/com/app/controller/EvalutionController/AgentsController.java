package com.app.controller.EvalutionController;

import com.app.entity.evalution.Agents;
import com.app.payload.EvalutionDTO.AgentsDto;
import com.app.payload.EvalutionDTO.AreaDto;
import com.app.service.EvalutionServices.AgentsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/agents")
public class AgentsController {
    private AgentsService agentsService;

    public AgentsController(AgentsService agentsService) {
        this.agentsService = agentsService;
    }
    @PostMapping("/add-agents")
    public ResponseEntity<AgentsDto> createAgents(@RequestBody AgentsDto agentsDto) throws Exception {
        AgentsDto agentsDto1 = agentsService.addAgents(agentsDto);
        return new ResponseEntity<>(agentsDto1, HttpStatus.CREATED);
    }
    @GetMapping("/get-allAgents")
    public ResponseEntity<List<AgentsDto>> getAllAgents(
            @RequestParam(defaultValue = "0",required = false)int pageNo,
            @RequestParam(defaultValue = "10",required = false)int pageSize,
            @RequestParam(defaultValue = "id",required = false)String sortBy,
            @RequestParam(defaultValue = "asc",required = false)String sortDir
    ){
        List<AgentsDto> allAgents = agentsService.findAllAgents(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allAgents,HttpStatus.OK);

    }

    @GetMapping("/get-agent/{id}")
    public ResponseEntity<AgentsDto> getAgentById(@PathVariable Long id){
        AgentsDto agentsDto = agentsService.findById(id);
        return new ResponseEntity<>(agentsDto, HttpStatus.OK);
    }

    @PutMapping("/update-agent/{id}")
    public ResponseEntity<AgentsDto> updateAgent(@PathVariable Long id, @RequestBody AgentsDto agentsDto) throws Exception {
        AgentsDto agentsDto1 = agentsService.updateAgent(id, agentsDto);
        return new ResponseEntity<>(agentsDto1, HttpStatus.OK);
    }

    @DeleteMapping("/delete-agent/{id}")
    public ResponseEntity<String> deleteAgent(@PathVariable Long id){
        agentsService.deleteAgent(id);
        return new ResponseEntity<>("id is deleted",HttpStatus.OK);
    }
}
