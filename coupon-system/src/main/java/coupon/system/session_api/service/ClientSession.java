package coupon.system.session_api.service;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ClientSession {
    private final ClientType clientType;
    private final long clientId;
    private long lastAccessMillis;

    public enum ClientType{
        CUSTOMER, COMPANY;
    }

    private ClientSession(long clientId, ClientType clientType) {
        this.clientId = clientId;
        this.lastAccessMillis = System.currentTimeMillis();
        this.clientType = clientType;
    }

    public static ClientSession ofNow(long employeeId, ClientType clientType) {
        return new ClientSession(employeeId, clientType);
    }
    public void access() {
        lastAccessMillis = System.currentTimeMillis();
    }


}
