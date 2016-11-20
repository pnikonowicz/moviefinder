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
public class FetchMoviesServiceTest {
    private Network network;

    @Before
    public void setup() {
        network = new Network();
    }

    @Test
    public void canRetrieveJson() throws Exception {
        String json = network.retrieveMovieListFromZipCode(98102);
        Assert.assertNotNull(json);
    }

    @Test
    @Ignore("fragile test")
    public void dateIsCorrectFormat() {
        String date = network.todaysDate();
        Assert.assertEquals("2016-11-19", date);
    }

    @Test
    public void urlIsCorrect() {
        String url = network.getUrl("data.tmsapi.com/v1.1/movies/showings", "2016-11-19", "488kpuyjtxzat8q3qtg7sekx", "98102");
        Assert.assertEquals("http://data.tmsapi.com/v1.1/movies/showings?startDate=2016-11-19&zip=98102&api_key=488kpuyjtxzat8q3qtg7sekx", url);
    }
}