package com.oocode;

import com.teamoptimization.*;

import java.io.IOException;
import java.time.DayOfWeek;

public class MetOfficeForecasterClientAdapter implements Forecaster {
    private MetOfficeForecasterClient client;
    public MetOfficeForecasterClientAdapter() {
        client = new MetOfficeForecasterClient();
    }
    @Override
    public Forecast forecast(String locationName, DayOfWeek day) throws IOException {
        LocatorClient.Location location = new LocatorClient().locationOf(locationName);
        MetOfficeForecasterClient.Forecast forecast= client.forecast(day.getValue(), location.latitude, location.longitude);
        return new Forecast(forecast.minTemp, forecast.maxTemp, forecast.description);
    }
}
