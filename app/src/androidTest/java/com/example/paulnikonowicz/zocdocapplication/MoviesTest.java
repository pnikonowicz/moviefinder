package com.example.paulnikonowicz.zocdocapplication;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import com.example.paulnikonowicz.zocdocapplication.dao.Movies;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MoviesTest {
    private Movies movies;

    @Before
    public void setup() {
        String json = "[{ 'tmsId': 'MV009070680000', 'rootId': '13042092', 'subType': 'Feature Film', 'title': 'Moonlight', 'releaseYear': 2016, 'releaseDate': '2016-09-02', 'titleLang': 'en', 'descriptionLang': 'en', 'entityType': 'Movie', 'genres': ['Drama'], 'longDescription': 'A young man deals with his dysfunctional home life and comes of age in Miami during the \\'War on Drugs\\' era. The story of his struggle to find himself is told across three defining chapters in his life as he experiences the ecstasy, pain, and beauty of falling in love while grappling with his own sexuality.', 'shortDescription': 'A young man experiences the joy and pain of falling in love while grappling with his own sexuality.', 'topCast': ['Mahershala Ali', 'Shariff Earp', 'Alex Hibbert'], 'directors': ['Barry Jenkins'], 'officialUrl': 'http://moonlight-movie.com/', 'qualityRating': { 'ratingsBody': 'TMS', 'value': '3.5' }, 'ratings': [{ 'body': 'Motion Picture Association of America', 'code': 'R' }], 'advisories': ['Adult Language', 'Adult Situations', 'Violence'], 'runTime': 'PT01H51M', 'preferredImage': { 'width': '240', 'height': '360', 'caption': { 'content': 'Moonlight (2016)', 'lang': 'en' }, 'uri': 'assets/p13042092_p_v5_aa.jpg', 'category': 'Poster Art', 'text': 'yes', 'primary': 'true' }, 'showtimes': [{ 'theatre': { 'id': '11008', 'name': 'SIFF Cinema Egyptian' }, 'dateTime': '2016-11-19T14:20', 'barg': false }, { 'theatre': { 'id': '11008', 'name': 'SIFF Cinema Egyptian' }, 'dateTime': '2016-11-19T16:45', 'barg': false }, { 'theatre': { 'id': '11008', 'name': 'SIFF Cinema Egyptian' }, 'dateTime': '2016-11-19T19:10', 'barg': false }, { 'theatre': { 'id': '11008', 'name': 'SIFF Cinema Egyptian' }, 'dateTime': '2016-11-19T21:30', 'barg': false }, { 'theatre': { 'id': '8184', 'name': 'Regal Meridian 16' }, 'dateTime': '2016-11-19T13:20', 'quals': 'Closed Captioned', 'barg': true, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AABFY&m=164354&d=2016-11-19' }, { 'theatre': { 'id': '8184', 'name': 'Regal Meridian 16' }, 'dateTime': '2016-11-19T16:00', 'quals': 'Closed Captioned', 'barg': false, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AABFY&m=164354&d=2016-11-19' }, { 'theatre': { 'id': '8184', 'name': 'Regal Meridian 16' }, 'dateTime': '2016-11-19T18:40', 'quals': 'Closed Captioned', 'barg': false, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AABFY&m=164354&d=2016-11-19' }, { 'theatre': { 'id': '8184', 'name': 'Regal Meridian 16' }, 'dateTime': '2016-11-19T21:20', 'quals': 'Closed Captioned', 'barg': false, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AABFY&m=164354&d=2016-11-19' }, { 'theatre': { 'id': '10602', 'name': 'Sundance Cinemas Seattle' }, 'dateTime': '2016-11-19T13:30', 'quals': '21+', 'barg': false }, { 'theatre': { 'id': '10602', 'name': 'Sundance Cinemas Seattle' }, 'dateTime': '2016-11-19T16:40', 'quals': '21+', 'barg': false }, { 'theatre': { 'id': '10602', 'name': 'Sundance Cinemas Seattle' }, 'dateTime': '2016-11-19T19:20', 'quals': '21+', 'barg': false }, { 'theatre': { 'id': '10602', 'name': 'Sundance Cinemas Seattle' }, 'dateTime': '2016-11-19T21:50', 'quals': '21+', 'barg': false }, { 'theatre': { 'id': '7955', 'name': 'AMC Loews Oak Tree 6' }, 'dateTime': '2016-11-19T10:50', 'quals': 'AMC Independent|Closed Captioned', 'barg': false, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AAAYZ&m=164354&d=2016-11-19' }, { 'theatre': { 'id': '7955', 'name': 'AMC Loews Oak Tree 6' }, 'dateTime': '2016-11-19T14:00', 'quals': 'AMC Independent|Closed Captioned', 'barg': false, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AAAYZ&m=164354&d=2016-11-19' }, { 'theatre': { 'id': '7955', 'name': 'AMC Loews Oak Tree 6' }, 'dateTime': '2016-11-19T16:40', 'quals': 'AMC Independent|Closed Captioned', 'barg': false, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AAAYZ&m=164354&d=2016-11-19' }, { 'theatre': { 'id': '7955', 'name': 'AMC Loews Oak Tree 6' }, 'dateTime': '2016-11-19T19:10', 'quals': 'Closed Captioned|AMC Independent', 'barg': false, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AAAYZ&m=164354&d=2016-11-19' }, { 'theatre': { 'id': '7955', 'name': 'AMC Loews Oak Tree 6' }, 'dateTime': '2016-11-19T21:50', 'quals': 'AMC Independent|Closed Captioned', 'barg': false, 'ticketURI': 'http://www.fandango.com/tms.asp?t=AAAYZ&m=164354&d=2016-11-19' }] }]";
        movies = new Movies(json);
    }
    @Test
    public void canGetMovie() {
        int count = movies.getCount();
        Assert.assertEquals(0, count);
    }
}
