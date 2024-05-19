package coupon.system.repository;

import coupon.system.data.entity.Company;
import coupon.system.data.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByEmailAndPassword(String email, String password);
    Optional<Company> findByUuid(UUID uuid);

}
