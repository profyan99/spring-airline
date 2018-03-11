package net.thumbtack.airline;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("constants.properties")
public class ConstantsSetting {
    private String cookie;

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String CLIENT_ROLE = "CLIENT";

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
