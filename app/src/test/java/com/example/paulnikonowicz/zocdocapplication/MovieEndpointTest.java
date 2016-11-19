package com.example.paulnikonowicz.zocdocapplication;

import com.example.paulnikonowicz.zocdocapplication.dao.Network;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class MovieEndpointTest {
    private String json;
    private Network network;

    @Before
    public void setup() {
        network = new Network();
        json = network.retrieveMovieListFromZipCode(98102);
        assertNotNull(json);
    }

    @Test
    public void canCreateObjectFromJsonResult() throws Exception {
        Assert.fail(json);
    }

    @Test
    @Ignore("fragile test")
    public void dateIsCorrectFormat() {
        String date = network.todaysDate();
        Assert.assertEquals("2016-11-19", date);
    }

    @Test
    public void urlIsCorrect() {
        String url = network.getUrl("host", "date", "api", "zip");
        Assert.assertEquals("http://host?startDate=date&zip=zip&api_key=api", url);
    }
}