package max.lab.springboot.latest.xthymeleaf;

import lombok.extern.slf4j.Slf4j;
import max.lab.springboot.latest.xthymeleaf.domain.Book;
import max.lab.springboot.latest.xthymeleaf.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class XThymeleafApplication {

	@Bean
	public CommandLineRunner init(BookRepository bookRepository) {
		return (args -> {
			bookRepository.saveAll(Arrays.asList(
					Book.builder().title("Hello").price(BigDecimal.valueOf(19.99D)).build(),
					Book.builder().title("World").price(BigDecimal.valueOf(12.99D)).build(),
					Book.builder().title("Hi").price(BigDecimal.valueOf(8.99D)).build(),
					Book.builder().title("Hello Kitty").price(BigDecimal.valueOf(23.99D)).build(),
					Book.builder().title("Java").price(BigDecimal.valueOf(15.99D)).build(),
					Book.builder().title(".Net").price(BigDecimal.valueOf(12.99D)).build(),
					Book.builder().title("Groovy").price(BigDecimal.valueOf(13.99D)).build(),
					Book.builder().title("Python").price(BigDecimal.valueOf(18.99D)).build(),
					Book.builder().title("C++").price(BigDecimal.valueOf(20.99D)).build(),
					Book.builder().title("C#").price(BigDecimal.valueOf(17.99D)).build(),
					Book.builder().title("VB").price(BigDecimal.valueOf(21.99D)).build(),
					Book.builder().title("Closure").price(BigDecimal.valueOf(32.99D)).build(),
					Book.builder().title("Scala").price(BigDecimal.valueOf(11.99D)).build(),
					Book.builder().title("Go").price(BigDecimal.valueOf(25.99D)).build(),
					Book.builder().title("Javascript").price(BigDecimal.valueOf(26.99D)).build(),
					Book.builder().title("Typescript").price(BigDecimal.valueOf(27.99D)).build(),
					Book.builder().title("SQL").price(BigDecimal.valueOf(21.99D)).build(),
					Book.builder().title("PHP").price(BigDecimal.valueOf(20.99D)).build()
			));
			log.info("All books are saved!");
		});
	}

	public static void main(String[] args) {
		SpringApplication.run(XThymeleafApplication.class, args);
	}

}
