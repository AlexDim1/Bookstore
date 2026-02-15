package bg.bookstore.bookstoreApplication.Payload.Request;

public class AddReviewRequest {
    private String content;

    public AddReviewRequest() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
