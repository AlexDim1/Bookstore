package bg.bookstore.bookstoreApplication.Payload.Response;

import bg.bookstore.bookstoreApplication.Entities.Product;

public class AdminProductResponse {
    private Long id;
    private String name;
    private String author;
    private String description;
    private String shortDescription;
    private Integer pageCount;
    private Double price;
    private Integer timesBought;

    public AdminProductResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.author = product.getAuthor();
        this.description = product.getDescription();
        this.shortDescription = product.getShortDescription();
        this.pageCount = product.getPageCount();
        this.price = product.getPrice();
        this.timesBought = product.getTimesBought();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
