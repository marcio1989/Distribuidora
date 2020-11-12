package br.com.distribuidora.model;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

/**
 * @author marcio
 *
 */
@Data
@Entity
@Table(name="tb_produtos")
public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int codigo;
	private String nome;
	private double preco;
	
	@JsonBackReference
	@ManyToOne
	private Cliente cliente;
	
	
	
	
	
	//@JsonManagedReference
	//@JsonBackReference(value = "cliente")
	//@ManyToOne
	//private Cliente cliente; // produto tem vários clientes e produto tem um cliente
	
	//@JsonBackReference(value = "fornecedor")
	//@OneToMany
	//@JsonBackReference(value = "fornecedor")
	//@ManyToOne
	//private Fornecedor fornecedor;
}
