package com.develogical;

import com.teamoptimization.CachingForecaster;
import com.teamoptimization.Forecaster;
import com.teamoptimization.LocatorClient;
import org.junit.Test;

import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
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
    public void testCached() throws Exception {
        Forecaster fakeForecaster = mock(Forecaster.class);
        String location = "Oxford";
        DayOfWeek day = DayOfWeek.MONDAY;

        CachingForecaster cachingForecaster = new CachingForecaster(fakeForecaster);
        cachingForecaster.forecast(location, day);
        cachingForecaster.forecast(location, day);
        cachingForecaster.forecast(location, day);
        cachingForecaster.forecast(location, day);

        verify(fakeForecaster, atMostOnce()).forecast(location, day);
    }
}
