package coupon.system.auth.signup_api.service;

import coupon.system.auth.signup_api.ex.SuchEmailAlreadyExistsException;
import coupon.system.data.dto.CustomerDto;
import coupon.system.data.entity.Customer;
import coupon.system.mapper.CustomerMapper;
import coupon.system.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    @Override
    public CustomerDto addNewCustomer(CustomerDto customerDto, String password) {
        Optional<Customer> optCustomer = customerRepository.findByEmail(customerDto.getEmail());
        if (optCustomer.isPresent()){
            throw new SuchEmailAlreadyExistsException();
        }
        Customer customer = customerMapper.toEntity(customerDto);
        customer.setPassword(password);
        customer.setUuid(UUID.randomUUID());

        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.toDto(savedCustomer);
    }
}
