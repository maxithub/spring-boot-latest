package max.lab.springboot.latest.borrowservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("book-service")
public interface BookService {
    @GetMapping("/books/mini")
    List<Book> getBooksMini();

    @GetMapping("/books")
    List<Book> getBooks();
}
