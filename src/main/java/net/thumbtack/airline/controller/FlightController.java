package net.thumbtack.airline.controller;

import net.thumbtack.airline.dto.FlightDTO;
import net.thumbtack.airline.dto.request.FlightAddRequestDTO;
import net.thumbtack.airline.dto.request.FlightGetParamsRequestDTO;
import net.thumbtack.airline.dto.request.FlightUpdateRequestDTO;
import net.thumbtack.airline.dto.response.ErrorDTO;
import net.thumbtack.airline.dto.response.FlightAddResponseDTO;
import net.thumbtack.airline.dto.response.FlightUpdateResponseDTO;
import net.thumbtack.airline.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private FlightService flightService;

    @Autowired
    public void setFlightService(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody FlightAddRequestDTO request) {
        ResponseEntity resp;
        FlightAddResponseDTO flightResponse =  flightService.add(request);
        if(flightResponse != null) {
            resp = ResponseEntity.ok(flightResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_ADDING_FLIGHT", "Don't know", "Error")
            );
        }
        return resp;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody FlightUpdateRequestDTO request) {
        ResponseEntity resp;
        FlightUpdateResponseDTO flightResponse =  flightService.update(request);
        if(flightResponse != null) {
            resp = ResponseEntity.ok(flightResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_UPDATING_FLIGHT", "Don't know", "Error")
            );
        }
        return resp;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        ResponseEntity resp;
        if(flightService.delete(id)) {
            resp = ResponseEntity.ok().build();
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_DELETING_FLIGHT", "Don't know", "Error")
            );
        }
        return resp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable("id") int id) {
        ResponseEntity resp;
        FlightAddResponseDTO flightResponse =  flightService.get(id);
        if(flightResponse != null) {
            resp = ResponseEntity.ok(flightResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_GETTING_FLIGHT", "Don't know", "Error")
            );
        }
        return resp;
    }

    @PutMapping("/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable("id") int id) {
        ResponseEntity resp;
        FlightAddResponseDTO flightResponse =  flightService.approve(id);
        if(flightResponse != null) {
            resp = ResponseEntity.ok(flightResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_APPROVING_FLIGHT", "Don't know", "Error")
            );
        }
        return resp;
    }

    //Admin and user privileges
    @GetMapping
    public ResponseEntity<?> flights(
            @RequestParam(required = false, value = "fromTown", defaultValue = "") String fromTown,
            @RequestParam(required = false, value = "toTown", defaultValue = "") String toTown,
            @RequestParam(required = false, value = "flightName ", defaultValue = "") String flightName,
            @RequestParam(required = false, value = "planeName", defaultValue = "") String planeName,
            @RequestParam(required = false, value = "fromDate", defaultValue = "") String fromDate,
            @RequestParam(required = false, value = "toDate", defaultValue = "") String toDate) {

        //TODO check user role. If admin, return flight with approve and plane
        ResponseEntity resp;
        List<FlightAddResponseDTO> flightResponse =  flightService.get(new FlightGetParamsRequestDTO(
                fromTown, toTown, flightName, planeName, fromDate, toDate
        ));
        if(!flightResponse.isEmpty()) {
            resp = ResponseEntity.ok(flightResponse);
        }
        else {
            resp = ResponseEntity.badRequest().body(
                    new ErrorDTO("ERROR_WITH_GETTING_LIST_FLIGHT", "Don't know", "Error")
            );
        }
        return resp;
    }

}
