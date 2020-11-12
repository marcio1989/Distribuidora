package br.com.distribuidora.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import br.com.distribuidora.model.Usuario;
import br.com.distribuidora.repository.UsuarioRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuarios/api/")
public class UsuarioController {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/v1")
	public Usuario salvarV1(@RequestBody Usuario usuario) {
		this.usuarioRepository.save(usuario);
		return usuario;
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
			value="Cadastrar um Usuario novo", 
			response=ResponseEntity.class, 
			notes="Essa operação salva um novo registro com as informações do Usuario.")
	@PostMapping("/v2")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<Usuario> salvarV2(@RequestBody Usuario usuario){
		return ResponseEntity.ok().body(this.usuarioRepository.save(usuario));
	}
	
	@GetMapping("/v1")
	public List<Usuario> listarV1(){
		return this.usuarioRepository.findAll();
	}
	@GetMapping("/v2")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<List<Usuario>> listarV2(){
		return ResponseEntity.ok().body(this.usuarioRepository.findAll());
	}
	
	@PutMapping("/v1/{id}")
	public Usuario editarUsuarioV1(@PathVariable("id") Long id,@RequestBody Usuario usuario) {
		Usuario usuarioDoBancoDeDados =  this.usuarioRepository.findById(id).get();
		BeanUtils.copyProperties(usuario, usuarioDoBancoDeDados, "id");
		this.usuarioRepository.save(usuarioDoBancoDeDados);
		return usuarioDoBancoDeDados;
	}
	@PatchMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Usuario> editarUsuarioV2(@PathVariable("id") Long id, @RequestBody Usuario usuario){
		Usuario usuarioDoBancoDeDados =  this.usuarioRepository.findById(id).get();
		BeanUtils.copyProperties(usuario, usuarioDoBancoDeDados, "id");
		return ResponseEntity.ok().body(this.usuarioRepository.save(usuario));
	}
	
	@DeleteMapping("/v1/{id}")
	public String deletarV1(@PathVariable("id") Long id) {
		this.usuarioRepository.deleteById(id);
		return "Usuario excluído com sucesso!";
	}
	@DeleteMapping("/v2/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public String deletarV2(@PathVariable("id") Long id) {
		this.usuarioRepository.deleteById(id);
		return "Usuario deletado!";
	}
	
}
