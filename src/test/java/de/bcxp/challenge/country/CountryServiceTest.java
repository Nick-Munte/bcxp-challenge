package de.bcxp.challenge.country;

import de.bcxp.challenge.reader.DatasetReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CountryServiceTest {
    private CountryService getCountryService(Collection<CountryData> data) throws Exception {
        DatasetReader<CountryData> CountryReader = Mockito.mock();
        when(CountryReader.read()).thenReturn(data);
        return new CountryService(CountryReader);
    }

    private static CountryData getCountryData(String country, int population, int area) {
        return new CountryData(country, "", 0, population, area, 0, 0, 0);
    }

    @BeforeAll
    public static void disableLogging() {
        Logger.getLogger(CountryService.class.getName()).setLevel(Level.OFF);
    }

    @Test
    public void givenEmptyData_whenFindCountryWithHighestPopulationDensity_thenReturnNone() throws Exception {
        Collection<CountryData> data = new ArrayList<>();
        CountryService service = getCountryService(data);
        String result = service.findCountryWithHighestPopulationDensity();
        assertEquals("None", result);
    }

    @Test
    public void givenRealData_whenFindCountryWithHighestPopulationDensity_thenReturnCorrectCountry() throws Exception {
        Collection<CountryData> data = new ArrayList<>();
        CountryData entry1 = getCountryData("germany", 1000, 5);
        CountryData entry2 = getCountryData("usa", 500,100);
        data.add(entry1);
        data.add(entry2);

        CountryService service = getCountryService(data);
        String result = service.findCountryWithHighestPopulationDensity();
        assertEquals("germany", result);
    }

    @Test
    public void givenRealDataWithInvalid_whenFindCountryWithHighestPopulationDensity_thenReturnCorrectCountry() throws Exception {
        Collection<CountryData> data = new ArrayList<>();
        CountryData entry1 = getCountryData("germany", 1000, 2);
        CountryData entry2 = getCountryData("usa", 500,100);
        CountryData entry3 = getCountryData("france", 99999,0);
        data.add(entry1);
        data.add(entry2);
        data.add(entry3);

        CountryService service = getCountryService(data);
        String result = service.findCountryWithHighestPopulationDensity();
        assertEquals("germany", result);
    }
}
