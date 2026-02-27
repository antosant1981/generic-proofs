package org.generic.proofs.controller;

import org.generic.proofs.controller.resource.BookResource;
import org.generic.proofs.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    ResponseEntity<BookResource> createBook(@RequestBody BookResource bookResource) {
        bookService.createBookAndPublishDomainEvent(bookResource.name(), bookResource.author());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResource);
    }
}
