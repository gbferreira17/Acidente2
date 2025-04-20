package br.com.fiap.Acidente.dto;

import br.com.fiap.Acidente.model.Usuario;

public record UsuarioExibicaoDTO(Long usuarioId, String nome, String email) {

    public UsuarioExibicaoDTO(Usuario usuario){
        this(
                usuario.getUsuarioId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }

}
