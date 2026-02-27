package org.generic.proofs.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.generic.proofs.infrastructure.publisher.KafkaPublisher;
import org.generic.proofs.model.JpaAuthor;
import org.generic.proofs.model.JpaBook;
import org.generic.proofs.repository.AuthorRepository;
import org.generic.proofs.repository.BookRepository;
import org.generic.proofs.service.BookService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final KafkaPublisher kafkaPublisher;

    public BookServiceImpl(BookRepository bookRepository,
                           AuthorRepository authorRepository,
                           KafkaPublisher kafkaPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.kafkaPublisher = kafkaPublisher;
    }

    @Override
    @Transactional
    public void createBook(String name, String author) {

        var authorEntity = authorRepository.save(new JpaAuthor(author));
        var bookEntity = new JpaBook(name);
        bookEntity.setAuthors(Set.of(authorEntity));

        if (author.endsWith("Troisi")) {
            throw new RuntimeException("Wrong author name: " + author);
        }
        bookRepository.save(bookEntity);
    }

    @Override
    @Transactional
    public void createBookAndPublishDomainEvent(String name, String author) {

        var authorEntity = authorRepository.save(new JpaAuthor(author));
        var bookEntity = new JpaBook(name);
        bookEntity.setAuthors(Set.of(authorEntity));

        if (author.endsWith("Troisi")) {
            throw new RuntimeException("Wrong author name: " + author);
        }
        bookRepository.save(bookEntity);

        kafkaPublisher.publish("book-topic", "Book created: " + name + " by " + author);
    }
}
