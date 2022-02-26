package com.techelevator.dao;

import com.techelevator.model.Campground;
import com.techelevator.model.Site;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class JdbcSiteDaoTests extends BaseDaoTests {

   // private static final Campground CAMPGROUND_1 = new Campground(1, 1, "Test Campground 1", 1, 12, 35.0);
    // private static final Campground CAMPGROUND_2 = new Campground(2, 1, "Test Campground 2", 1, 12, 35.0);

    private static final Site SITE_1 = new Site(1,1,  1, 10, true, 33, true);
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

        assertEquals(2,sites.size());
    }

    public void getAvailableSites_Should_ReturnSites() {

    }

    public void getAvailableSitesDateRange_Should_ReturnSites() {

    }

}
