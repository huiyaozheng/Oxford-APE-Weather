package com.teamoptimization;

public class Forecast {
    public int minTemp;
    public int maxTemp;
    public String description;

    public Forecast(int minTemp, int maxTemp, String description) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.description = description;
    }

    public Forecast() {
    }

    @Override
    public String toString() {
        return "Forecast(" + minTemp + ", " + maxTemp + ", " + description + ")";
    }
}
