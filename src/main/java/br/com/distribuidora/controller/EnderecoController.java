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

import br.com.distribuidora.model.Endereco;
import br.com.distribuidora.repository.EnderecoRepository;
import io.swagger.annotations.ApiOperation;

/**
 * @author marcio
 *
 */
@RestController
@RequestMapping("/enderecos/api/")
public class EnderecoController {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@PostMapping("/v1")
	public Endereco salvarV1(@RequestBody Endereco endereco) {
		this.enderecoRepository.save(endereco);
		return endereco;
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
			value="Cadastrar um Endereço novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Endereço.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Endereco> salvarV2(@RequestBody Endereco endereco){
		return ResponseEntity.ok().body(this.enderecoRepository.save(endereco));
	}
	
	@GetMapping("/v1")
	public List<Endereco> listarV1(){
		return this.enderecoRepository.findAll();
	}
	@GetMapping("/v2") 
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Endereco>> listarV2() {
		return ResponseEntity.ok().body(this.enderecoRepository.findAll());
	}
	@PutMapping("/v1/{id}")
	public Endereco editarEnderecoV1(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
		Endereco enderecoDoBancoDeDados = this.enderecoRepository.findById(id).get();
		BeanUtils.copyProperties(endereco, enderecoDoBancoDeDados, "id");
		this.enderecoRepository.save(enderecoDoBancoDeDados);
		return enderecoDoBancoDeDados;
	}
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Endereco> editarEnderecoV2(@PathVariable("id") Long id, @RequestBody Endereco endereco){
		Endereco enderecoDoBancoDeDados = this.enderecoRepository.findById(id).get();
		BeanUtils.copyProperties(endereco, enderecoDoBancoDeDados, "id");
		return ResponseEntity.ok().body(this.enderecoRepository.save(enderecoDoBancoDeDados));
	}
	
	@DeleteMapping("/v1/{id}")
	public String deletarV1(@PathVariable("id") Long id) {
		this.enderecoRepository.deleteById(id);
		return "Endereço informado deletado com sucesso!";
	}
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletarV2(@PathVariable("id") Long id) {
		this.enderecoRepository.deleteById(id);
		return "Endereço foi excluído com sucesso!";
	}
	
}
