package max.lab.springboot.latest.xthymeleaf.controller;

import max.lab.springboot.latest.xthymeleaf.domain.Book;
import max.lab.springboot.latest.xthymeleaf.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static java.lang.Boolean.FALSE;
import static java.util.Optional.ofNullable;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;
import static org.thymeleaf.util.StringUtils.isEmptyOrWhitespace;

@Controller
public class BookController {
    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(required = false) String name,
                              @RequestParam(required = false) Integer page,
                              @RequestParam(required = false) Integer size,
                              @RequestParam(required = false) String column,
                              @RequestParam(required = false) String order,
                              @RequestParam(required = false) Boolean reset) {
        if (ofNullable(reset).orElse(FALSE)) {
            page = null;
            size = null;
            column = null;
            order = null;
        }
        Pageable pageable = PageRequest.of(
                ofNullable(page).orElse(0),
                ofNullable(size).orElse(5),
                Sort.by(ofNullable(order).map(str -> "desc".equalsIgnoreCase(str))
                        .map(b -> b ? DESC : ASC).orElse(ASC),
                        (isEmptyOrWhitespace(column) ? "id" : column.trim())));
        Page<Book> bookPage = bookRepository.findByTitleLike(
                (isEmptyOrWhitespace(name) ? "%" : "%" + name.trim() + "%"),
                pageable);
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("bookPage", bookPage);
        return modelAndView;
    }

}
