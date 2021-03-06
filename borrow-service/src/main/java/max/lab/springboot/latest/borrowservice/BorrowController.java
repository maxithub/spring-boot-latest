package max.lab.springboot.latest.borrowservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class BorrowController {

    private final BookService bookService;

    public BorrowController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/borrows")
    public ResponseEntity<List<Borrow>> findAllBorrows() {
        return ResponseEntity.ok(Arrays.asList(
           new Borrow(1L, 1L, 1L, LocalDate.of(2019, 1, 1),
                   LocalDate.of(2019, 2, 1), Boolean.TRUE),
            new Borrow(2L, 2L, 2L, LocalDate.of(2019, 3, 10),
                    LocalDate.of(2019, 4, 10), Boolean.FALSE)
        ));
    }

    @GetMapping("/first-10-books")
    public ResponseEntity<List<Book>> getFirst10Books() {
        return ResponseEntity.ok(bookService.getBooks().subList(0, 10));
    }

    @GetMapping("/books/mini")
    public ResponseEntity<List<Book>> getBooksMin() {
        return ResponseEntity.ok(bookService.getBooksMini());
    }
}
