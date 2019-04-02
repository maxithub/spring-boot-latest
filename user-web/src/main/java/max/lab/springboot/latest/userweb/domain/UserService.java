package max.lab.springboot.latest.userweb.domain;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;

/**
 * Created by max on 19-4-2.
 */
@FeignClient(value = "user-service", fallback = UserService.UserServiceFallback.class)
public interface UserService {
    @GetMapping("/users")
    List<User> findAllUsers();

    class UserServiceFallback implements UserService {

        @Override
        public List<User> findAllUsers() {
            return Collections.EMPTY_LIST;
        }
    }
}
