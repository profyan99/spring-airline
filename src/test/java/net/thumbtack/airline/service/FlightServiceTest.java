package net.thumbtack.airline.service;

import net.thumbtack.airline.dao.FlightDao;
import net.thumbtack.airline.dto.request.FlightAddRequestDto;
import net.thumbtack.airline.dto.response.FlightAddResponseDto;
import net.thumbtack.airline.model.Flight;
import net.thumbtack.airline.model.Plane;
import net.thumbtack.airline.model.Schedule;
import net.thumbtack.airline.service.implementation.FlightServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class FlightServiceTest {

    @MockBean
    private FlightDao flightDaoMock;

    private FlightServiceImpl flightService;

    private Plane plane;

    private Answer flightAddAnswer;

    @Before
    public void setUp() {
        flightService = new FlightServiceImpl();
        flightService.setFlightDao(flightDaoMock);
        plane = new Plane("Airbus 319", 6, 18, 4, 6);
        flightAddAnswer = invocation -> {
            Flight flight = invocation.getArgument(0);
            flight.setId(1);
            flight.setPlane(plane);
            return null;
        };
    }

    @Test
    public void addFlightService_SimpleSchedule_ShouldReturnFlightAddResponseDto() {
        FlightAddRequestDto flightAddRequestDto = new FlightAddRequestDto(
                "flightOne", "Омск", "Питер", LocalTime.now().toString(), LocalTime.of(3, 20).toString(),
                10000, 5000, new Schedule(LocalDate.of(2018, Month.AUGUST, 5),
                LocalDate.of(2018, Month.AUGUST, 12), "daily"),
                plane.getName()
        );
        LocalDate[] expectedDates, actualDates;
        expectedDates = new LocalDate[flightAddRequestDto.getSchedule().getToDate().getDayOfMonth() -
                flightAddRequestDto.getSchedule().getFromDate().getDayOfMonth() + 1];
        int first = flightAddRequestDto.getSchedule().getFromDate().getDayOfMonth();
        for (int i = flightAddRequestDto.getSchedule().getFromDate().getDayOfMonth(),
             end = LocalDate.from(flightAddRequestDto.getSchedule().getToDate()).getDayOfMonth(); i <= end; i++) {
            expectedDates[i - first] = LocalDate.of(2018, Month.AUGUST, i);
        }

        when(flightDaoMock.getPlane(anyString())).thenReturn(Optional.of(plane));
        doAnswer(flightAddAnswer).when(flightDaoMock).add(any(Flight.class));
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
        verify(flightDaoMock, times(1)).add(any(Flight.class));
        verify(flightDaoMock, times(1)).exists(anyString());
        verifyNoMoreInteractions(flightDaoMock);
    }

    @Test
    public void addFlightService_OddAndEvenSchedule_ShouldReturnFlightAddResponseDto() {
        FlightAddRequestDto flightAddRequestDto = new FlightAddRequestDto(
                "flightOne", "Омск", "Питер", LocalTime.now().toString(), LocalTime.of(3, 20).toString(),
                10000, 5000, new Schedule(LocalDate.of(2018, Month.APRIL, 25),
                LocalDate.of(2018, Month.MAY, 4), "odd"),
                plane.getName()
        );
        LocalDate[] expectedDates, actualDates;
        expectedDates = new LocalDate[]{
                LocalDate.of(2018, Month.APRIL, 25),
                LocalDate.of(2018, Month.APRIL, 27),
                LocalDate.of(2018, Month.APRIL, 29),
                LocalDate.of(2018, Month.MAY, 1),
                LocalDate.of(2018, Month.MAY, 3),
        };

        when(flightDaoMock.getPlane(anyString())).thenReturn(Optional.of(plane));
        doAnswer(flightAddAnswer).when(flightDaoMock).add(any(Flight.class));
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

        flightAddRequestDto.getSchedule().setPeriod("even");
        expectedDates = new LocalDate[]{
                LocalDate.of(2018, Month.APRIL, 26),
                LocalDate.of(2018, Month.APRIL, 28),
                LocalDate.of(2018, Month.APRIL, 30),
                LocalDate.of(2018, Month.MAY, 2),
                LocalDate.of(2018, Month.MAY, 4),
        };

        flightAddResponseDto = flightService.add(flightAddRequestDto);
        actualDates = new LocalDate[flightAddResponseDto.getDates().size()];
        actualDates = flightAddResponseDto.getDates().toArray(actualDates);

        assertEquals(expectedDates.length, actualDates.length);
        assertArrayEquals(expectedDates, actualDates);
        assertEquals(flightAddRequestDto.getSchedule().getPeriod(), flightAddResponseDto.getSchedule().getPeriod());

        verify(flightDaoMock, times(2)).getPlane(anyString());
        verify(flightDaoMock, times(2)).add(any(Flight.class));
        verify(flightDaoMock, times(2)).exists(anyString());
        verifyNoMoreInteractions(flightDaoMock);
    }

    @Test
    public void addFlightService_WeekSchedule_ShouldReturnFlightAddResponseDto() {
        FlightAddRequestDto flightAddRequestDto = new FlightAddRequestDto(
                "flightOne", "Омск", "Питер", LocalTime.now().toString(), LocalTime.of(3, 20).toString(),
                10000, 5000, new Schedule(LocalDate.of(2018, Month.APRIL, 27),
                LocalDate.of(2018, Month.MAY, 4), "SAT,SUN"),
                plane.getName()
        );
        LocalDate[] expectedDates, actualDates;
        expectedDates = new LocalDate[]{
                LocalDate.of(2018, Month.APRIL, 28),
                LocalDate.of(2018, Month.APRIL, 29)
        };

        when(flightDaoMock.getPlane(anyString())).thenReturn(Optional.of(plane));
        doAnswer(flightAddAnswer).when(flightDaoMock).add(any(Flight.class));
        when(flightDaoMock.exists(anyString())).thenReturn(false);

        FlightAddResponseDto flightAddResponseDto = flightService.add(flightAddRequestDto);
        assertEquals(plane.getName(), flightAddResponseDto.getPlane().getName());
        assertEquals(flightAddRequestDto.getFromTown(), flightAddResponseDto.getFromTown());
        assertEquals(1, flightAddResponseDto.getFlightId());

        actualDates = new LocalDate[flightAddResponseDto.getDates().size()];
        actualDates = flightAddResponseDto.getDates().toArray(actualDates);

        assertEquals(expectedDates.length, actualDates.length);
        assertArrayEquals(expectedDates, actualDates);
        assertEquals(flightAddRequestDto.getSchedule().getPeriod(), flightAddResponseDto.getSchedule().getPeriod());

        flightAddRequestDto.getSchedule().setFromDate(LocalDate.of(2018, Month.APRIL, 30));
        expectedDates =  new LocalDate[0];

        flightAddResponseDto = flightService.add(flightAddRequestDto);
        actualDates = new LocalDate[flightAddResponseDto.getDates().size()];
        actualDates = flightAddResponseDto.getDates().toArray(actualDates);

        assertEquals(expectedDates.length, actualDates.length);
        assertArrayEquals(expectedDates, actualDates);
        assertEquals(flightAddRequestDto.getSchedule().getPeriod(), flightAddResponseDto.getSchedule().getPeriod());

        verify(flightDaoMock, times(2)).getPlane(anyString());
        verify(flightDaoMock, times(2)).add(any(Flight.class));
        verify(flightDaoMock, times(2)).exists(anyString());
        verifyNoMoreInteractions(flightDaoMock);
    }

    @Test
    public void addFlightService_MonthDaySchedule_ShouldReturnFlightAddResponseDto() {
        FlightAddRequestDto flightAddRequestDto = new FlightAddRequestDto(
                "flightOne", "Омск", "Питер", LocalTime.now().toString(), LocalTime.of(3, 20).toString(),
                10000, 5000, new Schedule(
                LocalDate.of(2018, Month.FEBRUARY, 23),
                LocalDate.of(2018, Month.MARCH, 12),
                "12, 15 , 30,31"),
                plane.getName()
        );
        LocalDate[] expectedDates, actualDates;
        expectedDates = new LocalDate[]{
                LocalDate.of(2018, Month.MARCH, 12)
        };

        when(flightDaoMock.getPlane(anyString())).thenReturn(Optional.of(plane));
        doAnswer(flightAddAnswer).when(flightDaoMock).add(any(Flight.class));
        when(flightDaoMock.exists(anyString())).thenReturn(false);

        FlightAddResponseDto flightAddResponseDto = flightService.add(flightAddRequestDto);
        assertEquals(plane.getName(), flightAddResponseDto.getPlane().getName());
        assertEquals(flightAddRequestDto.getFromTown(), flightAddResponseDto.getFromTown());
        assertEquals(1, flightAddResponseDto.getFlightId());

        actualDates = new LocalDate[flightAddResponseDto.getDates().size()];
        actualDates = flightAddResponseDto.getDates().toArray(actualDates);

        assertEquals(expectedDates.length, actualDates.length);
        assertArrayEquals(expectedDates, actualDates);
        assertEquals(flightAddRequestDto.getSchedule().getPeriod(), flightAddResponseDto.getSchedule().getPeriod());

        flightAddRequestDto.getSchedule().setToDate(LocalDate.of(2018, Month.MARCH, 11));
        expectedDates =  new LocalDate[0];

        flightAddResponseDto = flightService.add(flightAddRequestDto);
        actualDates = new LocalDate[flightAddResponseDto.getDates().size()];
        actualDates = flightAddResponseDto.getDates().toArray(actualDates);

        assertEquals(expectedDates.length, actualDates.length);
        assertArrayEquals(expectedDates, actualDates);
        assertEquals(flightAddRequestDto.getSchedule().getPeriod(), flightAddResponseDto.getSchedule().getPeriod());

        verify(flightDaoMock, times(2)).getPlane(anyString());
    verify(flightDaoMock, times(2)).add(any(Flight.class));
        verify(flightDaoMock, times(2)).exists(anyString());
        verifyNoMoreInteractions(flightDaoMock);
    }
}
