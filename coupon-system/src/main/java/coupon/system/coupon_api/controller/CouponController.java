package coupon.system.coupon_api.controller;

import coupon.system.coupon_api.service.coupon_service.CouponService;
import coupon.system.data.dto.CouponDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
@CrossOrigin("http://localhost:3000")
@RequiredArgsConstructor
public class CouponController {
    private final CouponService service;

    @GetMapping("/all")
    public ResponseEntity<Page<CouponDto>> getAllCoupons(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "sortProperty", defaultValue = "title") String sortProperty) {
        return ResponseEntity.ok(service.getCouponsByPage(pageNumber, size, sortProperty));
    }

    @GetMapping("/single")
    public ResponseEntity<CouponDto> getCoupon(@RequestParam(name = "coupon") UUID couponUuid) {
        return ResponseEntity.ok(service.getCoupon(couponUuid));
    }

    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories(){
        return ResponseEntity.ok(service.getAllCategories());
    }
}
