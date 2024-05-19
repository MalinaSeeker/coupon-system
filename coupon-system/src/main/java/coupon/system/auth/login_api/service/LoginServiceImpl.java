package coupon.system.auth.login_api.service;

import coupon.system.auth.login_api.ex.InvalidLoginCredentialsException;
import coupon.system.repository.CompanyRepository;
import coupon.system.repository.CustomerRepository;
import coupon.system.session_api.service.ClientSession;
import coupon.system.data.client.LoginCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;

    @Override
    public String getToken() {
        return UUID.randomUUID().toString();
    }

    @Override
    public ClientSession createSession(String clientType, LoginCredentials loginCredentials) {
        ClientSession.ClientType type = checkAndGetClientType(clientType);

        return switch (type) {
            case CUSTOMER ->
                    ClientSession.ofNow(customerRepository.findByEmailAndPassword(loginCredentials.email(), loginCredentials.password())
                            .orElseThrow(InvalidLoginCredentialsException::new).getId(), type);
            case COMPANY ->
                    ClientSession.ofNow(companyRepository.findByEmailAndPassword(loginCredentials.email(), loginCredentials.password())
                            .orElseThrow(InvalidLoginCredentialsException::new).getId(), type);
        };
    }

    private ClientSession.ClientType checkAndGetClientType(String type) {
        return Arrays.stream(ClientSession.ClientType.values())
                .filter(enumValue -> enumValue.name().equalsIgnoreCase(type))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("Invalid client type"));
    }
}
