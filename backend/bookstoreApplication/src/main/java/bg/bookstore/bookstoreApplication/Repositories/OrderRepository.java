package bg.bookstore.bookstoreApplication.Repositories;

import bg.bookstore.bookstoreApplication.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
