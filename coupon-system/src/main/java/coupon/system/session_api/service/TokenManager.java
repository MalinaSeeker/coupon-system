package coupon.system.session_api.service;

import coupon.system.session_api.ex.ExpiredSessionException;
import coupon.system.session_api.ex.IllegalAccessException;
import coupon.system.repository.CompanyRepository;
import coupon.system.repository.CustomerRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
@Getter
public class TokenManager {
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final Map<String, ClientSession> tokens;
    private final String maxSessionTime;

    public TokenManager(CustomerRepository customerRepository, CompanyRepository companyRepository,
                        Map<String, ClientSession> tokens,
                        @Value("${session_time.millis.max}") String maxSessionTime) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
        this.tokens = tokens;
        this.maxSessionTime = maxSessionTime;
    }

    public UUID getClientsUuid(String token) {
        ClientSession clientSession = tokens.get(token);
        if (clientSession == null) {
            throw new IllegalAccessException("Invalid token");
        }
        if (isSessionExpired(clientSession)) {
            deleteClientSession(token);
            throw new ExpiredSessionException("Session has expired");
        }
        else {
            System.out.println(tokens);
            clientSession.access();
        }

        return switch (clientSession.getClientType()) {
            case CUSTOMER -> customerRepository.findById(clientSession.getClientId())
                    .orElseThrow(() -> new IllegalAccessException("Access denied")).getUuid();
            case COMPANY -> companyRepository.findById((clientSession.getClientId()))
                    .orElseThrow(() -> new IllegalAccessException("Access denied")).getUuid();
        };
    }

    private void deleteClientSession(String token) {
        tokens.remove(token);
    }


    private boolean isSessionExpired(ClientSession clientSession) {
        long timePassed = System.currentTimeMillis() - clientSession.getLastAccessMillis();
        long parsedMexSessionTime = Long.parseLong(maxSessionTime);
        return timePassed > parsedMexSessionTime;
    }
}


