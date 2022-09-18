package pl.coderslab.springbootworkshop.books;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    Book getBook(Long id);

    Book updateBook(Book book);

    Book createBook(Book book);

    Book deleteBook(Book book);

    Book deleteBook(Long id);
}
