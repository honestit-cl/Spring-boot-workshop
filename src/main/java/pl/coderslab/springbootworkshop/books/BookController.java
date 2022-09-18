package pl.coderslab.springbootworkshop.books;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
// @Slf4j -> private static Logger log = LoggerFactory.getLogger(BookController.class);
@Slf4j
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<Book> getAllBooks() {
    return bookService.getAllBooks();
  }

  @PostMapping
  public Book createBook(@RequestBody @Valid Book book) {
    log.info("Book requested to be created: {}", book);
    return bookService.createBook(book);
  }

  @PutMapping("/{id}")
  public Book updateBook(@PathVariable Long id, @RequestBody @Valid Book book) {
    log.info("Book requested to be updated: {}", book);
    if (!id.equals(book.getId())) {
      throw new ResponseStatusException(
          HttpStatus.BAD_REQUEST, "Id from payload does not match path");
    }
    return bookService.updateBook(book);
  }

  @DeleteMapping("/{id}")
  public Book deleteBook(@PathVariable Long id, Authentication authentication) {
    String usernameFromAnnotation = authentication.getName();
    String usernameFromContext = SecurityContextHolder.getContext().getAuthentication().getName();
    log.info(
        "Deleting book (id = {}) by ({} | {})", id, usernameFromAnnotation, usernameFromContext);
    Book book = bookService.getBook(id);
    log.info("Book requested to be deleted: {}", book);

    return bookService.deleteBook(book);
  }

  @GetMapping("/{id}")
  public Book getBook(@PathVariable Long id) {
    Book book = bookService.getBook(id);
    log.debug("Book requested: {}", id);

    return book;
  }
}
