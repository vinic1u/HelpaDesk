package com.pedroribeiro.helpaai.services;

import com.pedroribeiro.helpaai.dtos.observation.ObservationRequestDTO;
import com.pedroribeiro.helpaai.dtos.ticket.TicketRequestDTO;
import com.pedroribeiro.helpaai.dtos.ticket.TicketResponseDTO;
import com.pedroribeiro.helpaai.entities.Category;
import com.pedroribeiro.helpaai.entities.Observation;
import com.pedroribeiro.helpaai.entities.Ticket;
import com.pedroribeiro.helpaai.entities.User;
import com.pedroribeiro.helpaai.enums.Priority;
import com.pedroribeiro.helpaai.enums.Status;
import com.pedroribeiro.helpaai.exceptions.NotAllowedException;
import com.pedroribeiro.helpaai.exceptions.ResourceNotFoundException;
import com.pedroribeiro.helpaai.repositories.CategoryRepository;
import com.pedroribeiro.helpaai.repositories.ObservationRepository;
import com.pedroribeiro.helpaai.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<TicketResponseDTO> findAllTickets(User user){

        List<Ticket> tickets;

        switch (user.getRole()){
            case CLIENT -> tickets = ticketRepository.findClientTickets(user.getEmail());
            case OPERATOR,ADMIN -> tickets = ticketRepository.findAll();
            default -> throw new RuntimeException("Permissão não concedida");
        }
        return tickets.stream().map(TicketResponseDTO::new).toList();
    }

    public TicketResponseDTO findTicketById(User user,Integer id){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Ticket com ID: " + id + " não encontrado!"));

        if(!(ticket.getClient().getId().equals(user.getId()))){
            throw new NotAllowedException("Você não tem permissão para alterar este chamado!");
        }

        return new TicketResponseDTO(ticket);
    }

    public TicketResponseDTO saveTicket(User user,TicketRequestDTO dto){



        Ticket ticket = new Ticket();
        ticket.setTitle(dto.getTitle());
        ticket.setPriority(dto.getPriority());
        ticket.setStatus(dto.getStatus());
        ticket.setClient(user);

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

    public TicketResponseDTO updateTicket(User user,Integer id,TicketRequestDTO dto){
        Ticket ticket = ticketRepository.findById(id)
                        .orElseThrow(()->new ResourceNotFoundException("Ticket de ID: " + id + " não encontrado!"));

        if(!(ticket.getClient().getId().equals(user.getId()))){
            throw new NotAllowedException("Você não tem permissão para alterar este chamado!");
        }

        ticket.setTitle(dto.getTitle());
        ticket.setPriority(dto.getPriority());
        ticket.setStatus(dto.getStatus());

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(()-> new ResourceNotFoundException("Categoria com ID: " +  dto.getCategoryId() + " não encontrada!"));
        ticket.setCategory(category);

        ticketRepository.save(ticket);
        return new TicketResponseDTO(ticket);
    }

    public TicketResponseDTO createObservation(User user,Integer id,ObservationRequestDTO dto){
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Ticket de ID: " + id + " não encontrado!"));

        if(!(ticket.getClient().getId().equals(user.getId()))){
            throw new NotAllowedException("Você não tem permissão para alterar este chamado!");
        }

        Observation observation = new Observation();
        observation.setObservation(dto.getObservation());
        observation.setTicket(ticket);
        observation.setUser(user);
        // TO DO author mapping
        ticket.getObservations().add(observation);
        ticketRepository.save(ticket);
        return new TicketResponseDTO(ticket);
    }
}
