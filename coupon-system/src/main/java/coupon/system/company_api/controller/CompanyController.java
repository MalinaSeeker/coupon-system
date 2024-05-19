package coupon.system.company_api.controller;

import coupon.system.company_api.service.CompanyService;
import coupon.system.session_api.service.TokenManager;
import coupon.system.data.dto.CouponDto;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService service;
    private final TokenManager manager;

    @PostMapping("/create")
    public ResponseEntity<CouponDto> addCoupon(@RequestHeader("Authorization") String token,
                                               @RequestBody CouponDto couponDto,
                                               ServletRequest request) {
        URI location = URI.create(((HttpServletRequest) request).getRequestURI());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);

        return ResponseEntity.created(location).headers(headers)
                .body(service.addCoupon(manager.getClientsUuid(token), couponDto));
    }

    @PatchMapping("/update-amount/{couponUuid}")
    public ResponseEntity<CouponDto> updateCoupon(@RequestHeader("Authorization") String token,
                                                  @PathVariable UUID couponUuid,
                                                  @RequestParam(name = "amount") int amount) {
        return ResponseEntity.ok(service.updateCouponAmount(manager.getClientsUuid(token), couponUuid, amount));
    }

    @DeleteMapping("/delete/{couponUuid}")
    public ResponseEntity<CouponDto> deleteCoupon(@RequestHeader("Authorization") String token,
                                                  @PathVariable UUID couponUuid) {
        service.deleteCoupon(manager.getClientsUuid(token), couponUuid);

        return ResponseEntity.noContent().headers(new HttpHeaders()).build();
    }

    @GetMapping("/coupons")
    public ResponseEntity<List<CouponDto>> getAllCompanyCoupons(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(service.getAllCoupons(manager.getClientsUuid(token)));
    }

}
