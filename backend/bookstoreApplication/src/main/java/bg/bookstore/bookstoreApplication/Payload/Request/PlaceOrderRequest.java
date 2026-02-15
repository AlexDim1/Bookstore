package bg.bookstore.bookstoreApplication.Payload.Request;

public class PlaceOrderRequest {
    private Integer buyAmount;
    private String buyerName;
    private String buyerAddress;

    public PlaceOrderRequest(Integer buyAmount, String buyerName, String buyerAddress) {
        this.buyAmount = buyAmount;
        this.buyerName = buyerName;
        this.buyerAddress = buyerAddress;
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
}
