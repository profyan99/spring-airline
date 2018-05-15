package net.thumbtack.airline;

import net.thumbtack.airline.dto.request.FlightAddRequestDto;
import net.thumbtack.airline.dto.response.FlightAddResponseDto;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.Schedule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class FlightIntegrationTest {

    private RestTemplate restTemplate;

    @Before
    public void setUp() {
        restTemplate = new RestTemplate();
    }

    @Test
    public void addFlightIntegrationTest() {
        Plane plane = new Plane("Airbus A319", 5, 16, 4, 6);

        FlightAddRequestDto flightAddRequestDto = new FlightAddRequestDto(
                "flightOne", "Омск", "Питер", LocalTime.now(), LocalTime.of(3, 20),
                10000, 5000, new Schedule(LocalDate.of(2018, Month.AUGUST, 5),
                LocalDate.of(2018, Month.AUGUST, 12), "daily"),
                plane.getName()
        );
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", "JAVASESSIONID=test");
        HttpEntity requestEntity = new HttpEntity(flightAddRequestDto, httpHeaders);
        FlightAddResponseDto responseDto = restTemplate.postForObject("http://localhost:8080/api/flights/",
                requestEntity, FlightAddResponseDto.class);
        assertNotNull(responseDto);
        assertEquals(flightAddRequestDto.getPlaneName(), responseDto.getPlane().getName());
    }


}
