package model;

import br.com.fiap.Acidente.model.Role;
import com.google.gson.annotations.Expose;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AcidenteModel {

    @Expose(serialize = false)
    private String acidenteId;
    @Expose
    private String modeloVeiculo;
    @Expose
    private String placaVeiculo;
    @Expose
    private String dataOcorrencia;
    @Expose
    private String endereco;

}
