package de.bcxp.challenge.weather;

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

public class WeatherServiceTest {
    private WeatherService getWeatherService(Collection<WeatherData> data) throws Exception {
        DatasetReader<WeatherData> weatherReader = Mockito.mock();
        when(weatherReader.read()).thenReturn(data);
        return new WeatherService(weatherReader);
    }

    private WeatherData getWeatherData(int day, int mxT, int mnT) {
        return new WeatherData(day, mxT, mnT ,0 ,0 ,0 ,0 ,0, 0, 0, 0, 0, 0, 0);
    }

    @BeforeAll
    public static void disableLogging() {
        Logger.getLogger(WeatherService.class.getName()).setLevel(Level.OFF);
    }

    @Test
    public void givenEmptyData_whenFindDayWithLowestTemperatureSpread_thenReturnNaN() throws Exception {
        Collection<WeatherData> data = new ArrayList<>();
        WeatherService service = getWeatherService(data);
        int result = service.findDayWithLowestTemperatureSpread();
        assertEquals(-1, result);
    }

    @Test
    public void givenRealData_whenFindDayWithLowestTemperatureSpread_thenReturnCorrectDay() throws Exception {
        Collection<WeatherData> data = new ArrayList<>();
        WeatherData entry1 = getWeatherData(0, 10, 5);
        WeatherData entry2 = getWeatherData(1, 9,6);
        data.add(entry1);
        data.add(entry2);

        WeatherService service = getWeatherService(data);
        int result = service.findDayWithLowestTemperatureSpread();
        assertEquals(0, result);
    }

    @Test
    public void givenRealDataWithInvalid_whenFindDayWithLowestTemperatureSpread_thenReturnCorrectDay() throws Exception {
        Collection<WeatherData> data = new ArrayList<>();
        WeatherData entry1 = getWeatherData(0, 10, 5);
        WeatherData entry2 = getWeatherData(1, 12,6);
        WeatherData entry3 = getWeatherData(1, 1,999);
        data.add(entry1);
        data.add(entry2);
        data.add(entry3);

        WeatherService service = getWeatherService(data);
        int result = service.findDayWithLowestTemperatureSpread();
        assertEquals(1, result);
    }
}
