package com.sabel.bookstore;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/shop")
public class BookRessource {
	
	private BookService bs;
	
	public BookRessource() {
		bs = new BookService();
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/ping")
	public String ping(){
		return "Die aktuelle Systemzeit ist " + new Date(); 
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Book> getAll(){
		return bs.getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	@Path("/books/id{id}")
	public Book getBook(@PathParam("id")int id){
		return bs.get(id);
	}
	
	@POST
	@Path("/new")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String create(
			@FormParam("title") String title,
			@FormParam("author")String author,
			@FormParam("isbn")String isbn,
			@FormParam("publisher")String publisher ){
		Book b = new Book();
		b.setTitle(title);
		b.setAuthor(author);
		b.setIsbn(isbn);
		b.setPublisher(publisher);
		bs.save(b);
		return "<html><head></head><body><p>Buch " + b + "erfolgreich hinzugefuegt!";
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public Book deleteBook(int id){
		Book b = bs.get(id);
		bs.deleteBook(b);
		return b;
	}

}
