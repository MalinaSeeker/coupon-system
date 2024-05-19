package coupon.system.coupon_api.service.coupon_service;

import coupon.system.coupon_api.ex.NoSuchCouponException;
import coupon.system.data.dto.CouponDto;
import coupon.system.data.entity.Coupon;
import coupon.system.mapper.CouponMapper;
import coupon.system.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
    private final CouponRepository couponRepository;
    private final CouponMapper couponMapper;
    @Override
    public CouponDto getCoupon(UUID couponUuid) {
        Coupon coupon = couponRepository.findByUuid(couponUuid)
                .orElseThrow(() -> new NoSuchCouponException("Coupon not found"));
        return couponMapper.toDto(coupon);
    }

    @Override
    public List<String> getAllCategories() {
        return couponRepository.findDistinctCategories();
    }

    @Override
    public Page<CouponDto> getCouponsByPage(int pageNumber, int size, String sortProperty) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size, Sort.by(sortProperty));
        return couponRepository.findAll(pageRequest).map(couponMapper::toCustomerEntity);
    }
}
