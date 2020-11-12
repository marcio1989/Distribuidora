package br.com.distribuidora.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.distribuidora.exception.EntidadeNaoEncontradaException;
import br.com.distribuidora.model.Produto;
import br.com.distribuidora.repository.ProdutoRepository;

@Service
public class ProdutoServiceImpl implements ProdutoService {//na classe IMPL precisa implementar todos os métodos da interface
		@Autowired
		private ProdutoRepository produtoRepository;//classe IMPL reconhece o repositorio

		@Override
		@Transactional//serve para mudança de status do banco de dados
		public Produto salvar(Produto produto) {
			return this.produtoRepository.save(produto);//método para salvar o produto
			
		}

		@Override
		public List<Produto> listarProdutos() {
			return this.produtoRepository.findAll();
		}

		@Override
		@Transactional
		public void removerPorProduto(Produto produto) {
			this.produtoRepository.delete(produto);
			
		}

		@Override
		@Transactional
		public void removerPorId(Long idProduto) {
			this.produtoRepository.deleteById(idProduto);
			
		}

		@Override
		public Produto buscarPorId(Long idProduto) {
			Optional<Produto> produto = this.produtoRepository.findById(idProduto);//findById retorna o Optional
			//return this.repository.findById(idProduto).get();
			if(produto.get() == null) {
				throw new
					EntidadeNaoEncontradaException("Não foi possivel localizar o produto pesquisado!!!");
			}else {
				return produto.get();
			}
		}

		@Override
		public void remover(Produto buscarPorId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Produto buscarPorIdProduto(Long idProduto) {
			return this.produtoRepository.findById(idProduto).get();
		}

	
}
