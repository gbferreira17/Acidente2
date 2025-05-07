package model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class ErroMsgModel {
    @Expose
    private String email;
    @Expose
    private String modeloVeiculo;
    @Expose
    private String placaVeiculo;
    @Expose
    private String dataOcorrencia;
    @Expose
    private String endereco;
}
