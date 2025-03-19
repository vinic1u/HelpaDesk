package com.pedroribeiro.helpaai.controller;

import com.pedroribeiro.helpaai.dtos.observation.ObservationRequestDTO;
import com.pedroribeiro.helpaai.dtos.ticket.TicketRequestDTO;
import com.pedroribeiro.helpaai.dtos.ticket.TicketResponseDTO;
import com.pedroribeiro.helpaai.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {


    @Autowired
    private TicketService ticketService;


    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> findAllTickets(){
        return ResponseEntity.ok(ticketService.findAllTickets());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketResponseDTO> findTicketById(
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(ticketService.findTicketById(id));
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> saveTicket(
            @RequestBody TicketRequestDTO dto
            ){
        TicketResponseDTO response = ticketService.saveTicket(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(
            @PathVariable("id") Integer id,
            @RequestBody TicketRequestDTO dto
    ){
        TicketResponseDTO response = ticketService.updateTicket(id,dto);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/{id}/observation")
    public ResponseEntity<TicketResponseDTO> createObservation(
            @PathVariable("id") Integer id,
            @RequestBody ObservationRequestDTO dto
            ){
        TicketResponseDTO response = ticketService.createObservation(id,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
