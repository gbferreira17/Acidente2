package model;

import br.com.fiap.Acidente.model.Role;
import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class UsuarioModel {

    @Expose(serialize = false)
    private Long usuarioId;
    @Expose
    private String nome;
    @Expose
    private String email;
    @Expose(deserialize = false)
    private String password;
    @Expose(deserialize = false)
    private Role role;

}
