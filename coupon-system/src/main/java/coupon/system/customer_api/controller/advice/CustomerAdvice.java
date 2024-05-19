package coupon.system.customer_api.controller.advice;
import coupon.system.coupon_api.ex.CouponAlreadyExistsException;
import coupon.system.coupon_api.ex.NoSuchCouponException;
import coupon.system.customer_api.controller.CustomerController;
import coupon.system.coupon_api.ex.UnavailableCouponException;
import coupon.system.session_api.ex.ExpiredSessionException;
import coupon.system.customer_api.ex.NoSuchCustomerException;
import coupon.system.session_api.ex.IllegalAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CustomerController.class)
public class CustomerAdvice {

    @ExceptionHandler({NoSuchCustomerException.class, NoSuchCouponException.class, UnavailableCouponException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNoSuchEntity(RuntimeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }
    @ExceptionHandler({ExpiredSessionException.class, IllegalAccessException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ProblemDetail handleSessionAccess(RuntimeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
    }
    @ExceptionHandler({CouponAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleOwnedCoupon(CouponAlreadyExistsException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }

}
