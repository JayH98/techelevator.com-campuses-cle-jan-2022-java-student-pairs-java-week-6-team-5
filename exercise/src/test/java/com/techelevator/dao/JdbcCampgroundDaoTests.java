package com.techelevator.dao;

import com.techelevator.model.Campground;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JdbcCampgroundDaoTests extends BaseDaoTests {

    private static final Campground CAMPGROUND_1 = new Campground(1, 1, "Test Campground 1", 1, 12, 35.0);
    private static final Campground CAMPGROUND_2 = new Campground(2, 1, "Test Campground 2", 2, 12, 35.0);

    private CampgroundDao dao;

    @Before
    public void setup() {
        dao = new JdbcCampgroundDao(dataSource);
    }

    @Test
    public void getCampgrounds_Should_ReturnAllCampgrounds() {
        List<Campground> campgrounds = dao.getCampgroundsByParkId(1);
        assertEquals(2,campgrounds.size());
        assertCampgroundsMatch(CAMPGROUND_1, campgrounds.get(0));
        assertCampgroundsMatch(CAMPGROUND_2, campgrounds.get(1));
    }

    private void assertCampgroundsMatch(Campground expected, Campground actual) {
        Assert.assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
        Assert.assertEquals(expected.getParkId(), actual.getParkId());
        Assert.assertEquals(expected.getName(), actual.getName());
        Assert.assertEquals(expected.getOpenFromMonth(), actual.getOpenFromMonth());
        Assert.assertEquals(expected.getOpenToMonth(), actual.getOpenToMonth());
        Assert.assertEquals(expected.getDailyFee(), actual.getDailyFee(), 0.01);
    }


}
