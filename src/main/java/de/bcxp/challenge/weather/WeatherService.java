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

    /**
     * Calculates the day with the smallest difference between MxT and MnT
     * Filters out any invalid entries, where MxT < MnT
     * Returns "None" if there are no entries
     * @return String
     * @throws Exception Any exceptions that occur during dataset reading or stream operations
     */
    public String findDayWithLowestTemperatureSpread() throws Exception {
        Collection<WeatherData> data = reader.read();

        Optional<WeatherData> minSpreadEntry = data.stream().
                // Filter out any entries where MnT is greater than MxT
                filter((entry) -> entry.getMnT() <= entry.getMxT()).
                // Get the entry with the smallest positive difference between MxT and MnT
                min(Comparator.comparingInt(entry -> entry.getMxT() - entry.getMnT()));

        if (minSpreadEntry.isEmpty()) {
            return "None";
        }

        return String.valueOf(minSpreadEntry.get().getDay());
    }
}
