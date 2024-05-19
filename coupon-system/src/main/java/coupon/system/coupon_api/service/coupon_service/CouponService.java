package coupon.system.coupon_api.service.coupon_service;

import coupon.system.data.dto.CouponDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CouponService {
    Page<CouponDto> getCouponsByPage(int pageNumber, int size, String sortProperty);
    CouponDto getCoupon(UUID couponUuid);
    List<String> getAllCategories();
}
