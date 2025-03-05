package com.pedroribeiro.helpaai.dtos.ticket;


import com.pedroribeiro.helpaai.dtos.observation.ObservationRequestDTO;
import com.pedroribeiro.helpaai.enums.Priority;
import com.pedroribeiro.helpaai.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRequestDTO {

    private String title;
    private Priority priority;
    private Status status;
    private List<ObservationRequestDTO> observations = new ArrayList<>();
    private Integer categoryId;
}
