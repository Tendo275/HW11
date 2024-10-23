package ru.dmalomoshin.books.controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dmalomoshin.books.model.Book;
import ru.dmalomoshin.books.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@AllArgsConstructor
public class BookController {

    private final Counter allBooksCounter = Metrics.counter("all_books_count");

    private final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<Book>> getAllBooks() {
        allBooksCounter.increment();
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book updatedBook) {
        Book book = bookService.updateBook(id, updatedBook);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

}
