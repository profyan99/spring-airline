package net.thumbtack.airline.service;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.service.Implementation.FlightServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class FlightServiceTest {

    @MockBean
    private FlightDao flightDaoMock;

    FlightServiceImpl flightService;

    @Before
    public void setUp() {
        flightService = new FlightServiceImpl();
        flightService.setFlightDao(flightDaoMock);
    }
}
