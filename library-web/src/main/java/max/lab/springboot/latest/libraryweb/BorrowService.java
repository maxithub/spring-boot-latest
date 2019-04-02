package max.lab.springboot.latest.libraryweb;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@FeignClient(value = "borrow-service", fallback = BorrowService.BorrowServiceFallback.class )
public interface BorrowService {
    List<Borrow> findAllBorrows();

    @Component
    class BorrowServiceFallback implements BorrowService {

        @Override
        public List<Borrow> findAllBorrows() {
            return Collections.emptyList();
        }
    }
}