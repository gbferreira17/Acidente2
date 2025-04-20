package br.com.fiap.Acidente.repository;

import br.com.fiap.Acidente.model.Acidente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcidenteRepository extends JpaRepository<Acidente,Long> {



}
