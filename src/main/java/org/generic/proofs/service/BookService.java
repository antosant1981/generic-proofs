package org.generic.proofs.service;

public interface BookService {
    void createBook(String name, String author);
    void createBookAndPublishDomainEvent(String name, String author);
}
