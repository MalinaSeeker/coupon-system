package coupon.system.customer_api.controller;

import coupon.system.customer_api.service.CustomerService;
import coupon.system.session_api.service.TokenManager;
import coupon.system.data.dto.CouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;
    private final TokenManager session;

    @GetMapping("/purchased-coupons")
    public ResponseEntity<List<CouponDto>> getAllPurchasedCoupons(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.getAllPurchasedCoupons(session.getClientsUuid(token)));
    }

    @GetMapping("/not-purchased-coupons")
    public ResponseEntity<List<CouponDto>> getNotPurchasedCoupons(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.getAllNotPurchasedCoupons(session.getClientsUuid(token)));
    }


    @GetMapping("/in-a-week")
    public ResponseEntity<List<CouponDto>> getExpiredInAWeekCoupons(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.getAllExpiredInAWeek(session.getClientsUuid(token)));
    }

    @GetMapping("/by-category")
    public ResponseEntity<List<CouponDto>> getCouponsByCategory(@RequestHeader("Authorization") String token,
                                                                @RequestParam(name = "category") String category) {
        return ResponseEntity.ok(service.getCouponsByCategory(session.getClientsUuid(token), category));
    }

    @PostMapping("/purchase")
    public ResponseEntity<HttpStatus> purchaseACoupon(@RequestHeader("Authorization") String token,
                                                      @RequestParam(name = "coupon") UUID couponUuid) {
        service.purchaseACoupon(session.getClientsUuid(token), couponUuid);

        return ResponseEntity.noContent().headers(new HttpHeaders()).build();
    }

}
