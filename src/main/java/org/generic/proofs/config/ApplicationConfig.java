package org.generic.proofs.config;

import org.generic.proofs.infrastructure.publisher.KafkaPublisher;
import org.generic.proofs.repository.AuthorRepository;
import org.generic.proofs.repository.BookRepository;
import org.generic.proofs.service.BookService;
import org.generic.proofs.service.impl.BookServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public BookService bookService(BookRepository bookRepository,
                                   AuthorRepository authorRepository,
                                   KafkaPublisher kafkaPublisher) {
        return new BookServiceImpl(bookRepository, authorRepository, kafkaPublisher);
    }
}
