package br.com.digitalzyon.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.digitalzyon.domain.model.Contato;
import br.com.digitalzyon.domain.service.ContatoService;

@CrossOrigin("*")
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
	
	@GetMapping("/lista-paginada")
	public Page<Contato> listarPaginado(@PageableDefault(page = 0, size = 5) Pageable pageable){
		return contatoService.listarPaginado(pageable);
	}
	
	@PutMapping("/favorito/{id}")
	public void favoritar(@PathVariable Integer id) {
		Contato contato = contatoService.buscarPorId(id);
		
		if (contato != null) {
			Boolean favorito = contato.getFavorito() == Boolean.TRUE;
			contato.setFavorito(!favorito);
			contatoService.salvar(contato);
		}
	}
	
	@PutMapping("{id}/foto")
	public byte[] addPhoto(@PathVariable Integer id, 
			@RequestParam("foto") Part arquivo) {
		
		Optional<Contato> contato = contatoService.buscarContatoPorId(id);
		
		return contato.map(c -> {
			try {
				InputStream is = arquivo.getInputStream();
				byte[] bytes = new byte[(int) arquivo.getSize()];
				IOUtils.readFully(is, bytes);
				c.setFoto(bytes);
				contatoService.salvar(c);
				is.close();
				return bytes;
			} catch (IOException e) {
				return null;
			}
		}).orElse(null);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(@PathVariable Integer id) {
		contatoService.deletar(id);
	}

}
