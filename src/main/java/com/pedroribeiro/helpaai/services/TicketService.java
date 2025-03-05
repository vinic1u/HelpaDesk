package com.pedroribeiro.helpaai.services;

import com.pedroribeiro.helpaai.dtos.observation.ObservationRequestDTO;
import com.pedroribeiro.helpaai.dtos.ticket.TicketRequestDTO;
import com.pedroribeiro.helpaai.dtos.ticket.TicketResponseDTO;
import com.pedroribeiro.helpaai.entities.Category;
import com.pedroribeiro.helpaai.entities.Observation;
import com.pedroribeiro.helpaai.entities.Ticket;
import com.pedroribeiro.helpaai.enums.Priority;
import com.pedroribeiro.helpaai.enums.Status;
import com.pedroribeiro.helpaai.exceptions.ResourceNotFoundException;
import com.pedroribeiro.helpaai.repositories.CategoryRepository;
import com.pedroribeiro.helpaai.repositories.ObservationRepository;
import com.pedroribeiro.helpaai.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<TicketResponseDTO> findAllTickets(){
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(TicketResponseDTO::new).toList();
    }

    public TicketResponseDTO findTicketById(Integer id){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket com ID: " + id + " não encontrado!"));
        return new TicketResponseDTO(ticket);
    }

    public TicketResponseDTO saveTicket(TicketRequestDTO dto){
        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setPriority(Priority.BAIXA);
        ticket.setStatus(Status.PENDENTE_OPERADOR);

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Categoria com ID: " +  dto.getCategoryId() + " não encontrada!"));
        ticket.setCategory(category);

        for(ObservationRequestDTO observationDTO : dto.getObservations()){
            Observation observationEntity = new Observation();
            observationEntity.setObservation(observationDTO.getObservation());
            observationEntity.setTicket(ticket);
            ticket.getObservations().add(observationEntity);
        }

        ticketRepository.save(ticket);
        return new TicketResponseDTO(ticket);
    }

    public TicketResponseDTO updateTicket(Integer id,TicketRequestDTO dto){
        Ticket ticket = ticketRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Ticket de ID: " + id + " não encontrado!"));

        ticket.setTitle(dto.getTitle());
        ticket.setPriority(dto.getPriority());
        ticket.setStatus(dto.getStatus());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Categoria com ID: " +  dto.getCategoryId() + " não encontrada!"));
        ticket.setCategory(category);

        ticketRepository.save(ticket);
        return new TicketResponseDTO(ticket);
    }
}
