package de.bcxp.challenge.weather;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Logger;
import de.bcxp.challenge.reader.DatasetReader;

public class WeatherService {
    private static final Logger logger = Logger.getLogger(WeatherService.class.getName());

    private final DatasetReader<WeatherData> reader;

    public WeatherService(DatasetReader<WeatherData> reader) {
        this.reader = reader;
    }

    public int findDayWithLowestTemperatureSpread() throws Exception {
        Collection<WeatherData> data = reader.read();

        Optional<WeatherData> minSpreadEntry = data.stream().
                // Filter out any entries where MnT is greater than MxT
                filter((entry) -> entry.getMnT() <= entry.getMxT()).
                // Get the entry with the smallest positive difference between MxT and MnT
                min(Comparator.comparingInt(entry -> entry.getMxT() - entry.getMnT()));

        if (minSpreadEntry.isEmpty()) {
            return -1;
        }

        return minSpreadEntry.get().getDay();
    }
}
