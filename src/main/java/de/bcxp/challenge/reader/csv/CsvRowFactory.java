package de.bcxp.challenge.reader.csv;

import java.util.Map;

public interface CsvRowFactory<T> {
    /**
     * Builds one record out of the row's data
     * @param rowData the data read from the CSV file as a string to string map
     * @return T
     */
    T build(Map<String, String> rowData);
}
