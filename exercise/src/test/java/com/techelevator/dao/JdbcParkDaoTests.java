package com.techelevator.dao;

import com.techelevator.model.Campground;
import com.techelevator.model.Park;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class JdbcParkDaoTests extends BaseDaoTests {

    private static final Park PARK_1 = new Park(1,"Park 1", "Pennsylvania", LocalDate.parse("1970-01-01"), 1024, 512, "Test description 1");
    private static final Park PARK_2 = new Park(2,"Park 2", "Ohio", LocalDate.parse("1970-01-01"), 2048, 1024, "Test description 2");

    private ParkDao dao;

    @Before
    public void setup() {
        dao = new JdbcParkDao(dataSource);
    }

    @Test
    public void getParksTest_Should_ReturnAllParksInLocationAlphaOrder() {
        List<Park> parks = dao.getAllParks();

        assertEquals(2, parks.size());
        assertEquals("Pennsylvania", parks.get(0).getLocation());
        assertEquals("Ohio", parks.get(1).getLocation());


    }

}
