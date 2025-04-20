package br.com.fiap.Acidente.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tb_acidente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

public class Acidente {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "SEQ_ACIDENTE"
    )
    @SequenceGenerator(
            name = "SEQ_ACIDENTE",
            sequenceName = "SEQ_ACIDENTE",
            allocationSize = 1
    )
    @Column(name = "acidente_id")
    private Long acidenteId;

    @Column(name = "modelo_veiculo")
    private String modeloVeiculo;

    @Column(name = "placa_veiculo")
    private String placaVeiculo;

    @Column(name = "data_ocorrencia")
    private LocalDate dataOcorrencia;

    private String endereco;
}
