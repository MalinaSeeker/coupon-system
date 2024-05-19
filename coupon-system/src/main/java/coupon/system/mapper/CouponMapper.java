package coupon.system.mapper;

import coupon.system.data.dto.CouponDto;
import coupon.system.data.entity.Coupon;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    CouponDto toDto(Coupon coupon);
    Coupon toEntity(CouponDto couponDto);

    default CouponDto toCustomerEntity(Coupon coupon) {
        coupon.setAmount(null);
        return toDto(coupon);
    }
}
