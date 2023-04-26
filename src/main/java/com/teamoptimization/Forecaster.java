package com.teamoptimization;

import java.io.IOException;
import java.time.DayOfWeek;

public interface Forecaster {
    public Forecast forecast(String location, DayOfWeek day) throws IOException;
}

