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
            Book.builder().id(1L).isbn("1234-234-889").title("Spring Boot 2.1.3")
                    .price(BigDecimal.valueOf(39.99)).dateOfPublish(LocalDate.of(2018, 10, 9)).build(),
            Book.builder().id(2L).isbn("8923-234-234").title("Spring Cloud")
                    .price(BigDecimal.valueOf(19.99)).dateOfPublish(LocalDate.of(2017, 1, 3)).build()
        ));
    }

}
