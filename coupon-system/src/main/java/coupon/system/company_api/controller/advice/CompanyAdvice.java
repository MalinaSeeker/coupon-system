package coupon.system.company_api.controller.advice;

import coupon.system.company_api.controller.CompanyController;
import coupon.system.company_api.ex.NoSuchCompanyException;
import coupon.system.coupon_api.ex.CouponAlreadyExistsException;
import coupon.system.coupon_api.ex.NoSuchCouponException;
import coupon.system.session_api.ex.ExpiredSessionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = CompanyController.class)
public class CompanyAdvice {

    @ExceptionHandler({NoSuchCompanyException.class, NoSuchCouponException.class})
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
    @ExceptionHandler({IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ProblemDetail handleInvalidArgument(IllegalArgumentException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
