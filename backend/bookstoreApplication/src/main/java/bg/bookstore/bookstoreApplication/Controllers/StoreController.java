package bg.bookstore.bookstoreApplication.Controllers;

import bg.bookstore.bookstoreApplication.Entities.Order;
import bg.bookstore.bookstoreApplication.Entities.Product;
import bg.bookstore.bookstoreApplication.Entities.Review;
import bg.bookstore.bookstoreApplication.Payload.Request.AddReviewRequest;
import bg.bookstore.bookstoreApplication.Payload.Request.PlaceOrderRequest;
import bg.bookstore.bookstoreApplication.Payload.Response.MessageResponse;
import bg.bookstore.bookstoreApplication.Payload.Response.ProductResponse;
import bg.bookstore.bookstoreApplication.Payload.Response.StoreResponse;
import bg.bookstore.bookstoreApplication.Repositories.OrderRepository;
import bg.bookstore.bookstoreApplication.Repositories.ProductRepository;
import bg.bookstore.bookstoreApplication.Repositories.ReviewRepository;
import bg.bookstore.bookstoreApplication.Utilities.Validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/store")
public class StoreController {
    private final ProductRepository productRepo;
    private final ReviewRepository reviewRepo;
    private final OrderRepository orderRepo;

    public StoreController(ProductRepository productRepo, ReviewRepository reviewRepo, OrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
        this.orderRepo = orderRepo;
    }

    @PostMapping("/products/{id}/buy")
    public ResponseEntity<?> buyProduct(@PathVariable Long id, @RequestBody PlaceOrderRequest request) {
        if(!Validation.validateBuyerInfo(request))
            return new ResponseEntity<>(new MessageResponse("Невалидни данни.", "error"), HttpStatus.BAD_REQUEST);

        Product product = productRepo.findProductById(id);

        Order newOrder = new Order(product,
                request.getBuyAmount(),
                request.getBuyerName(),
                request.getBuyerAddress());

        product.setTimesBought(product.getTimesBought() + request.getBuyAmount());
        productRepo.save(product);

        try {
            orderRepo.save(newOrder);
        }
        catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Поръчката беше неуспешна.", "error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(new MessageResponse("Успешно поръчахте " + newOrder.getBuyAmount() + "бр. " + product.getName() + "! Поръчката ще бъде при вас в срок до 3 работни дни.", "success"));
    }

    @PostMapping("/products/{id}/reviews/add")
    public ResponseEntity<?> addReview(@PathVariable Long id, @RequestBody AddReviewRequest request) {
        if (!Validation.validateReview(request))
            return new ResponseEntity<>(new MessageResponse("Моля, въведете текст.", "error"), HttpStatus.BAD_REQUEST);

        Product product = productRepo.findProductById(id);

        reviewRepo.save(new Review(request.getContent(), product));
        return ResponseEntity.ok("Успешно добавено ревю.");
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<?> getProduct(@PathVariable Long id) {
        Product product = productRepo.findProductById(id);

        if(product == null)
            return ResponseEntity.ok("Няма такъв продукт!");

        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts() {
        List<Product> products = productRepo.findAll();

        List<StoreResponse> productsResponse = new ArrayList<>();

        for (Product product : products)
            productsResponse.add(new StoreResponse(product));

        List<List<StoreResponse>> productRows = productRows(productsResponse, 2);
        return ResponseEntity.ok(productRows);
    }

    private List<List<StoreResponse>> productRows(List<StoreResponse> products, Integer perRow) {
        List<List<StoreResponse>> productRows = new ArrayList<>();

        Integer rowCount = products.size() / perRow + 1;

        List<StoreResponse> row;
        for (int i = 1; i <= rowCount; i++) {
            int endIdx = i * perRow;

            if(endIdx > products.size() - 1)
                endIdx = products.size();
            row = products.subList((i - 1) * perRow, endIdx);

            productRows.add(row);
        }

        return productRows;
    }
}
