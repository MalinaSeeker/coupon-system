package coupon.system.mapper;

import coupon.system.data.dto.CompanyDto;
import coupon.system.data.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto toDto(Company company);
    Company toEntity(CompanyDto companyDto);
}
