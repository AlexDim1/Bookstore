package bg.bookstore.bookstoreApplication.Controllers;

import bg.bookstore.bookstoreApplication.Entities.Order;
import bg.bookstore.bookstoreApplication.Entities.Product;
import bg.bookstore.bookstoreApplication.Payload.Request.AddProductRequest;
import bg.bookstore.bookstoreApplication.Payload.Response.*;
import bg.bookstore.bookstoreApplication.Repositories.OrderRepository;
import bg.bookstore.bookstoreApplication.Repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final ProductRepository productRepo;
    private final OrderRepository orderRepo;

    public AdminController(ProductRepository productRepo, OrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    @GetMapping("/data")
    public GetDataResponse getData() {
        Auth
        List<Product> products = productRepo.findAll();
        List<ProductTableResponse> productsResponse = new ArrayList<>();
        for (Product product: products)
            productsResponse.add(new ProductTableResponse(product));

        List<Order> orders = orderRepo.findAll();
        List<AdminOrderResponse> ordersResponse = new ArrayList<>();
        for (Order order: orders)
            ordersResponse.add(new AdminOrderResponse(order));

        return new GetDataResponse(productsResponse, ordersResponse);
    }

    @GetMapping("/{id}")
    public AdminProductResponse getProduct(@PathVariable Long id) {
        return new AdminProductResponse(productRepo.findProductById(id));
    }

    @PostMapping("/products/add")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest request) {
        Product product = productRepo.findProductByName(request.getName());

        if (product != null)
            return new ResponseEntity<>(new MessageResponse("Продуктът вече е добавен.", "error"), HttpStatus.BAD_REQUEST);

        product = new Product(request.getName(),
                request.getAuthor(),
                request.getDescription(),
                request.getShortDescription(),
                request.getPageCount(),
                request.getPrice());

        productRepo.save(product);
        return ResponseEntity.ok("Продуктът " + product.getName() + " с id: " + product.getId() + " e добавен успешно.");
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody AddProductRequest request) {
        Product product = productRepo.findProductById(id);

        product.setName(request.getName());
        product.setAuthor(request.getAuthor());
        product.setShortDescription(request.getShortDescription());
        product.setDescription(request.getDescription());
        product.setPageCount(request.getPageCount());
        product.setPrice(request.getPrice());

        productRepo.save(product);

        return ResponseEntity.ok("Запазено.");
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productRepo.deleteById(id);

        return ResponseEntity.ok("Продуктът беше изтрит успешно.");
    }
}
