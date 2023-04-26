package com.oocode;

import com.teamoptimization.*;

import java.io.IOException;
import java.time.DayOfWeek;

public class Example {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new RuntimeException("Must specify Day and Place");
        }
        forecast(args[0], args[1]);
    }

    private static void forecast(String day, String place) throws IOException {
        int dayNumber = DayOfWeek.valueOf(day.toUpperCase()).getValue();
        LocatorClient.Location location = new LocatorClient().locationOf(place);
        /*
        MetOfficeForecasterClient forecasterClient = new MetOfficeForecasterClient();
        MetOfficeForecasterClient.Forecast forecast =
                forecasterClient.forecast(dayNumber, location.latitude, location.longitude);
        */
        CachingForecaster forecaster = new CachingForecaster(new MetOfficeForecasterClientAdapter());
        Forecast forecast = forecaster.forecast(place, DayOfWeek.valueOf(day.toUpperCase()));
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, forecast.minTemp, forecast.maxTemp, forecast.description);

        forecast = forecaster.forecast(place, DayOfWeek.valueOf(day.toUpperCase()));
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, forecast.minTemp, forecast.maxTemp, forecast.description);

        forecast = forecaster.forecast(place, DayOfWeek.valueOf(day.toUpperCase()));
        System.out.printf("forecaster: %s day=%s min=%s max=%s description=%s%n",
                place, day, forecast.minTemp, forecast.maxTemp, forecast.description);
    }
}

