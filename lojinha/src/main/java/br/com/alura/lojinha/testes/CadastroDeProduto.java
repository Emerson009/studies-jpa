package br.com.alura.lojinha.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.lojinha.dao.CategoriaDAO;
import br.com.alura.lojinha.dao.ProdutoDAO;
import br.com.alura.lojinha.model.Categoria;
import br.com.alura.lojinha.model.Produto;
import br.com.alura.lojinha.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		Produto produto = produtoDAO.buscarPorId(1l);
		System.out.println(produto.getPreco());
		
		List<Produto> buscarTodos = produtoDAO.buscarPorNomeDaCategoria("CELULARES");
		buscarTodos.forEach(p -> System.out.println(p.getNome()));
		
		BigDecimal precoDoProduto = produtoDAO.buscarPrecoDoProdutoComNome("Xiaomi Redmi 10");
		System.out.println(precoDoProduto);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");
		Produto celular = new Produto("Xiaomi Redmi 10", "Muito Legal", new BigDecimal("800"), celulares);
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		CategoriaDAO categoriaDAO = new CategoriaDAO(em);
		
		em.getTransaction().begin();
		
		categoriaDAO.cadastrar(celulares);
		produtoDAO.cadastrar(celular);
		
		em.getTransaction().commit();
		em.close();
	}

}
