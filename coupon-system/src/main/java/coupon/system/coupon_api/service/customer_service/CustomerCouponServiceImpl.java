package coupon.system.coupon_api.service.customer_service;

import coupon.system.coupon_api.ex.CouponAlreadyExistsException;
import coupon.system.coupon_api.ex.NoSuchCouponException;
import coupon.system.coupon_api.ex.UnavailableCouponException;
import coupon.system.data.dto.CouponDto;
import coupon.system.data.entity.Coupon;
import coupon.system.data.entity.Customer;
import coupon.system.mapper.CouponMapper;
import coupon.system.repository.CouponRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerCouponServiceImpl implements CustomerCouponService {
    private final CouponRepository repository;
    private final CouponMapper couponMapper;

    @AllArgsConstructor
    public enum ExpirationTimeFrame {
        WEEK(7);
        final int numberOfDays;
    }

    @Override
    public List<CouponDto> getAllPurchasedCoupons(Customer customer) {
        List<Coupon> coupons = repository.findAllByCustomers_Id(customer.getId());
        return coupons.stream().map(couponMapper::toCustomerEntity).toList();
    }

    @Override
    public List<CouponDto> getAllNotPurchasedCoupons(Customer customer) {
        List<Coupon> coupons = repository.findAllByCustomersNotContaining(customer);
        return coupons.stream().map(couponMapper::toCustomerEntity).toList();
    }

    @Override
    public List<CouponDto> getExpiredInTimeFrame(List<CouponDto> couponDtos, CustomerCouponServiceImpl.ExpirationTimeFrame frame) {
        LocalDate today = LocalDate.now();
        int numberOfDays = frame.numberOfDays;
        return couponDtos.stream()
                .filter(couponDto -> couponDto.getEndDate().toLocalDate().isAfter(today))
                .filter(couponDto -> ChronoUnit.DAYS.between(today, couponDto.getEndDate().toLocalDate()) <= numberOfDays)
                .toList();
    }

    @Override
    public List<CouponDto> getByCategory(Customer customer, String category) {
        List<Coupon> coupons = repository.findAllByCustomers_IdAndCategory(customer.getId(), category);
        return coupons.stream().map(couponMapper::toCustomerEntity).toList();
    }

    @Override
    @Transactional
    public void purchaseACoupon(Customer customer, UUID couponUuid) {
        Coupon coupon = repository.findByUuid(couponUuid)
                .orElseThrow(() -> new NoSuchCouponException("Coupon not found"));

        if (coupon.getAmount() == 0 || coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
            throw new UnavailableCouponException("Coupon is no longer available");
        }
        Optional<Coupon> optCoupon = repository.findByCustomers_IdAndUuid(customer.getId(), couponUuid);
        if (optCoupon.isPresent()) {
            throw new CouponAlreadyExistsException("This coupon has already been purchased");
        }

        coupon.getCustomers().add(customer);
        coupon.setAmount(coupon.getAmount() -1);
        customer.getCoupons().add(coupon);
    }
}
