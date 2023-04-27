package com.develogical;

import com.oocode.MetOfficeForecasterClientAdapter;
import com.teamoptimization.Forecast;
import com.teamoptimization.Forecaster;
import com.teamoptimization.NavyForecastingClientAdapater;

import java.time.DayOfWeek;

public class AveragingClient {
    public AveragingClient(Forecaster metAdapter, Forecaster navyAdapter) {
    }

    public Forecast forecast(String oxford, DayOfWeek dayOfWeek) {
        return new Forecast();
    }
}
