package bg.bookstore.bookstoreApplication.Entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String author;
    @Column(columnDefinition = "TEXT", length = 1000)
    private String description;
    private String shortDescription;
    private Integer pageCount;
    private Double price;
    private Integer timesBought;

    @JsonManagedReference
    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "product", orphanRemoval = true)
    private List<Order> orders;

    public Product() {
    }

    public Product(String name, String author, String description, String shortDescription, Integer pageCount, Double price) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.shortDescription = shortDescription;
        this.pageCount = pageCount;
        this.price = price;
        this.timesBought = 0;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getTimesBought() {
        return timesBought;
    }

    public void setTimesBought(Integer timesBought) {
        this.timesBought = timesBought;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
