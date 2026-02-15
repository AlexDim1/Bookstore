package bg.bookstore.bookstoreApplication.Repositories;

import bg.bookstore.bookstoreApplication.Entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
