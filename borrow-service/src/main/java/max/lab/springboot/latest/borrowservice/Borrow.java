package max.lab.springboot.latest.borrowservice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Borrow {
    private Long id;
    private Long bookId;
    private Long userId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private Boolean returned;
}
