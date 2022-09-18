package pl.coderslab.springbootworkshop.books;

import org.springframework.stereotype.Service;
import pl.coderslab.springbootworkshop.db.InMemoryStore;

import java.util.List;

@Service
public class InMemoryBookService implements BookService {

  private final InMemoryStore inMemoryStore;

  public InMemoryBookService(InMemoryStore inMemoryStore) {
    this.inMemoryStore = inMemoryStore;
  }

  @Override
  public List<Book> getAllBooks() {
    return inMemoryStore.findAll();
  }

  @Override
  public Book getBook(Long id) {
    return inMemoryStore.findById(id);
  }

  @Override
  public Book updateBook(Book book) {
    return inMemoryStore.update(book);
  }

  @Override
  public Book createBook(Book book) {
    return inMemoryStore.store(book);
  }

  @Override
  public Book deleteBook(Book book) {
    return inMemoryStore.delete(book);
  }

  @Override
  public Book deleteBook(Long id) {
    return null;
  }
}
