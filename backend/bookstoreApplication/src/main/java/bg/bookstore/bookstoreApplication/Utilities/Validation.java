package bg.bookstore.bookstoreApplication.Utilities;

import bg.bookstore.bookstoreApplication.Payload.Request.AddReviewRequest;
import bg.bookstore.bookstoreApplication.Payload.Request.PlaceOrderRequest;

public class Validation {
    public static boolean validateBuyerInfo(PlaceOrderRequest request) {
        return !request.getBuyerAddress().isEmpty() && !request.getBuyerName().isEmpty();
    }

    public static boolean validateReview(AddReviewRequest request) {
        return !request.getContent().isEmpty();
    }
}
