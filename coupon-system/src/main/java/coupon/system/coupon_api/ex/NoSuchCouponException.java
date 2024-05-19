package coupon.system.coupon_api.ex;

public class NoSuchCouponException extends RuntimeException {
    public NoSuchCouponException(String message) {
        super(message);
    }
}
