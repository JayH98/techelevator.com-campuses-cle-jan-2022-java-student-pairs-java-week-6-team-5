package com.techelevator.dao;

import com.techelevator.model.Reservation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;



public class JdbcReservationDaoTests extends BaseDaoTests {

    LocalDate today = LocalDate.now();

    private final Reservation RESERVATION_1 = new Reservation(1,1, "Test Testerson", today.plusDays(1), today.plusDays(5), today.minusDays(23));
    private final Reservation RESERVATION_2 = new Reservation(2,1, "Bob Robertson", today.plusDays(11), today.plusDays(18), today.minusDays(23));
    private final Reservation RESERVATION_3 = new Reservation(3,1, "Manager Managerson", today.minusDays(5), today.plusDays(2), today.minusDays(23));
    private final Reservation RESERVATION_4 = new Reservation(4,1, "Leonard Leonardson", today.minusDays(11), today.minusDays(18), today.minusDays(23));

    private ReservationDao dao;

    @Before
    public void setup() {
        dao = new JdbcReservationDao(dataSource);
    }

    @Test
    public void createReservation_Should_ReturnNewReservationId() {
        int reservationCreated = dao.createReservation(1,
                "TEST NAME",
                LocalDate.now().plusDays(1),
                LocalDate.now().plusDays(3));

        assertEquals(5, reservationCreated);
    }

    @Test
    public void getReservation_Should_Return_Reservations_For_Next_Thirty_Days_By_ParkId() {
        List<Reservation> reservations = dao.getReservationByParkId(1);
        assertEquals(2, reservations.size());
        assertReservationMatch(RESERVATION_1, reservations.get(0));
        assertReservationMatch(RESERVATION_3, reservations.get(1));
    }

    private void assertReservationMatch (Reservation expected, Reservation actual) {
        Assert.assertEquals(expected.getReservationId(), actual.getReservationId());
        Assert.assertEquals(expected.getSiteId(), actual.getSiteId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getFromDate(), actual.getFromDate());
        Assert.assertEquals(expected.getToDate(), actual.getToDate());
        Assert.assertEquals(expected.getCreateDate(), actual.getCreateDate());

    }

}
