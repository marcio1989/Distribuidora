package br.com.distribuidora.service;

import java.util.List;

import br.com.distribuidora.model.Produto;

public interface ProdutoService {// interface todos os métodos são públicos e abstratos ou seja: não possui corpo
	
	Produto salvar(Produto produto);
	
	List<Produto> listarProdutos();
	
	void removerPorProduto(Produto produto);
	
	void removerPorId(Long idProduto);
	
	Produto buscarPorId(Long idProduto);

	void remover(Produto buscarPorId);

	Produto buscarPorIdProduto(Long id);
}
