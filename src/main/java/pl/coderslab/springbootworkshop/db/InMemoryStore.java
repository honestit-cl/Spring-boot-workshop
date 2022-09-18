package pl.coderslab.springbootworkshop.db;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.coderslab.springbootworkshop.books.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class InMemoryStore {

  private ConcurrentMap<Long, Book> store = new ConcurrentHashMap<>();
  private AtomicLong idMarker = new AtomicLong(1);

  public List<Book> findAll() {
    return new ArrayList<>(store.values());
  }

  public Book store(Book book) {
    book.setId(idMarker.getAndIncrement());
    store.put(book.getId(), book);

    return book;
  }

  public Book findById(Long id) {
    return store.get(id);
  }

  public Book update(Book book) {
    store.replace(book.getId(), book);
    return book;
  }

  public Book delete(Book book) {
    store.remove(book.getId());
    return book;
  }
}
