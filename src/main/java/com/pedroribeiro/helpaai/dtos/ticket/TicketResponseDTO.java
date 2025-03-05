package com.pedroribeiro.helpaai.dtos.ticket;

import com.pedroribeiro.helpaai.dtos.category.CategoryResponseDTO;
import com.pedroribeiro.helpaai.dtos.observation.ObservationResponseDTO;
import com.pedroribeiro.helpaai.dtos.user.UserResponseDTO;
import com.pedroribeiro.helpaai.entities.Ticket;
import com.pedroribeiro.helpaai.enums.Priority;
import com.pedroribeiro.helpaai.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketResponseDTO {


    private Integer id;

    private String title;

    private Priority priority;

    private LocalDateTime createdAt;

    private LocalDateTime lastInteraction;

    private Status status;

    private List<ObservationResponseDTO> observations = new ArrayList<>();

    private CategoryResponseDTO category;

    private UserResponseDTO client;

    private UserResponseDTO operator;

    public TicketResponseDTO(Ticket entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.priority = entity.getPriority();
        this.createdAt = entity.getCreatedAt();
        this.lastInteraction = entity.getLastInteraction();
        this.status = entity.getStatus();
        this.observations = entity.getObservations().stream().map(ObservationResponseDTO::new).toList();
        this.category = new CategoryResponseDTO(entity.getCategory());
    }
}
