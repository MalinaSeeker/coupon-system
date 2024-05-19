package coupon.system.auth.login_api.controller;

import coupon.system.auth.login_api.service.LoginService;
import coupon.system.session_api.service.ClientSession;
import coupon.system.data.client.LoginCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class LoginController {
    private final Map<String, ClientSession> tokens;
    private final LoginService service;

    @PostMapping("/client")
    public ResponseEntity<String> handleLogin(@RequestParam(name = "type") String clientType,
                                              @RequestBody LoginCredentials loginCredentials) {
        ClientSession session = service.createSession(clientType, loginCredentials);
        String token = service.getToken();

        tokens.put(token, session);

        return ResponseEntity.ok(token);
    }
}
