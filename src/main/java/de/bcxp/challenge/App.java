package de.bcxp.challenge;

import de.bcxp.challenge.country.CountryData;
import de.bcxp.challenge.country.CountryService;
import de.bcxp.challenge.reader.DatasetReader;
import de.bcxp.challenge.reader.csv.CsvDatasetReader;
import de.bcxp.challenge.reader.csv.CsvRowFactory;
import de.bcxp.challenge.weather.WeatherData;
import de.bcxp.challenge.weather.WeatherService;

import java.nio.file.Path;
import java.nio.file.Paths;

public final class App {

    /**
     * This is the main entry method of the challenge.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) throws Exception {
        WeatherService weatherService = App.getWeatherService();
        CountryService countryService = App.getCountryService();

        String dayWithSmallestTempSpread = weatherService.findDayWithLowestTemperatureSpread();
        System.out.printf("Day with smallest temperature spread: %s%n", dayWithSmallestTempSpread);

        String countryWithHighestPopulationDensity = countryService.findCountryWithHighestPopulationDensity();
        System.out.printf("Country with highest population density: %s%n", countryWithHighestPopulationDensity);
    }

    private static WeatherService getWeatherService() {
        Path csvFilePath = Paths.get("src", "main", "resources", "de", "bcxp", "challenge", "weather.csv");
        CsvRowFactory<WeatherData> rowFactory = (entry) -> {
            return new WeatherData(
                    Integer.parseInt(entry.get("Day")), Integer.parseInt(entry.get("MxT")),
                    Integer.parseInt(entry.get("MnT")), Integer.parseInt(entry.get("AvT")),
                    Float.parseFloat(entry.get("AvDP")), Integer.parseInt(entry.get("1HrP TPcpn")),
                    Integer.parseInt(entry.get("PDir")), Float.parseFloat(entry.get("AvSp")),
                    Integer.parseInt(entry.get("Dir")), Integer.parseInt(entry.get("MxS")),
                    Float.parseFloat(entry.get("SkyC")), Integer.parseInt(entry.get("MxR")),
                    Integer.parseInt(entry.get("Mn")), Float.parseFloat(entry.get("R AvSLP")));
        };
        String delimiter = ",";
        boolean skipOnMalformedRow = false;

        DatasetReader<WeatherData> reader = new CsvDatasetReader<WeatherData>(csvFilePath, rowFactory,
                                                                            delimiter, skipOnMalformedRow);
        return new WeatherService(reader);
    }

    private static CountryService getCountryService() {
        Path csvFilePath = Paths.get("src", "main", "resources", "de", "bcxp", "challenge", "countries.csv");
        CsvRowFactory<CountryData> rowFactory = (entry) -> {
            return new CountryData(
                    entry.get("Name"), entry.get("Capital"),
                    entry.get("Accession"), Integer.parseInt(entry.get("Population")),
                    Integer.parseInt(entry.get("Area (km²)")), Integer.parseInt(entry.get("GDP (US$ M)")),
                    Float.parseFloat(entry.get("HDI")), Integer.parseInt(entry.get("MEPs")));
        };
        String delimiter = ";";
        boolean skipOnMalformedRow = true;

        DatasetReader<CountryData> reader = new CsvDatasetReader<CountryData>(csvFilePath, rowFactory,
                delimiter, skipOnMalformedRow);
        return new CountryService(reader);
    }
}
