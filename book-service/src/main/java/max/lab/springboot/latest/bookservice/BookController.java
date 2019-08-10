package max.lab.springboot.latest.bookservice;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class BookController {
    @GetMapping("/books/mini")
    public ResponseEntity<List<Book>> findAllBooksMini() {
        return ResponseEntity.ok(Arrays.asList(
                Book.builder().id(1L).isbn("1234-234-889").title("Spring Boot 2.1.3")
                        .price(BigDecimal.valueOf(39.99)).dateOfPublish(LocalDate.of(2018, 10, 9)).build(),
                Book.builder().id(2L).isbn("8923-234-234").title("Spring Cloud")
                        .price(BigDecimal.valueOf(19.99)).dateOfPublish(LocalDate.of(2017, 1, 3)).build()
        ));
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> findAllBooks() {
        return ResponseEntity.ok(IntStream.rangeClosed(1, 10000)
                .mapToObj(i -> Book.builder()
                        .id((long) i)
                        .isbn(String.format("%d-%d-%s", RandomUtils.nextInt(1000), RandomUtils.nextInt(1000), RandomUtils.nextInt(1000)))
                        .title(RandomStringUtils.randomAscii(100))
                        .price(BigDecimal.valueOf(RandomUtils.nextDouble()))
                        .dateOfPublish(LocalDate.now().minus(RandomUtils.nextInt(365 * 10), ChronoUnit.DAYS))
                        .build())
                .collect(Collectors.toList()));
    }

}
