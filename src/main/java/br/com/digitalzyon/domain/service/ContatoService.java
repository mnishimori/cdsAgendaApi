package br.com.digitalzyon.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalzyon.domain.model.Contato;
import br.com.digitalzyon.domain.repository.ContatoRepository;

@Service
public class ContatoService {
	
	@Autowired
	private ContatoRepository contatoRepository;
	
	@Transactional
	public Contato salvar(Contato contato) {
		return contatoRepository.save(contato);
	}
	
	public List<Contato> listar() {
		return contatoRepository.findAll();
	}
	
	public Contato buscarPorId(Integer id) {
		return contatoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Contato não encontrato"));
	}

	@Transactional
	public void deletar(Integer id) {
		contatoRepository.deleteById(id);
	}

}
