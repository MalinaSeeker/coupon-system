package coupon.system.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcType(CharJdbcType.class)
    @Column(updatable = false,unique = true, nullable = false)
    private UUID uuid;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Date startDate;
    @Column(nullable = false)
    private Date endDate;
    @Column(nullable = false)
    private Double price;
    private String imageURL;
    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "customer_coupon",
            joinColumns = @JoinColumn(name = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customers;
}
