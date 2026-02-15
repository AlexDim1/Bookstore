package bg.bookstore.bookstoreApplication.Payload.Response;

import java.util.List;

public class GetDataResponse {
    private List<ProductTableResponse> products;
    private List<AdminOrderResponse> orders;

    public GetDataResponse(List<ProductTableResponse> products, List<AdminOrderResponse> orders) {
        this.products = products;
        this.orders = orders;
    }

    public List<ProductTableResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductTableResponse> products) {
        this.products = products;
    }

    public List<AdminOrderResponse> getOrders() {
        return orders;
    }

    public void setOrders(List<AdminOrderResponse> orders) {
        this.orders = orders;
    }
}
