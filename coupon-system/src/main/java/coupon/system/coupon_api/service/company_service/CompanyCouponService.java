package coupon.system.coupon_api.service.company_service;

import coupon.system.data.dto.CouponDto;
import coupon.system.data.entity.Company;

import java.util.List;
import java.util.UUID;

public interface CompanyCouponService {
    CouponDto addNewCoupon(Company company, CouponDto couponDto);
    CouponDto updateCouponAmount(Company company, UUID couponUuid, int amount);
    void deleteCoupon(Company company, UUID couponUuid);
    List<CouponDto> getAllCompanyCoupons(Company company);
}
