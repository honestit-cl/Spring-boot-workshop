package pl.coderslab.springbootworkshop.books;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaBookService implements BookService {

  private final BookRepository bookRepository;

  public JpaBookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  @Override
  public Book getBook(Long id) {
    return bookRepository
        .findById(id)
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("No book with id = %d found", id)));
  }

  @Override
  public Book updateBook(Book book) {
    return bookRepository
        .findById(book.getId())
        .map(bookRepository::save)
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    String.format("No book with id = %d found", book.getId())));
  }

  @Override
  public Book createBook(Book book) {
    return bookRepository.save(book);
  }

  @Override
  public Book deleteBook(Book book) {
    return bookRepository
        .findById(book.getId())
        .map(
            b -> {
              bookRepository.delete(b);
              return b;
            })
        .orElseThrow(
            () ->
                new IllegalArgumentException(
                    String.format("No book with id = %d found", book.getId())));
  }

  @Override
  public Book deleteBook(Long id) {
    return bookRepository
        .findById(id)
        .map(
            b -> {
              bookRepository.delete(b);
              return b;
            })
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("No book with id = %d found", id)));
  }
}
