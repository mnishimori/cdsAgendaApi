package br.com.digitalzyon.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalzyon.domain.model.Contato;
import br.com.digitalzyon.domain.service.ContatoService;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {
	
	@Autowired
	private ContatoService contatoService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Contato cadastrar(@RequestBody Contato contato) {
		return contatoService.salvar(contato);
	}
	
	@GetMapping
	public List<Contato> listar(){
		return contatoService.listar();
	}
	
	@PatchMapping("/favorito/{id}")
	public void favoritar(@PathVariable Integer id, @RequestBody Boolean favorito) {
		Contato contato = contatoService.buscarPorId(id);
		
		if (contato != null) {
			contato.setFavorito(favorito);
			contatoService.salvar(contato);
		}
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		contatoService.deletar(id);
	}

}
