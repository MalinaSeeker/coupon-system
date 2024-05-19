package coupon.system.data.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CompanyDto {
    private UUID uuid;
    private String name;
    private String email;
    private String password;
    private List<CouponDto> coupons;
}
