package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public class NavyForecastingClientAdapater implements Forecaster {
    private NavyForecastingClient client;
    public NavyForecastingClientAdapater() {
        client = new NavyForecastingClient();
    }

    @Override
    public Forecast forecast(String location, DayOfWeek day) throws IOException {
        return new Forecast(client.min(day, location), client.max(day, location), client.desc(day, location));
    }
}
