package com.godxvincent.spring5webapp.bootstrap;

import com.godxvincent.spring5webapp.domain.Author;
import com.godxvincent.spring5webapp.domain.Book;
import com.godxvincent.spring5webapp.repositories.AuthorRepository;
import com.godxvincent.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
* Con el @component spring boot sabe el esta encargado de administrar la inicialización de este componente.
* */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author erick = new Author("Erick", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234509");
        erick.getBooks().add(ddd);
        ddd.getAuthors().add(erick);

        authorRepository.save(erick);
        bookRepository.save(ddd);

        Author ron = new Author("Ron", "Jhonson");
        Book noEJB = new Book("J2EE Development Without EJB", "123124235434");
        ron.getBooks().add(noEJB);
        noEJB.getAuthors().add(ron);
        authorRepository.save(ron);
        bookRepository.save(noEJB);

        System.out.println("Started in BootStraped");
        System.out.println("Number of books "+ bookRepository.count());
        System.out.println("Number of authors "+ authorRepository.count());


    }
}
