package bg.bookstore.bookstoreApplication.Repositories;

import bg.bookstore.bookstoreApplication.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductByName(String name);
    Product findProductById(Long id);
}
