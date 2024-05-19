package coupon.system.auth.login_api.service;

import coupon.system.session_api.service.ClientSession;
import coupon.system.data.client.LoginCredentials;

public interface LoginService {
    String getToken();
    ClientSession createSession(String clientType, LoginCredentials loginCredentials);
}
