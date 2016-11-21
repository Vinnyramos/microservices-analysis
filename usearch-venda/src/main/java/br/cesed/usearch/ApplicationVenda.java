package br.cesed.usearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication
public class ApplicationVenda extends SpringBootServletInitializer {

    public static void main(final String[] args) {
        SpringApplication.run(ApplicationVenda.class, args);
    }

    @Override
    protected final SpringApplicationBuilder configure(final SpringApplicationBuilder application) {
        return application.sources(ApplicationVenda.class);
    }
}
