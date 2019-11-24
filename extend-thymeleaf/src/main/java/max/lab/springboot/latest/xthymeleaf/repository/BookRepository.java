package max.lab.springboot.latest.xthymeleaf.repository;

import max.lab.springboot.latest.xthymeleaf.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Page<Book> findByTitleLike(String name, Pageable pageable);
}
