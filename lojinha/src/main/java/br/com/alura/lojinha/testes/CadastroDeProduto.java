package br.com.alura.lojinha.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.alura.lojinha.dao.ProdutoDAO;
import br.com.alura.lojinha.model.Produto;
import br.com.alura.lojinha.util.JPAUtil;

public class CadastroDeProduto {
	
	public static void main(String[] args) {
		Produto celular = new Produto("Xiaomi Redmi 10", "", new BigDecimal("800"), null);
		
		
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		em.getTransaction().begin();
		produtoDAO.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}

}
