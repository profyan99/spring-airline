package net.thumbtack.airline;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("constants.properties")
public class ConstantsSetting {
    private String cookie;

    @Value("${min_password_length}")
    private int minPasswordLength;

    @Value("${max_name_length}")
    private int maxNameLength;

    @Value("${rest_http_port}")
    private String restHttpPort;

    public static final String ADMIN_ROLE = "ADMIN";
    public static final String CLIENT_ROLE = "CLIENT";
    public static final String REGISTRATION_ERROR = "Error with registration";
    public static final String ACCOUNT_EXIST_ERROR = "Account has already registered";

    public String getCookie() {
        return cookie;
    }

    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public String getRestHttpPort() {
        return restHttpPort;
    }
}