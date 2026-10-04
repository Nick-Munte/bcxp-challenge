package de.bcxp.challenge.reader.csv;

import java.util.Map;

public interface CsvRowFactory<T> {
    T build(Map<String, String> rowData);
}
