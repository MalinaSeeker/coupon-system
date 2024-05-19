package coupon.system.coupon_api.ex;

public class UnavailableCouponException extends RuntimeException {
    public UnavailableCouponException(String messages) {
        super(messages);
    }
}
