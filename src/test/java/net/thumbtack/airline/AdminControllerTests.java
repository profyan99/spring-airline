package net.thumbtack.airline;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.thumbtack.airline.dao.AdminDAO;
import net.thumbtack.airline.dto.request.AdminRegistrationRequestDTO;
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
    AdminDAO adminDAO;

    /*@Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }*/

    @Test
    public void registrationAdminTest() throws Exception {
        AdminRegistrationRequestDTO requestDTO = new AdminRegistrationRequestDTO(
                "Дмитрий",
                "Колончев",
                "Васильев",
                "Главный администратор",
                "dmitriy55",
                "dimka1323"
        );
        this.mvc.perform(post("/api/admin")
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
        .andExpect(jsonPath("$.userType").value(UserRole.ADMIN_ROLE.toString()));
    }

    @After
    public void afterTest() {
        adminDAO.clearDataBase();
    }
}
