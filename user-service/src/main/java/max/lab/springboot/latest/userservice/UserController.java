package max.lab.springboot.latest.userservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public ResponseEntity<List<User>> findAllUsers() {
        return ResponseEntity.ok(Arrays.asList(
                new User(1L, "John", 23, "M"),
                new User(1L, "Mary", 34, "F")
        ));
    }
}
