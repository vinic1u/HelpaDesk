package com.pedroribeiro.helpaai.dtos.ticket;


import com.pedroribeiro.helpaai.dtos.observation.ObservationRequestDTO;
import com.pedroribeiro.helpaai.enums.Priority;
import com.pedroribeiro.helpaai.enums.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {

    @NotNull(message = "Titulo precisa ser informado")
    @Size(min = 3,max = 100,message = "Titulo deve ter entre 3 a 100 caracteres")
    private String title;

    @NotNull(message = "Prioridade deve ser informada")
    private Priority priority;

    @NotNull(message = "Status deve ser Informado")
    private Status status;


    private List<ObservationRequestDTO> observations = new ArrayList<>();

    @NotNull(message = "Categoria deve ser informada")
    private Integer categoryId;
}
