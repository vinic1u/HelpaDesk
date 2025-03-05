package com.pedroribeiro.helpaai.dtos.observation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObservationRequestDTO {

    private String observation;
}
