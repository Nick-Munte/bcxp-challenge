package de.bcxp.challenge.reader.csv;

import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CsvDatasetReaderTest {
    private static final Path resourceDir = Paths.get("src", "test", "de", "bcxp", "challenge");

    private CsvDatasetReader<Map<String, String>> getCsvDatasetReader(String filename, boolean skipOnMalformedRow) {
        Path filepath = this.resourceDir.resolve(filename);
        return new CsvDatasetReader<Map<String, String>>(filepath, (rowData -> rowData), ",", skipOnMalformedRow);
    }

    @Test
    public void givenCorrectInput_thenSucceed() {
        CsvDatasetReader<Map<String, String>> reader = getCsvDatasetReader("correct.csv", false);

        // Read file
        List<Map<String, String>> data = reader.read();

        // Expected result
        Map<String, String> row1 = new HashMap<>();
        row1.put("a", "1");
        row1.put("b", "2");
        row1.put("c", "3");
        Map<String, String> row2 = new HashMap<>();
        row1.put("a", "4");
        row1.put("b", "5");
        row1.put("c", "6");
        Map<String, String> row3 = new HashMap<>();
        row1.put("a", "7");
        row1.put("b", "8");
        row1.put("c", "9");

        List<Map<String, String>> expected = new ArrayList<>();
        expected.add(row1);
        expected.add(row2);
        expected.add(row3);

        assertEquals(data, expected);
    }

    @Test
    public void givenMissingFile_thenThrowFileNotFoundException() {
        CsvDatasetReader<Map<String, String>> reader = getCsvDatasetReader("missing.csv", false);
        assertThrows(FileNotFoundException.class, reader::read);
    }

    @Test
    public void givenEmptyFile_thenThrowMissingCsvHeaderException() {
        CsvDatasetReader<Map<String, String>> reader = getCsvDatasetReader("empty.csv", false);
        assertThrows(MissingCsvHeaderException.class, reader::read);
    }

    @Test
    public void givenFileWithoutDataRows_thenReturnEmptyArrayList() {
        CsvDatasetReader<Map<String, String>> reader = getCsvDatasetReader("nodata.csv", false);
        List<Map<String, String>> data = reader.read();
        assertTrue(data.isEmpty());
    }

    @Test
    public void givenFileWithMalformedRows_whenSkippingDisabled_thenThrowMalformedRowException() {
        CsvDatasetReader<Map<String, String>> reader = getCsvDatasetReader("malformed.csv", false);
        assertThrows(MalformedCsvRowException.class, reader::read);
    }

    @Test
    public void givenFileWithMalformedRows_whenSkippingEnabled_thenSucceed() {
        CsvDatasetReader<Map<String, String>> reader = getCsvDatasetReader("malformed.csv", true);

        // Read file
        List<Map<String, String>> data = reader.read();

        // Expected result
        Map<String, String> row1 = new HashMap<>();
        row1.put("a", "4");
        row1.put("b", "5");
        row1.put("c", "6");

        List<Map<String, String>> expected = new ArrayList<>();
        expected.add(row1);

        assertEquals(data, expected);
    }
}
