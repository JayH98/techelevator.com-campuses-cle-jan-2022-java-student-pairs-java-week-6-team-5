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

//    private static final Park PARK_1 = new Park(1,"Park 1", "Pennsylvania", LocalDate.parse("1/1/1970"), 1024, 512, "Test description 1");
//    private static final Park PARK_2 = new Park(2,"Park 2", "Ohio", LocalDate.parse("1/1/1970"), 2048, 1024, "Test description 2");

    private ParkDao dao;

    @Before
    public void setup() {
        dao = new JdbcParkDao(dataSource);
    }

    @Test
    public void getParksTest_Should_ReturnAllParksInLocationAlphaOrder() {
        List<Park> parks = dao.getAllParks();

        assertEquals(2, parks.size());
        assertEquals("Ohio", parks.get(0).getLocation());
        assertEquals("Pennsylvania", parks.get(1).getLocation());
    }
//
//    private void assertParksMatch (Park expected, Park actual) {
//        Assert.assertEquals(expected.getName(), actual.getName());
//        Assert.assertEquals(expected.getLocation(), actual.getLocation());
//        Assert.assertEquals(expected.getEstablishDate(), actual.getEstablishDate());
//        Assert.assertEquals(expected.getArea(), actual.getArea());
//        Assert.assertEquals(expected.getVisitors(), actual.getVisitors());
//        Assert.assertEquals(expected.getDescription(), actual.getDescription());
//    }
}
