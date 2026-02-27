package org.generic.proofs.controller;

import org.generic.proofs.controller.resource.BookResource;
import org.generic.proofs.dto.BindingItem;
import org.generic.proofs.service.BookService;
import org.generic.proofs.service.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final ValidationService validationService;

    public BookController(BookService bookService,
                          ValidationService validationService) {
        this.bookService = bookService;
        this.validationService = validationService;
    }

    @PostMapping
    ResponseEntity<BookResource> createBook(@RequestBody BookResource bookResource) {
        bookService.createBookAndPublishDomainEvent(bookResource.name(), bookResource.author());
        return ResponseEntity.status(HttpStatus.CREATED).body(bookResource);
    }

    @PutMapping("/validate/{id}")
    ResponseEntity<String> validateBookMetadata(@RequestBody BindingItem bindingItem) {
        var validationResult = validationService.validate(bindingItem);
        return ResponseEntity.ok(validationResult.validationResponse().name() + " - " + validationResult.message());
    }
}
