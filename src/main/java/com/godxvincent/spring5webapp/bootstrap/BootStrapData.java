package com.godxvincent.spring5webapp.bootstrap;

import com.godxvincent.spring5webapp.domain.Author;
import com.godxvincent.spring5webapp.domain.Book;
import com.godxvincent.spring5webapp.domain.Publisher;
import com.godxvincent.spring5webapp.repositories.AuthorRepository;
import com.godxvincent.spring5webapp.repositories.BookRepository;
import com.godxvincent.spring5webapp.repositories.PublisherRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/*
* Con el @component spring boot sabe el esta encargado de administrar la inicialización de este componente.
* */
@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author erick = new Author("Erick", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234509");
        Publisher anyone1 = new Publisher("Ricardo", "Line 1", "bogota", "Colombia","123455");
        /* Siempre se debe grabar las entidades padres primero, luego se graba la dependencia y
         luego se vuelve a actualizar el objeto padre
         Sin embargo, puede presentarse a veces un error de timing con los procesos de salvado entonces se sugiere marcar la relación
         con cascade.all Ver Link
         https://rajendraprasadpadma.medium.com/object-references-an-unsaved-transient-instance-save-the-transient-instance-before-flushing-1bede249108
         */
        publisherRepository.save(anyone1);


        anyone1.getBooks().add(ddd);
        erick.getBooks().add(ddd);
        ddd.getAuthors().add(erick);
        ddd.setPublisher(anyone1);


        authorRepository.save(erick);
        bookRepository.save(ddd);
        publisherRepository.save(anyone1);


        Author ron = new Author("Ron", "Jhonson");
        Book noEJB = new Book("J2EE Development Without EJB", "123124235434");
        ron.getBooks().add(noEJB);

        anyone1.getBooks().add(noEJB);

        noEJB.getAuthors().add(ron);
        noEJB.setPublisher(anyone1);

        authorRepository.save(ron);
        bookRepository.save(noEJB);
        publisherRepository.save(anyone1);


        System.out.println("Started in BootStraped");
        System.out.println("Number of books "+ bookRepository.count());
        System.out.println("Number of authors "+ authorRepository.count());
        System.out.println("Number of publishers "+ publisherRepository.count());
        if (authorRepository.count() == 0) {
            throw new Exception();
        }


    }
}
