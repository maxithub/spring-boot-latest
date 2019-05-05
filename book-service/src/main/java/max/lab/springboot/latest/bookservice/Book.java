package max.lab.springboot.latest.bookservice;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private BigDecimal price;
    private LocalDate dateOfPublish;
}
