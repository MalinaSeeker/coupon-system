package coupon.system.coupon_api.service.customer_service;

import coupon.system.data.dto.CouponDto;
import coupon.system.data.entity.Customer;

import java.util.List;
import java.util.UUID;

public interface CustomerCouponService {
    void purchaseACoupon(Customer customer, UUID couponUuid);
    List<CouponDto> getAllPurchasedCoupons(Customer customer);
    List<CouponDto> getAllNotPurchasedCoupons(Customer customer);
    List<CouponDto> getExpiredInTimeFrame(List<CouponDto> couponDtos, CustomerCouponServiceImpl.ExpirationTimeFrame expiration);
    List<CouponDto> getByCategory(Customer customer, String category);
}

