package coupon.system.company_api.ex;

public class NoSuchCompanyException extends RuntimeException {
    public NoSuchCompanyException(String message) {
        super(message);
    }
}
