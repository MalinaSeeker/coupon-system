package coupon.system.repository;

import coupon.system.data.entity.Coupon;
import coupon.system.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    Optional<Coupon> findByUuid(UUID uuid);
    @Query("SELECT DISTINCT c.category FROM Coupon c")
    List<String> findDistinctCategories();
    Optional<Coupon> findByCompany_IdAndUuid(Long companyId, UUID uuid);
    List<Coupon> findAllByCompany_Id(Long companyId);
    Optional<Coupon> findByCustomers_IdAndUuid(Long customerId, UUID uuid);
    List<Coupon> findAllByCustomers_Id(Long customerId);
    List<Coupon> findAllByCustomers_IdAndCategory(Long customerId, String category);
    List<Coupon> findAllByCustomersNotContaining(Customer customer);
    void deleteByEndDateBefore(Date endDate);
    @Query("SELECT c FROM Coupon c WHERE c.company.id = :companyId AND c.description = :description")
    Optional<Coupon> findCouponWithMatchingDetails(@Param("companyId") Long companyId,
                                                   @Param("description") String description);
}
