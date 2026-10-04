package de.bcxp.challenge.weather;

import java.util.logging.Logger;
import de.bcxp.challenge.reader.DatasetReader;

public class WeatherService {
    private static final Logger logger = Logger.getLogger(WeatherService.class.getName());

    private final DatasetReader<WeatherData> reader;

    public WeatherService(DatasetReader<WeatherData> reader) {
        this.reader = reader;
    }

    public String findCountryWithLowestTemperatureSpread() {
        return "None";
    }
}
