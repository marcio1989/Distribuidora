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

import br.com.distribuidora.model.Cliente;
import br.com.distribuidora.service.ClienteService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clientes/api/")
public class ClienteController {
	
	@Autowired
	private ClienteService  clienteService;
	

	@PostMapping("/v1")
	public Cliente salvarV1(@RequestBody Cliente cliente) {
		return this.clienteService.salvar(cliente);
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
			value="Cadastrar um Cliente novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Cliente.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Cliente> salvarV2(@RequestBody Cliente cliente){
		return ResponseEntity.ok().body(this.clienteService.salvar(cliente));
	}
	
	@GetMapping("/v1")//versão das listas
	public List<Cliente> listarV1() {
		return this.clienteService.listaClientes();
	}
	
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Cliente>> listarV2() {
		return ResponseEntity.ok().body(this.clienteService.listaClientes());
	}
	
	@PutMapping("/v1/{id}")
	public Cliente editarClienteId1(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		Cliente clienteDoBancoDeDados = this.clienteService.buscaPorIdCliente(id);
		BeanUtils.copyProperties(cliente, clienteDoBancoDeDados, "id");
		this.clienteService.salvar(clienteDoBancoDeDados);
		return clienteDoBancoDeDados;
	}
	
	@PutMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Cliente> editarClienteId2(@PathVariable("id") Long id, @RequestBody Cliente cliente){
		Cliente clienteDoBancoDeDados = this.clienteService.buscaPorIdCliente(id);
		BeanUtils.copyProperties(cliente, clienteDoBancoDeDados, "id");
		return ResponseEntity.ok().body(this.clienteService.salvar(clienteDoBancoDeDados));
	}
	
	@DeleteMapping("/v1/{id}")
	public String deletar1(@PathVariable("id") Long id) {
		this.clienteService.remover(this.clienteService.buscaPorIdCliente(id));
		return "Cliente informado excluído com sucesso!";
	}
	
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK )
	public String deletar2(@PathVariable("id") Long id) {
		this.clienteService.remover(this.clienteService.buscaPorIdCliente(id));
		return "Cliente informado excluído com sucesso!";
		
	}
	
}
