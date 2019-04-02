package max.lab.springboot.latest.libraryweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by max on 19-4-2.
 */
@Controller
public class RootController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

}
