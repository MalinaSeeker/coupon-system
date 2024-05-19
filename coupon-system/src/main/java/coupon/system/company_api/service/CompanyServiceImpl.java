package coupon.system.company_api.service;

import coupon.system.company_api.ex.NoSuchCompanyException;
import coupon.system.coupon_api.service.company_service.CompanyCouponService;
import coupon.system.data.dto.CouponDto;
import coupon.system.data.entity.Company;
import coupon.system.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyCouponService couponService;

    @Override
    public CouponDto addCoupon(UUID companyUuid, CouponDto couponDto) {
        return couponService.addNewCoupon(getCompany(companyUuid), couponDto);
    }

    @Override
    public CouponDto updateCouponAmount(UUID companyUuid, UUID couponUuid, int amount) {
        return couponService.updateCouponAmount(getCompany(companyUuid), couponUuid, amount);
    }

    @Override
    public List<CouponDto> getAllCoupons(UUID companyUuid) {
        return couponService.getAllCompanyCoupons(getCompany(companyUuid));
    }

    @Override
    public void deleteCoupon(UUID companyUuid, UUID couponUuid) {
        couponService.deleteCoupon(getCompany(companyUuid), couponUuid);
    }

    private Company getCompany(UUID uuid) {
        return companyRepository.findByUuid(uuid).orElseThrow(() -> new NoSuchCompanyException("Company not found"));
    }
}
