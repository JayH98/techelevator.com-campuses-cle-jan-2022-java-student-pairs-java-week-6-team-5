package com.techelevator.dao;

import com.techelevator.model.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDao {

    List<Reservation> getReservationByParkId(int parkId);

    int createReservation(int siteId, String name, LocalDate fromDate, LocalDate toDate);

}
