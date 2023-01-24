package com.distribuida.rest;

import com.distribuida.db.Book;
import com.distribuida.servicios.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/books")
public class BookRest {
    @Inject private BookRepository bookRepository;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Book findById(@PathParam("id") Integer id){
        return bookRepository.findById(id);
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> findAll () {

        return bookRepository.findAll();
    }
    @DELETE
    @Path("/{id}")
    public Response delete (@PathParam("id") Integer id){
        bookRepository.delete(id);

        return Response.status((Response.Status.OK) ).build();
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Book b){

        bookRepository.create(b);

        return Response.status(Response.Status.CREATED).build();
    }
    @PUT @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update (Book b, @PathParam("id") Integer id){
        b.setId(id);
        bookRepository.update(b);
        return Response.status((Response.Status.OK) ).build();
    }

}
