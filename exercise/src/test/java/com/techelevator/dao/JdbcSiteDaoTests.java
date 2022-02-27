package com.techelevator.dao;

import com.techelevator.model.Campground;
import com.techelevator.model.Site;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JdbcSiteDaoTests extends BaseDaoTests {

    private static final Site SITE_1 = new Site(1, 1, 1, 10, true, 33, true);
    private static final Site SITE_2 = new Site(2, 1, 2, 10, true, 30, true);
    private static final Site SITE_3 = new Site(3, 1, 3, 10, true, 0, true);

    private SiteDao dao;

    @Before
    public void setup() {
        dao = new JdbcSiteDao(dataSource);
    }

    @Test
    public void getSitesThatAllowRVs_Should_ReturnSites() {
        List<Site> sites = dao.getSitesThatAllowRVs(1);

        assertEquals(2, sites.size());
    }

    @Test
    public void getSite_Should_Return_Current_Site_Availability_By_Reservation() {
        List<Site> sites = dao.getSitesThatAreFreeToReserveByParkID(1);
        assertEquals(2, sites.size());
        assertSiteMatch(SITE_2, sites.get(0));
        assertSiteMatch(SITE_3, sites.get(1));
    }

    public void getAvailableSites_Should_ReturnSites() {

    }

    public void getAvailableSitesDateRange_Should_ReturnSites() {

    }

    private void assertSiteMatch(Site expected, Site actual) {
        Assert.assertEquals(expected.getSiteId(), actual.getSiteId());
        Assert.assertEquals(expected.getCampgroundId(), actual.getCampgroundId());
        Assert.assertEquals(expected.getSiteNumber(), actual.getSiteNumber());
        Assert.assertEquals(expected.getMaxOccupancy(), actual.getMaxOccupancy());
        Assert.assertEquals(expected.isAccessible(), actual.isAccessible());
        Assert.assertEquals(expected.getMaxRvLength(), actual.getMaxRvLength());
        Assert.assertEquals(expected.isUtilities(), actual.isUtilities());
    }
}