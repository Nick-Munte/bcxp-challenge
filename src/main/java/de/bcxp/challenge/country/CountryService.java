package de.bcxp.challenge.country;

import de.bcxp.challenge.reader.DatasetReader;
import de.bcxp.challenge.weather.WeatherData;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class CountryService {
    private final DatasetReader<CountryData> reader;

    public CountryService(DatasetReader<CountryData> reader) {
        this.reader = reader;
    }

    /**
     * Calculates the country with the smallest ratio between population and area
     * Filters out any invalid entries, where area is not positive
     * Returns "None" if there are no valid entries
     * @return String
     * @throws Exception Any exceptions that occur during dataset reading or stream operations
     */
    public String findCountryWithHighestPopulationDensity() throws Exception {
        Collection<CountryData> data = reader.read();

        Optional<CountryData> maxDensityEntry = data.stream().
                // Filter out any entries where area is not positive
                        filter((entry) -> entry.getArea_km2() > 0).
                // Get the entry with the largest ratio between population and area
                        max(Comparator.comparingDouble(entry -> (double) entry.getPopulation() / entry.getArea_km2()));

        if (maxDensityEntry.isEmpty()) {
            return "None";
        }

        return String.valueOf(maxDensityEntry.get().getName());
    }
}
