package br.com.distribuidora.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.distribuidora.exception.EntidadeComPendencialException;
import br.com.distribuidora.model.Cliente;
import br.com.distribuidora.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	@Transactional
	public Cliente salvar(Cliente cliente) {
		return this.clienteRepository.save(cliente);
		
	}

	@Override
	@Transactional
	public void remover(Cliente cliente) {
		if (!cliente.getProdutos().isEmpty()) {
			throw new EntidadeComPendencialException("Cliente não pode ser removido, existem produtos vinculados");
		}else {		
			this.clienteRepository.delete(cliente);
		}
		
	}
	@Override
	public List<Cliente> listaClientes(){
		return this.clienteRepository.findAll();
	}

	@Override
	public Cliente buscaPorIdCliente(Long idCliente) {
		return this.clienteRepository.findById(idCliente).get();
	}

}
