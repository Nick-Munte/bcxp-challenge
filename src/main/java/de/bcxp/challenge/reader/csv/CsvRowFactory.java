package de.bcxp.challenge.reader.csv;

import java.util.HashMap;

public interface CsvRowFactory<T> {
    T build(HashMap<String, String> rowData);
}
