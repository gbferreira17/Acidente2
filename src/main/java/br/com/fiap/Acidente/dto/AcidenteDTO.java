package br.com.fiap.Acidente.dto;

import br.com.fiap.Acidente.model.Acidente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AcidenteDTO(
        Long acidenteId,

        @NotBlank(message = "O modelo do veículo é obrigatório!")
        String modeloVeiculo,

        @NotBlank(message = "A placa do veículo é obrigatório!")
        String placaVeiculo,

        @NotNull
        LocalDate dataOcorrencia,

        @NotBlank(message = "O endereço é obrigatório!")
        String endereco
) {
    public AcidenteDTO(Acidente acidente){
        this(
                acidente.getAcidenteId(),
                acidente.getModeloVeiculo(),
                acidente.getPlacaVeiculo(),
                acidente.getDataOcorrencia(),
                acidente.getEndereco()
        );
    }

}
