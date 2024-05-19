package coupon.system.auth.signup_api.service;

import coupon.system.data.dto.CustomerDto;

public interface SignUpService {
    CustomerDto addNewCustomer(CustomerDto customerDto, String password);

}
