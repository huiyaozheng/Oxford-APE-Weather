package com.develogical;

import com.teamoptimization.CachingForecaster;
import com.teamoptimization.Forecast;
import com.teamoptimization.Forecaster;
import com.teamoptimization.LocatorClient;
import org.hamcrest.core.IsNot;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CachingForecasterTests {
    @Test
    public void testNotCachedYet() throws Exception {
        Forecaster fakeForecaster = mock(Forecaster.class);
        String location = "Oxford";
        DayOfWeek day = DayOfWeek.MONDAY;

        CachingForecaster cachingForecaster = new CachingForecaster(fakeForecaster);
        cachingForecaster.forecast(location, day);

        verify(fakeForecaster).forecast(location, day);
    }

    @Test
    public void testSingleCached() throws Exception {
        Forecaster fakeForecaster = mock(Forecaster.class);
        given(fakeForecaster.forecast("Oxford", DayOfWeek.MONDAY)).willReturn(new Forecast());
        String location = "Oxford";
        DayOfWeek day = DayOfWeek.MONDAY;

        CachingForecaster cachingForecaster = new CachingForecaster(fakeForecaster);
        cachingForecaster.forecast(location, day);
        cachingForecaster.forecast(location, day);
        cachingForecaster.forecast(location, day);
        cachingForecaster.forecast(location, day);

        verify(fakeForecaster, atMostOnce()).forecast(location, day);
    }

    @Test
    public void testDifferentCached() throws Exception {
        Forecaster fakeForecaster = mock(Forecaster.class);
        given(fakeForecaster.forecast("Oxford", DayOfWeek.MONDAY)).willReturn(new Forecast(1,2, "Oxford"));
        given(fakeForecaster.forecast("London", DayOfWeek.TUESDAY)).willReturn(new Forecast(3,4,"London"));
        String location = "Oxford";
        DayOfWeek day = DayOfWeek.MONDAY;

        CachingForecaster cachingForecaster = new CachingForecaster(fakeForecaster);
        Forecast forecast1 = cachingForecaster.forecast("Oxford", DayOfWeek.MONDAY);
        Forecast forecast2 = cachingForecaster.forecast("London", DayOfWeek.TUESDAY);
        Forecast forecast3 = cachingForecaster.forecast("Oxford", DayOfWeek.MONDAY);
        Forecast forecast4 = cachingForecaster.forecast("London", DayOfWeek.TUESDAY);
        Forecast forecast5 = cachingForecaster.forecast("Oxford", DayOfWeek.MONDAY);
        Forecast forecast6 = cachingForecaster.forecast("London", DayOfWeek.TUESDAY);
        Forecast forecast7 = cachingForecaster.forecast("Oxford", DayOfWeek.MONDAY);
        Forecast forecast8 = cachingForecaster.forecast("London", DayOfWeek.TUESDAY);
        Forecast forecast9 = cachingForecaster.forecast("Oxford", DayOfWeek.MONDAY);
        Forecast forecast10 = cachingForecaster.forecast("London", DayOfWeek.TUESDAY);

        assertThat(forecast1, equalTo(forecast3));
        assertThat(forecast2, equalTo(forecast4));

        assertThat(forecast1, IsNot.not(equalTo(forecast2)));

        verify(fakeForecaster, atMost(2)).forecast(location, day);
    }
}
