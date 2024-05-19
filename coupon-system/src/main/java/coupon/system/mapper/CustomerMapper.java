package coupon.system.mapper;

import coupon.system.data.dto.CustomerDto;
import coupon.system.data.entity.Customer;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDto toDto(Customer customer);
    Customer toEntity(CustomerDto dto);

}
