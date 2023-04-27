package com.develogical;

import com.oocode.MetOfficeForecasterClientAdapter;
import com.teamoptimization.*;
import org.hamcrest.core.IsNot;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class AveragingClientTests {
    @Test
    public void test() throws IOException {
        Forecaster fakeMetAdapter = mock(Forecaster.class);
        Forecaster fakeNavyAdapter = mock(Forecaster.class);
        given(fakeMetAdapter.forecast("Oxford", DayOfWeek.MONDAY)).willReturn(new Forecast(1,2,"blahblah"));
        given(fakeNavyAdapter.forecast("Oxford", DayOfWeek.MONDAY)).willReturn(new Forecast(3,4,"blah"));

        AveragingClient client = new AveragingClient(fakeMetAdapter, fakeNavyAdapter);
        Forecast forecast = client.forecast("Oxford", DayOfWeek.MONDAY);

        assertThat(forecast.minTemp, equalTo(2));
        assertThat(forecast.maxTemp, equalTo(3));
        assertThat(forecast.description, anyOf(equalTo("blahblah"), equalTo("blah")));
    }
}
