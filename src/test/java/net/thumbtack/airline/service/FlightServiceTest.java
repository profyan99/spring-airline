package net.thumbtack.airline.service;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dto.request.FlightAddRequestDto;
import net.thumbtack.airline.dto.response.FlightAddResponseDto;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.Schedule;
import net.thumbtack.airline.service.Implementation.FlightServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    public void addFlightService_ShouldReturnFlightAddResponseDto() {
        FlightAddRequestDto flightAddRequestDto = new FlightAddRequestDto(
                "flightOne", "Омск", "Питер", LocalTime.now().toString(), LocalTime.of(3, 20).toString(),
                10000, 5000, new Schedule(LocalDate.of(2018, Month.AUGUST, 5),
                LocalDate.of(2018, Month.AUGUST, 12), "daily"),
                "Airbus 319"
        );
        LocalDate[] expectedDates, actualDates;
        expectedDates = new LocalDate[flightAddRequestDto.getSchedule().getToDate().getDayOfMonth() -
                flightAddRequestDto.getSchedule().getFromDate().getDayOfMonth()  + 1];
        int first = flightAddRequestDto.getSchedule().getFromDate().getDayOfMonth();
        for (int i = flightAddRequestDto.getSchedule().getFromDate().getDayOfMonth(),
             end = LocalDate.from(flightAddRequestDto.getSchedule().getToDate()).getDayOfMonth(); i <= end; i++) {
            expectedDates[i-first] = LocalDate.of(2018, Month.AUGUST, i);
        }
        Plane plane = new Plane(flightAddRequestDto.getPlaneName(), 6, 18, 4, 6);

        when(flightDaoMock.getPlane(anyString())).thenReturn(Optional.of(plane));
        doAnswer(invocation -> {
            Flight flight = invocation.getArgument(0);
            flight.setId(1);
            flight.setPlane(plane);
            return null;
        }).when(flightDaoMock).add(any(Flight.class), anyList());
        when(flightDaoMock.exists(anyString())).thenReturn(false);

        FlightAddResponseDto flightAddResponseDto = flightService.add(flightAddRequestDto);
        assertEquals(plane.getName(), flightAddResponseDto.getPlane().getName());
        assertEquals(flightAddRequestDto.getFromTown(), flightAddResponseDto.getFromTown());
        assertEquals(flightAddRequestDto.getStart(), flightAddResponseDto.getStart());
        assertEquals(1, flightAddResponseDto.getFlightId());
        assertFalse(flightAddResponseDto.isApproved());

        actualDates = new LocalDate[flightAddResponseDto.getDates().size()];
        actualDates = flightAddResponseDto.getDates().toArray(actualDates);

        assertEquals(expectedDates.length, actualDates.length);
        assertArrayEquals(expectedDates, actualDates);
        assertEquals(flightAddRequestDto.getSchedule().getPeriod(), flightAddResponseDto.getSchedule().getPeriod());

        verify(flightDaoMock, times(1)).getPlane(anyString());
        verify(flightDaoMock, times(1)).add(any(Flight.class), anyList());
        verify(flightDaoMock, times(1)).exists(anyString());
        verifyNoMoreInteractions(flightDaoMock);
    }
}
