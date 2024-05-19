package coupon.system.company_api.service;

import coupon.system.data.dto.CouponDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyService {
    CouponDto addCoupon(UUID uuid, CouponDto couponDto);
    CouponDto updateCouponAmount(UUID companyUuid, UUID couponUuid, int amount);
    List<CouponDto> getAllCoupons(UUID uuid);

    void deleteCoupon(UUID companyUuid, UUID couponUuid);

}
