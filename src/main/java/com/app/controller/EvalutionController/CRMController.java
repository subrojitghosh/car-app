package com.app.controller.EvalutionController;

import com.app.entity.evalution.Agents;
import com.app.entity.evalution.Area;
import com.app.entity.evalution.CustomarVisit;
import com.app.repository.EvalutionRepo.AgentsRepository;
import com.app.repository.EvalutionRepo.AreaRepository;
import com.app.repository.EvalutionRepo.CustomarVisitRepository;
import com.app.service.CarsServices.SmsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/crm")
public class CRMController {
    private final AreaRepository areaRepository;
    private final AgentsRepository agentsRepository;
    private final CustomarVisitRepository customarVisitRepository;
    private final SmsService smsService;

    public CRMController(AreaRepository areaRepository, AgentsRepository agentsRepository, CustomarVisitRepository customarVisitRepository, SmsService smsService) {
        this.areaRepository = areaRepository;
        this.agentsRepository = agentsRepository;
        this.customarVisitRepository = customarVisitRepository;
        this.smsService = smsService;
    }
    @GetMapping("/area-agents")
    public ResponseEntity<List<Area>> searchAgent(
            @RequestParam int pinCode
    ){
        List<Area> areas = areaRepository.findByPinCode(pinCode);
        return new ResponseEntity<>(areas, HttpStatus.OK);
    }
    @PutMapping("/allocate")
    public ResponseEntity<String> allocateAgent(
            @RequestParam long customarId,
            @RequestParam long agentId
    ){
        Agents agents=null;
        Optional<Agents> opAgent= agentsRepository.findById(agentId);
        if(opAgent.isPresent()){
            agents=opAgent.get();
        }
        Optional<CustomarVisit>opcustomer = customarVisitRepository.findById(customarId);
        CustomarVisit customarVisit = opcustomer.get();
        customarVisit.setAgents(agents);
        customarVisitRepository.save(customarVisit);
         smsService.sendSms("+919851729772", " Agent is now Allocated - 9348661610");
        return new ResponseEntity<>("Agent is now allocated",HttpStatus.OK);
    }
}
