package com.app.controller.LocationsController;

import com.app.payload.Location.LocationDetailsDto;
import com.app.service.LocationsService.LocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/get-location")
    public ResponseEntity<LocationDetailsDto> getLocationDetails(){
       return ResponseEntity.ok( locationService.getLocation());

    }
}
