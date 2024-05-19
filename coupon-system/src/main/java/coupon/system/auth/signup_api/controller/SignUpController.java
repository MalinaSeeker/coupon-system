package coupon.system.auth.signup_api.controller;

import coupon.system.auth.signup_api.service.SignUpService;
import coupon.system.data.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class SignUpController {
    private final SignUpService service;

    @PostMapping("/customer")
    public ResponseEntity<CustomerDto> addNewCustomer(@RequestBody CustomerDto customerDto,
                                                      @RequestParam(name = "password") String password){
        return ResponseEntity.ok(service.addNewCustomer(customerDto, password));
    }
}
