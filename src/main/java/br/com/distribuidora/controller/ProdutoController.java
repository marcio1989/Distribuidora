package br.com.distribuidora.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.distribuidora.model.Produto;
import br.com.distribuidora.service.ProdutoService;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author marcio
 *
 */
@Data
@RestController
@RequestMapping("/produtos/api/")
public class ProdutoController {//classe controller recebe as requisições
	//@Autowired
	//private ProdutoRepository repository; //classe controller não precisa saber do repository
	
	@Autowired
	private ProdutoService produtoService; // classe controller referencia a interface service 
	
	@PostMapping("/v1")
	public Produto salvarV1(@RequestBody Produto produto) {
		return this.produtoService.salvar(produto);
	}
	/*
	 * Olá turma, logo abaixo está o método da aula de hoje (11/11/2020)
	 * que o professor Carlos passou para todos fazer em cima da anotação @PostMapping
	 * da versão 2.
	 * A descrição e o entendimento da aula de hoje fica a critério de cada um.
	 * Lembrando que precisa ser criado a classe "SwaggerConfiguration" dentro do pacote "swagger".
	 * URL da documentação: http://localhost:8081/swagger-ui.html
	 * */
	@ApiOperation(
			value="Cadastrar um Produto novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Produto.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Produto> salvarV2(@RequestBody Produto produto){
		return ResponseEntity.ok().body(this.produtoService.salvar(produto));
	}
	
	@GetMapping("/v1")
	public List<Produto> listar(){
		return this.produtoService.listarProdutos();
	}
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Produto>> listarV2(){
		return ResponseEntity.ok().body(this.produtoService.listarProdutos());
	}
	@PutMapping("/v1/{id}")
	public Produto editarProdutoId1(@PathVariable("id") Long id, @RequestBody Produto produto) {
		Produto produtoDoBancoDeDados = this.produtoService.buscarPorIdProduto(id);
		BeanUtils.copyProperties(produto, produtoDoBancoDeDados, "id");
		this.produtoService.salvar(produtoDoBancoDeDados);
		return produtoDoBancoDeDados;
	}
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Produto> editarProdutoId2(@PathVariable("id") Long id, @RequestBody Produto produto){
		Produto produtoDoBandoDeDados = this.produtoService.buscarPorIdProduto(id);
		BeanUtils.copyProperties(produto, produtoDoBandoDeDados, "id");
		return ResponseEntity.ok().body(this.produtoService.salvar(produtoDoBandoDeDados));
	}
	
	@DeleteMapping("/v1/{id}")
	public String Deletar1(@PathVariable("id") Long id) {
		this.produtoService.removerPorProduto(this.produtoService.buscarPorId(id));
		return "Produto excluído com sucesso!";
	}
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletar2(@PathVariable("id") Long id) {
		this.produtoService.remover(this.produtoService.buscarPorId(id));
		return "Produto foi deletado!";
	}
	
	public Produto buscarProdutoId(@PathVariable("id") Long id, @RequestBody Produto produto) {
		Produto produtoBancoDeDados = this.produtoService.buscarPorId(id);
		BeanUtils.copyProperties(produto, produtoBancoDeDados, "id");
		this.produtoService.salvar(produtoBancoDeDados);
		return produtoBancoDeDados;
	}
	
//	@GetMapping
//	public List<Produto> listar() {
//		return this.repository.findAll();
//	}
//	@PutMapping("/{id}")
//	public Produto editar(@PathVariable("id") Long id,@RequestBody Produto produto) {
//		Produto produtoDoBancoDeDados = this.repository.findById(id).get();
//		BeanUtils.copyProperties(produto, produtoDoBancoDeDados, "id");
//		this.repository.save(produtoDoBancoDeDados);
//		return produtoDoBancoDeDados;
//	}
//	@DeleteMapping("/{id}")
//	public void deletar(@PathVariable("id") Long id) {
//		this.repository.deleteById(id);
//	} 
//	@PostMapping
//	public Produto salvar(@RequestBody Produto produto) {
//		return this.repository.save(produto);
//	}
}
