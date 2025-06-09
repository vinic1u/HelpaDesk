package com.pedroribeiro.helpaai.dtos.observation;

import com.pedroribeiro.helpaai.dtos.user.UserResponseDTO;
import com.pedroribeiro.helpaai.entities.Observation;
import com.pedroribeiro.helpaai.entities.Ticket;
import com.pedroribeiro.helpaai.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObservationResponseDTO {

    private String observation;
    private UserResponseDTO author;
    private LocalDateTime createdAt;

    public ObservationResponseDTO(Observation entity){
        this.observation = entity.getObservation();
        this.author = entity.getUser() != null ? new UserResponseDTO(entity.getUser()) : null;
        this.createdAt = entity.getCreatedAt();
    }
}
