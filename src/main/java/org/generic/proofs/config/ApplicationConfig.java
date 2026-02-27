package org.generic.proofs.config;

import org.generic.proofs.infrastructure.publisher.KafkaPublisher;
import org.generic.proofs.repository.AuthorRepository;
import org.generic.proofs.repository.BookRepository;
import org.generic.proofs.service.BookService;
import org.generic.proofs.service.ValidationService;
import org.generic.proofs.service.impl.BookServiceImpl;
import org.generic.proofs.service.impl.ValidationServiceImpl;
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

    @Bean
    public ValidationService validationService(ValidatorChainSettings validatorChainSettings) {
        return new ValidationServiceImpl(validatorChainSettings);
    }
}
