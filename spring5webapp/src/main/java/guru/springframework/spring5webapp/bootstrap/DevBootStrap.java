package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootStrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootStrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }

    private void initData(){

        Publisher harper = new Publisher();
        harper.setName("Harper Collins");
        harper.setAddress("123 Downing Street, UK");
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234" );
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(harper);
        publisherRepository.save(harper);
        authorRepository.save(eric);
        bookRepository.save(ddd);

        Publisher workx = new Publisher();
        workx.setName("Workx");
        workx.setAddress("711, Davey Boy rd, Atlantis");
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444" );
        noEJB.setPublisher(workx);
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        publisherRepository.save(workx);
        authorRepository.save(rod);
        bookRepository.save(noEJB);
    }


}
