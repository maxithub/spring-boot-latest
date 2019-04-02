package max.lab.springboot.latest.libraryweb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

@FeignClient(value = "user-service", fallback = UserService.UserServiceFallback.class)
public interface UserService {
    @GetMapping("/users")
    List<User> findAllUsers();

    @Component // Must be a Spring component
    class UserServiceFallback implements UserService {
        @Override
        public List<User> findAllUsers() {
            return Collections.EMPTY_LIST;
        }
    }
}