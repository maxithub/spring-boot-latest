package max.lab.springboot.latest.libraryweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class LibraryApi {
    private final UserService userService;
    private final BookService bookService;

    public LibraryApi(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping("/users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookService.findAllBooks();
    }
}
