package coupon.system.coupon_api.service.company_service;

import coupon.system.coupon_api.ex.CouponAlreadyExistsException;
import coupon.system.coupon_api.ex.NoSuchCouponException;
import coupon.system.data.dto.CouponDto;
import coupon.system.data.entity.Company;
import coupon.system.data.entity.Coupon;
import coupon.system.mapper.CouponMapper;
import coupon.system.repository.CouponRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class CompanyCouponServiceImpl implements CompanyCouponService {
    private final CouponRepository repository;
    private final CouponMapper couponMapper;

    @Transactional
    @Scheduled(cron = "0 */5 * * * *")
    @Async
    public void deleteExpiredCoupons(){
        repository.deleteByEndDateBefore(Date.valueOf(LocalDate.now()));
    }

    @Override
    @Transactional
    public CouponDto addNewCoupon(Company company, CouponDto couponDto) {
        if (couponDto.getPrice() < 0) {
            throw new IllegalArgumentException("Invalid coupon price");
        }
        checkCompanyCouponExistence(company, couponDto);

        Coupon coupon = couponMapper.toEntity(couponDto);
        coupon.setUuid(UUID.randomUUID());
        coupon.setCompany(company);
        company.getCoupons().add(coupon);

        return couponMapper.toDto(repository.save(coupon));
    }

    @Override
    @Transactional
    public CouponDto updateCouponAmount(Company company, UUID couponUuid, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        Coupon coupon = repository.findByCompany_IdAndUuid(company.getId(), couponUuid)
                .orElseThrow(() -> new NoSuchCouponException("Coupon not found"));

        coupon.setAmount(amount);
        return couponMapper.toDto(coupon);
    }

    @Override
    @Transactional
    public void deleteCoupon(Company company, UUID couponUuid) {
        Optional<Coupon> optCoupon = repository.findByCompany_IdAndUuid(company.getId(), couponUuid);
        if (optCoupon.isEmpty()) {
            throw new NoSuchCouponException("Coupon not found");
        }
        repository.delete(optCoupon.get());
    }

    @Override
    public List<CouponDto> getAllCompanyCoupons(Company company) {
        List<Coupon> coupons = repository.findAllByCompany_Id(company.getId());
        return coupons.stream().map(couponMapper::toDto).toList();
    }
    private void checkCompanyCouponExistence(Company company, CouponDto couponDto) {
        Optional<Coupon> optCoupon = repository.findCouponWithMatchingDetails(company.getId(), couponDto.getDescription());

        if (optCoupon.isPresent()) {
            throw new CouponAlreadyExistsException(
                    "Coupon with this description already exists. you may update the amount in the appropriate endPoint");
        }
    }
}
