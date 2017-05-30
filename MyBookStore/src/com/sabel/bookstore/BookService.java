package com.sabel.bookstore;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class BookService {
	private EntityManagerFactory emf;
	private EntityManager em;
	public BookService() {
		emf = Persistence.createEntityManagerFactory("bookstore");
		em = emf.createEntityManager();
	}
	
	public void save(Book b){
		em.getTransaction().begin();
		em.persist(b);
		em.getTransaction().commit();
	}
	
	public Book get(int id){
		return em.find(Book.class, id);
	}
	
	public List<Book> getAll(){
		TypedQuery<Book> query = em.createQuery("SELECT b FROM b",Book.class);
		List<Book> resultList = query.getResultList();
		return resultList;
	}
	
	public Book deleteBook(Book b){
		Book book = get(b.getId());
		em.getTransaction().begin();
		em.remove(book);
		em.getTransaction().commit();
		return book;
	}
}
