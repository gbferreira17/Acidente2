package br.com.fiap.Acidente.service;

import br.com.fiap.Acidente.dto.RegisterDTO;
import br.com.fiap.Acidente.dto.UsuarioExibicaoDTO;
import br.com.fiap.Acidente.model.Usuario;
import br.com.fiap.Acidente.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioExibicaoDTO salvarUsuario(RegisterDTO registerDTO){
        String senhaCriptografada = new BCryptPasswordEncoder().encode(registerDTO.password());

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(registerDTO, usuario);
        usuario.setPassword(senhaCriptografada);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioExibicaoDTO(usuarioSalvo);
    }

}
