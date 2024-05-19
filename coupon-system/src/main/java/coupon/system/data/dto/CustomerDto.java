package coupon.system.data.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CustomerDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
    private List<CouponDto> coupons;
}
