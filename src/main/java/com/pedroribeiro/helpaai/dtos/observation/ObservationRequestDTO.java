package com.pedroribeiro.helpaai.dtos.observation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObservationRequestDTO {

    @NotNull(message = "Observação deve ser informada")
    @NotEmpty(message = "Observação não pode estar em branco")
    private String observation;
}
