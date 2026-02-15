package bg.bookstore.bookstoreApplication.Payload.Response;

import bg.bookstore.bookstoreApplication.Entities.Product;

public class ProductTableResponse {
    private Long id;
    private String name;
    private String author;
    private Integer pageCount;
    private Double price;
    private Integer timesBought;

    public ProductTableResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.author = product.getAuthor();
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
