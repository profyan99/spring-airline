package net.thumbtack.airline.service.Implementation;

import net.thumbtack.airline.dao.CookieDAO;
import net.thumbtack.airline.dto.UserCookieDTO;
import net.thumbtack.airline.model.UserCookie;
import net.thumbtack.airline.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CookieServiceImpl implements CookieService {

    private CookieDAO cookieDAO;

    @Autowired
    public void setCookieDAO(CookieDAO cookieDAO) {
        this.cookieDAO = cookieDAO;
    }

    @Override
    public UserCookieDTO getUserCookie(String uuid) {
        UserCookie cookie = cookieDAO.get(uuid);
        return new UserCookieDTO(cookie.getId(), cookie.getUserType());
    }

    @Override
    public String setUserCookie(UserCookieDTO user) {
        UUID uuid = UUID.randomUUID();
        cookieDAO.set(new UserCookie(user.getId(), user.getUserType(), uuid.toString()));
        return uuid.toString();
    }

    @Override
    public void deleteUserCookie(String uuid) {
        cookieDAO.delete(uuid);
    }
}
