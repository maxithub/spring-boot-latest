package max.lab.springboot.latest.libraryweb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private BigDecimal price;
    private LocalDate dateOfPublish;
}
