package com.distribuida.config;

import com.distribuida.servicios.BookRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import javax.sql.DataSource;


@ApplicationScoped
public class DbConfig {
    @Inject
    @ConfigProperty(name = "db.user")
    private String dbUser;
    @Inject
    @ConfigProperty(name = "db.password")
    private String dbPassword;
    @Inject
    @ConfigProperty(name = "db.url")
    private String dbUrl;

    @Produces
    @ApplicationScoped
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(dbUser);
        config.setPassword(dbPassword);
        HikariDataSource dataSource = new HikariDataSource(config);
        return dataSource;
    }
    @Produces
    @ApplicationScoped
    public Jdbi jdbi (DataSource dataSource){
        Jdbi ret = Jdbi.create(dataSource);
        ret.installPlugin(new SqlObjectPlugin());
        return ret;
    }
    @Produces
    @ApplicationScoped
    public BookRepository bookRepository(Jdbi jdbi){
        return jdbi.onDemand(BookRepository.class);
    }
}
