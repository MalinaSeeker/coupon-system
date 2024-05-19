package coupon.system.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @JdbcType(CharJdbcType.class)
    @Column(updatable = false,unique = true, nullable = false)
    private UUID uuid;
    @Column(nullable = false)
    private  String firstName;
    @Column(nullable = false)
    private  String lastName;
    @Column(nullable = false, unique = true)
    private  String email;
    @Column(nullable = false)
    private  String password;
    @ManyToMany(mappedBy = "customers", cascade = {CascadeType.PERSIST})
    private List<Coupon> coupons;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(uuid, customer.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
