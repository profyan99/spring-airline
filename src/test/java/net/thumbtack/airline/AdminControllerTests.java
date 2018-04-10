package net.thumbtack.airline;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.dao.AdminDao;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDto;
import net.thumbtack.airline.exception.ErrorCode;
import net.thumbtack.airline.model.UserRole;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.servlet.http.Cookie;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    Utils utils;

    @Autowired
    AdminDao adminDao;

    private Cookie cookie;

    @Test
    public void registrationAdminTest() throws Exception {
        AdminRegistrationRequestDto requestDTO = new AdminRegistrationRequestDto(
                "Дмитрий",
                "Колончев",
                "Васильев",
                "Главный администратор",
                "NewLogin",
                "dimka1323"
        );
        MvcResult result = this.mvc.perform(post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestDTO))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.firstName").value(requestDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(requestDTO.getLastName()))
                .andExpect(jsonPath("$.patronymic").value(requestDTO.getPatronymic()))
                .andExpect(jsonPath("$.position").value(requestDTO.getPosition()))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.userType").value(UserRole.ADMIN.toString()))
                .andReturn();
        this.cookie = result.getResponse().getCookie("JAVASESSIONID");


        requestDTO = new AdminRegistrationRequestDto(
                "Dmitriy",
                "Колончев",
                "Васильев",
                "Главный администратор",
                "simpleLogin",
                "simplePass"
        );
        this.mvc.perform(post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestDTO))
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].errorCode").exists());

        requestDTO.setPassword("");
        requestDTO.setFirstName("Дмитрий");
        requestDTO.setLogin("NewLogin");
        this.mvc.perform(post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestDTO))
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].errorCode").exists());

        requestDTO.setPassword("SimplePassword");
        this.mvc.perform(post("/api/admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestDTO))
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].errorCode", is(ErrorCode.ACCOUNT_EXIST_ERROR.name())));
    }

    /*@Test
    public void updateAdminTest() throws Exception {
        AdminUpdateRequestDto requestDto = new AdminUpdateRequestDto(
                "Дмитрий",
                "Колончев",
                "Васильев",
                "Главный администратор",
                "dimka1323",
                "newpassword"
        );
        this.mvc.perform(put("/api/admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestDto))
                .cookie(this.cookie)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.firstName").value(requestDto.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(requestDto.getLastName()))
                .andExpect(jsonPath("$.patronymic").value(requestDto.getPatronymic()))
                .andExpect(jsonPath("$.position").value(requestDto.getPosition()))
                .andExpect(jsonPath("$.userType").value(UserRole.ADMIN.toString()));

        requestDto.setOldPassword("newpassword");
        this.mvc.perform(put("/api/admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestDto))
                .cookie(this.cookie)
        )
                .andExpect(status().isOk());


        requestDto.setOldPassword("InvalidPassword");
        this.mvc.perform(put("/api/admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestDto))
                .cookie(this.cookie)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].errorCode", is(ErrorCode.INVALID_PASSWORD.name())));

        requestDto.setOldPassword("newpassword");
        requestDto.setLastName("");

        this.mvc.perform(put("/api/admin")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(requestDto))
                .cookie(this.cookie)
        )
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$[0].errorCode", containsInAnyOrder("Bad lastName")));
    }*/

    @After
    public void afterTest() {
        adminDao.clearDataBase();
    }
}
