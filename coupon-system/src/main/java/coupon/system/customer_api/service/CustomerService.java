package coupon.system.customer_api.service;

import coupon.system.data.dto.CouponDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    List<CouponDto> getAllPurchasedCoupons(UUID uuid);
    List<CouponDto> getAllNotPurchasedCoupons(UUID uuid);
    List<CouponDto> getAllExpiredInAWeek(UUID uuid);
    void purchaseACoupon(UUID uuid, UUID couponUuid);
    List<CouponDto> getCouponsByCategory(UUID customerUuid, String category);
}
