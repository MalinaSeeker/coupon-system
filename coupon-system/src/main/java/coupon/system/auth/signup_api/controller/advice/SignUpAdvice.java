package coupon.system.auth.signup_api.controller.advice;

import coupon.system.auth.signup_api.controller.SignUpController;
import coupon.system.auth.signup_api.ex.SuchEmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = SignUpController.class)
public class SignUpAdvice {

    @ExceptionHandler({SuchEmailAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ProblemDetail handleExistingCustomer(SuchEmailAlreadyExistsException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
    }
}
