package bg.bookstore.bookstoreApplication.Payload.Response;

import bg.bookstore.bookstoreApplication.Entities.Product;

public class StoreResponse {
    private Long id;
    private String name;
    private String author;
    private String shortDescription;
    private Double price;

    public StoreResponse(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.author = product.getAuthor();
        this.shortDescription = product.getShortDescription();
        this.price = product.getPrice();
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
