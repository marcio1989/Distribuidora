package br.com.distribuidora.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

/**
 * @author marcio
 *
 */
@Data
@Entity
@Table(name="tb_clientes")
public class Cliente implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String cpf;
	private String telefone;
	
	@JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Produto> produtos;
	
	//@OneToOne(mappedBy = "cliente")
	//private Endereco endereco;
	
	//@JsonBackReference
	//@JsonManagedReference
	//@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	//@JsonManagedReference//parte direta da referência
	//@JsonIgnore
	//@OneToMany(mappedBy = "cliente") // cliente tem um e vários produtos e produtos tem um e vários clientes
	//private List<Produto> produtos;
	
	//@JsonBackReference//parte posterior da referência 
	//@OneToOne(mappedBy = "cliente") // cliente tem um endereço e endereço tem um cliente
	//private Endereco endereco;
}
