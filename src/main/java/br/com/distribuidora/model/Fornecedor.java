/**
 * 
 */
package br.com.distribuidora.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author marcio
 *
 */
@Data
@Entity
@Table(name="tb_fornecedores")
public class Fornecedor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cnpj;
	private String email;
	private String telefone;
	
	
	//fornecedor lado dominante, não existe produto sem fornecedor
	//criei uma terceira tabela entre fornecedor e produto
	// coluna id para fornecedor e produto
	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "fornecedor_produto",
//	uniqueConstraints = @UniqueConstraint(columnNames = {"id_produto", "id_fornecedor"}),//informa que id produto e id fornecedor são unicos dentro da tabela fornecedor_produto
//	joinColumns = @JoinColumn(name="id_fornecedor"),inverseJoinColumns = @JoinColumn(name="id_produto") ) 
	//@JsonBackReference 
	//@JsonManagedReference
	
	//@OneToMany(mappedBy = "fornecedor", cascade = CascadeType.ALL)
	//@JsonManagedReference
	//@JsonIgnore
	//private List<Produto> produtos;
	
	//@JsonBackReference
	//@JsonBackReference
	//@OneToOne(mappedBy = "fornecedor", cascade = CascadeType.ALL) 
	//private Endereco endereco;
}  
