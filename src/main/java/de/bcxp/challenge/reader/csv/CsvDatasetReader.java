package de.bcxp.challenge.reader.csv;

import de.bcxp.challenge.reader.DatasetReader;

import java.nio.file.Path;
import java.util.ArrayList;

public class CsvDatasetReader<T> implements DatasetReader<T> {
    private final Path path;
    private final CsvRowFactory<T> rowFactory;
    private final String delimiter;
    private final boolean skipOnMalformedRow;

    public CsvDatasetReader(Path path, CsvRowFactory<T> rowFactory, String delimiter, boolean skipOnMalformedRow) {
        this.path = path;
        this.rowFactory = rowFactory;
        this.delimiter = delimiter;
        this.skipOnMalformedRow = skipOnMalformedRow;
    }

    public ArrayList<T> read() {
        return new ArrayList<T>();
    }
}
