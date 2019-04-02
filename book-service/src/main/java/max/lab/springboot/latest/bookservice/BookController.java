package max.lab.springboot.latest.bookservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class BookController {
    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(Arrays.asList(
            new Book(1L, "1234-234-889", "Spring Boot 2.1.3", BigDecimal.valueOf(39.99), LocalDate.of(2018, 10, 9)),
            new Book(2L, "8923-234-234", "Spring Cloud", BigDecimal.valueOf(19.99), LocalDate.of(2017, 1, 3))
        ));
    }

}
