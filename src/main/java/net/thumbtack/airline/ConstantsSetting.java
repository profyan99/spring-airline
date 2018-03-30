package net.thumbtack.airline;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties
@PropertySource("classpath:constants.properties")
public class ConstantsSetting {

    @Value("${min_password_length}")
    private int minPasswordLength;

    @Value("${max_name_length}")
    private int maxNameLength;

    @Value("${rest_http_port}")
    private String restHttpPort;

    public static final String BAD_FIRSTNAME = "Bad firstName";
    public static final String BAD_LASTNAME = "Bad lastName";
    public static final String BAD_PATRONYMIC = "Bad patronymic";
    public static final String BAD_LOGIN = "Bad login";
    public static final String BAD_PASSWORD = "Bad password";
    public static final String BAD_PHONE = "Bad phone";
    public static final String BAD_EMAIL = "Bad email";
    public static final String BAD_PASSPORT = "Bad passport";

    public static final String EMAIL_REGEX = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    public static final String NAME_REGEX = "^[а-яА-ЯёЁ\\s\\-]+$";

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
