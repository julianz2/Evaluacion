package com.distribuida.servicios;

import com.distribuida.db.Book;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

public interface BookRepository {
    @SqlQuery("select * from books where id=?")
    @RegisterBeanMapper(Book.class)
    Book findById(Integer id);
    @SqlQuery("select * from books order by id asc")
    @RegisterBeanMapper(Book.class)
    List<Book> findAll();
    @SqlUpdate("delete from books where id=?")
    void delete(Integer id);
    @SqlUpdate("insert into books (isbn,title,author,price) values(:isbn,:title,:author,:price)")
    void create(@BindBean Book book);
    @SqlUpdate("update  books set isbn=:isbn,title=:title,author=:author,price=:price where id=id)")
    void update(@BindBean Book book);
}
