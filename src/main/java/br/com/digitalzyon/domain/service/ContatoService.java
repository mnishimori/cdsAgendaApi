package br.com.digitalzyon.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Page<Contato> listarPaginado(Pageable pageable) {
		return contatoRepository.findAll(pageable);
	}
	
	public Contato buscarPorId(Integer id) {
		return contatoRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Contato não encontrato"));
	}
	
	public Optional<Contato> buscarContatoPorId(Integer id) {
		return contatoRepository.findById(id);
	}

	@Transactional
	public void deletar(Integer id) {
		contatoRepository.deleteById(id);
	}

}
