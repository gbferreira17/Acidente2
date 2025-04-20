package br.com.fiap.Acidente.service;

import br.com.fiap.Acidente.dto.AcidenteDTO;
import br.com.fiap.Acidente.model.Acidente;
import br.com.fiap.Acidente.repository.AcidenteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AcidenteService {

    @Autowired
    private AcidenteRepository acidenteRepository;

    public AcidenteDTO registrarAcidente(AcidenteDTO acidenteDTO){
        Acidente acidente = new Acidente();
        BeanUtils.copyProperties(acidenteDTO,acidente);
        return new AcidenteDTO(acidenteRepository.save(acidente));
    }

    public AcidenteDTO alterarAcidente(AcidenteDTO acidenteDTO){
        Acidente acidente = new Acidente();
        BeanUtils.copyProperties(acidenteDTO,acidente);

        Optional<Acidente> acidenteOptional = acidenteRepository.findById(acidente.getAcidenteId());

        if (acidenteOptional.isPresent()){
            return new AcidenteDTO(acidenteRepository.save(acidente));
        }else {
            throw new RuntimeException("Acidente não encontrado");
        }
    }

    public List<AcidenteDTO> listarTodos(){
        return acidenteRepository.findAll().stream().map(AcidenteDTO::new).toList();
    }



    public void excluir(Long id){
        Optional<Acidente> optionalAcidente= acidenteRepository.findById(id);

        if (optionalAcidente.isPresent()){
            acidenteRepository.delete(optionalAcidente.get());
        }else {
            throw new RuntimeException("Acidente não encontrado");
        }

    }

}
