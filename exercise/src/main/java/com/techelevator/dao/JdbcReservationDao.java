package com.techelevator.dao;

import com.techelevator.model.Park;
import com.techelevator.model.Reservation;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JdbcReservationDao implements ReservationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcReservationDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Reservation> getReservationByParkId(int parkId){
        List<Reservation> reservations = new ArrayList<>();
        String sql = "SELECT reservation_id, reservation.site_id, reservation.name, from_date, to_date, create_date " +
                "FROM reservation " +
                "JOIN site ON reservation.site_id = site.site_id " +
                "JOIN campground ON site.campground_id = campground.campground_id " +
                "JOIN park ON campground.park_id = park.park_id " +
                "WHERE from_date BETWEEN create_date AND create_date + 30 AND park.park_id = ? And from_date < to_date " +
                "ORDER BY from_date DESC;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, parkId);
        while (results.next()){
            reservations.add(mapRowToReservation(results));
        }
        return reservations;
    }

    @Override
    public int createReservation(int siteId, String name, LocalDate fromDate, LocalDate toDate) {
        String sql = "INSERT INTO reservation (site_id, name, from_date, to_date) " +
                "VALUES (?, ?, ?, ?) " +
                "RETURNING reservation_id;";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class, siteId, name, fromDate, toDate);

        return newId;
    }


    private Reservation mapRowToReservation(SqlRowSet results) {
        Reservation r = new Reservation();
        r.setReservationId(results.getInt("reservation_id"));
        r.setSiteId(results.getInt("site_id"));
        r.setName(results.getString("name"));
        r.setFromDate(results.getDate("from_date").toLocalDate());
        r.setToDate(results.getDate("to_date").toLocalDate());
        r.setCreateDate(results.getDate("create_date").toLocalDate());
        return r;
    }


}
