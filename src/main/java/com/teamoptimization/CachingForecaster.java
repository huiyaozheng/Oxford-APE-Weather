package com.teamoptimization;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CachingForecaster implements Forecaster {
    @Override
    public Forecast forecast(String locationName, DayOfWeek day) throws IOException {
        LocatorClient.Location location = new LocatorClient().locationOf(locationName);
        Query query = new Query(locationName, day);
        if (cache.containsKey(query)) {
            return cache.get(query);
        } else {
            Forecast forecast = source.forecast(locationName, day);
            cache.put(query, forecast);
            return forecast;
        }
    }

    private class Query {
        public String locationName;
        public DayOfWeek day;

        public Query(String _locationName, DayOfWeek _day) {
            locationName = _locationName;
            day = _day;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Query that = (Query) o;
            return locationName.equals(that.locationName) && day.equals(that.day);
        }

        @Override
        public int hashCode() {
            return Objects.hash(locationName, day);
        }
    }

    ;
    private Forecaster source;
    private Map<Query, Forecast> cache = new HashMap<>();

    public CachingForecaster(Forecaster _source) {
        source = _source;
    }
}
