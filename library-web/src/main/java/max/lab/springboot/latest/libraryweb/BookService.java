package max.lab.springboot.latest.libraryweb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@FeignClient(value = "book-service", fallback = BookService.BookServiceFallBack.class)
public interface BookService {
    @GetMapping("/books")
    List<Book> findAllBooks();

    @Component
    class BookServiceFallBack implements BookService {
        @Override
        public List<Book> findAllBooks() {
            return Collections.emptyList();
        }
    }
}