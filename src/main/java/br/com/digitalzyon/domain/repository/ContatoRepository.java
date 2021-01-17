package br.com.digitalzyon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digitalzyon.domain.model.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Integer>{

}
