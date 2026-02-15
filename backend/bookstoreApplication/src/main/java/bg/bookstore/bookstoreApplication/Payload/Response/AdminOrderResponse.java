package bg.bookstore.bookstoreApplication.Payload.Response;

import bg.bookstore.bookstoreApplication.Entities.Order;
import java.util.Date;

public class AdminOrderResponse {
    private Long id;
    private Date date;
    private String productName;
    private Integer buyAmount;
    private String buyerName;
    private String buyerAddress;
    private Double totalPrice;

    public AdminOrderResponse(Order order) {
        this.id = order.getId();
        this.date = order.getDate();
        this.productName = order.getProduct().getName();
        this.buyAmount = order.getBuyAmount();
        this.buyerName = order.getBuyerName();
        this.buyerAddress = order.getBuyerAddress();
        this.totalPrice = order.getTotalPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(Integer buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
