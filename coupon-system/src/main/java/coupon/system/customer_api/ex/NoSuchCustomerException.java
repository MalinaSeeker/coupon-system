package coupon.system.customer_api.ex;

public class NoSuchCustomerException extends RuntimeException {
    public NoSuchCustomerException(String message) {
        super(message);
    }
}
