package coupon.system.coupon_api.controller.advice;
import coupon.system.coupon_api.controller.CouponController;
import coupon.system.coupon_api.ex.NoSuchCouponException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CouponController.class)
public class CouponAdvice {

    @ExceptionHandler({NoSuchCouponException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ProblemDetail handleNoSuchCoupon(NoSuchCouponException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

}
