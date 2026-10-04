package de.bcxp.challenge.country;

import de.bcxp.challenge.reader.DatasetReader;

public class CountryService {
    private final DatasetReader<CountryData> reader;

    public CountryService(DatasetReader<CountryData> reader) {
        this.reader = reader;
    }

    public String findCountryWithHighestPopulationDensity() throws Exception {
        return "None";
    }
}
