package ru.dmalomoshin.books.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dmalomoshin.books.aspects.TrackUserAction;
import ru.dmalomoshin.books.model.Book;
import ru.dmalomoshin.books.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository repository;

    @TrackUserAction
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @TrackUserAction
    public Optional<Book> getBookById(Long id) {
        return repository.findById(id);
    }

    @TrackUserAction
    public Book createBook(Book book) {
        return repository.save(book);
    }

    @TrackUserAction
    public Book updateBook(Long id, Book bookDetails) {
        Optional<Book> optionalBook = repository.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setPublicationYear(bookDetails.getPublicationYear());
            repository.save(book);
            return book;
        } else {
            return null;
        }
    }

    @TrackUserAction
    public void deleteBook(Long id) {
        repository.deleteById(id);
    }

}
