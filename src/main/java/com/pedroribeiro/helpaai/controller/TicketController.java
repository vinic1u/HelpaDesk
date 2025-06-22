package com.pedroribeiro.helpaai.controller;

import com.pedroribeiro.helpaai.dtos.observation.ObservationRequestDTO;
import com.pedroribeiro.helpaai.dtos.ticket.TicketRequestDTO;
import com.pedroribeiro.helpaai.dtos.ticket.TicketResponseDTO;
import com.pedroribeiro.helpaai.entities.User;
import com.pedroribeiro.helpaai.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {


    @Autowired
    private TicketService ticketService;


    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> findAllTickets(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(ticketService.findAllTickets(user));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TicketResponseDTO> findTicketById(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Integer id
    ){
        return ResponseEntity.ok(ticketService.findTicketById(user,id));
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> saveTicket(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody TicketRequestDTO dto
            ){
        TicketResponseDTO response = ticketService.saveTicket(user,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TicketResponseDTO> updateTicket(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Integer id,
            @Valid @RequestBody TicketRequestDTO dto
    ){
        TicketResponseDTO response = ticketService.updateTicket(user,id,dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value= "/{id}/atribuir")
    public ResponseEntity<String> assignTicket(
            @AuthenticationPrincipal User user,
            @PathVariable Integer id
    ){
        String response = ticketService.assignTicket(user,id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/{id}/observation")
    public ResponseEntity<TicketResponseDTO> createObservation(
            @AuthenticationPrincipal User user,
            @PathVariable("id") Integer id,
            @Valid @RequestBody ObservationRequestDTO dto
            ){
        TicketResponseDTO response = ticketService.createObservation(user,id,dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
