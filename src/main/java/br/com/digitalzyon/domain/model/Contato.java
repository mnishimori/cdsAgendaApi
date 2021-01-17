package br.com.digitalzyon.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = "model", name = "contato")
public class Contato {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@CreationTimestamp
	@Column(name = "dt_cadastro")
	private Date dtCadastro;
	
	@NotBlank(message = "Informe o nome do contato")
	@Column(length = 250)
	private String nome;
	
	@NotBlank(message = "Informe o email")
	@Column(length = 250)
	private String email;
	
	@Column
	private Boolean favorito;

}
