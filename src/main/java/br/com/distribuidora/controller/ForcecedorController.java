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

import br.com.distribuidora.model.Fornecedor;
import br.com.distribuidora.repository.FornecedorRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @author marcio
 *
 */
@RestController
@RequestMapping("/fornecedores/api/")
public class ForcecedorController {
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@PostMapping("/v1")
	public Fornecedor salvarV1(@RequestBody Fornecedor fornecedor) {
		 this.fornecedorRepository.save(fornecedor);
		 return fornecedor;
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
			value="Cadastrar um Fornecedor novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Fornecedor.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Fornecedor> salvarV2(@RequestBody Fornecedor fornecedor){
		return ResponseEntity.ok().body(this.fornecedorRepository.save(fornecedor));
	}
	
	@GetMapping("/v1")
	public List<Fornecedor> listarV1() {
		return this.fornecedorRepository.findAll();
	}
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Fornecedor>> listarV2(){
		return ResponseEntity.ok().body(this.fornecedorRepository.findAll());
	}
	
	@PutMapping("/v1/{id}")
	public Fornecedor editarFornecedorV1(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor) {
		Fornecedor fornecedorDoBancoDeDados = this.fornecedorRepository.findById(id).get();
		BeanUtils.copyProperties(fornecedor, fornecedorDoBancoDeDados, "id");
		this.fornecedorRepository.save(fornecedorDoBancoDeDados);
		return fornecedorDoBancoDeDados;
	}
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Fornecedor> editarFornecedorV2(@PathVariable("id") Long id, @RequestBody Fornecedor fornecedor){
		Fornecedor fornecedorDoBancoDeDados = this.fornecedorRepository.findById(id).get();
		BeanUtils.copyProperties(fornecedor, fornecedorDoBancoDeDados, "id");
		return ResponseEntity.ok().body(this.fornecedorRepository.save(fornecedorDoBancoDeDados));
	}
	
	@DeleteMapping("/v1/{id}")
	public String deletarV1(@PathVariable("id") Long id) {
		this.fornecedorRepository.deleteById(id);
		return "Fornecedor excluído com sucesso!";
	}
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletarV2(@PathVariable("id") Long id) {
		this.fornecedorRepository.deleteById(id);
		return "Fornecedor foi excluído";
	}
}
