package br.com.fiap.Acidente.controller;


import br.com.fiap.Acidente.dto.AcidenteDTO;
import br.com.fiap.Acidente.model.Acidente;
import br.com.fiap.Acidente.service.AcidenteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acidente")
public class AcidenteController {

    @Autowired
    private AcidenteService acidenteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AcidenteDTO registrar(@RequestBody @Valid AcidenteDTO acidente){
        return acidenteService.registrarAcidente(acidente);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AcidenteDTO alterar(@RequestBody @Valid AcidenteDTO acidente){
        return acidenteService.alterarAcidente(acidente);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AcidenteDTO> listarTodos(){

        return acidenteService.listarTodos();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id){

         acidenteService.excluir(id);
    }

}
