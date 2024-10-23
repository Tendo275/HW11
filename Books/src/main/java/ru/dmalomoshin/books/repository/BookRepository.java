package ru.dmalomoshin.books.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dmalomoshin.books.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
