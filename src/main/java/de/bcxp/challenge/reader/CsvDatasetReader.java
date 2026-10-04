package de.bcxp.challenge.reader;

import java.nio.file.Path;
import java.util.ArrayList;

public class CsvDatasetReader<T> implements DatasetReader<T> {
    private final Path path;

    public CsvDatasetReader(Path path) {
        this.path = path;
    }

    public ArrayList<T> read() {
        return new ArrayList<T>();
    }
}
