package coupon.system.data.dto;
import lombok.*;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class CouponDto {
    private UUID uuid;
    private String category;
    private String title;
    private String description;
    private Double price;
    private Date startDate;
    private Date endDate;
    private Integer amount;
    private String imageURL;

}
