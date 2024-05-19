package coupon.system.customer_api.service;

import coupon.system.coupon_api.service.customer_service.CustomerCouponService;
import coupon.system.coupon_api.service.customer_service.CustomerCouponServiceImpl;
import coupon.system.customer_api.ex.NoSuchCustomerException;
import coupon.system.data.dto.CouponDto;
import coupon.system.repository.CustomerRepository;
import coupon.system.data.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final CustomerCouponService couponService;
    @Override
    public List<CouponDto> getAllPurchasedCoupons(UUID customerUuid) {
        return couponService.getAllPurchasedCoupons(getCustomer(customerUuid));
    }

    @Override
    public List<CouponDto> getAllNotPurchasedCoupons(UUID customerUuid) {
        return couponService.getAllNotPurchasedCoupons(getCustomer(customerUuid));
    }

    @Override
    public List<CouponDto> getAllExpiredInAWeek(UUID customerUuid) {
        List<CouponDto> couponDtos = getAllPurchasedCoupons(customerUuid);
        return couponService.getExpiredInTimeFrame(couponDtos, CustomerCouponServiceImpl.ExpirationTimeFrame.WEEK);
    }

    @Override
    public void purchaseACoupon(UUID customerUuid, UUID couponUuid) {
        couponService.purchaseACoupon(getCustomer(customerUuid), couponUuid);
    }

    @Override
    public List<CouponDto> getCouponsByCategory(UUID customerUuid, String category) {
        return couponService.getByCategory(getCustomer(customerUuid), category);
    }

    private Customer getCustomer(UUID uuid) {
        return repository.findByUuid(uuid)
                .orElseThrow(() -> new NoSuchCustomerException("Customer not Found"));
    }
}
