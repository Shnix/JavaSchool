package enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN, ROLE_DRIVER;

    public String getAuthority() {
        return name();
    }

}
