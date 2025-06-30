package com.pedroribeiro.helpaai.dtos.ticket;

import com.pedroribeiro.helpaai.enums.Priority;
import com.pedroribeiro.helpaai.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketUpdateRequestDTO {

    @NotNull(message = "Status deve ser Informado")
    private Status status;

    @NotNull(message = "Prioridade deve ser informada")
    private Priority priority;

    @NotNull(message = "Categoria deve ser informada")
    private Integer categoryId;
}
