package de.bcxp.challenge.reader.csv;

import de.bcxp.challenge.reader.DatasetReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Logger;

public class CsvDatasetReader<T> implements DatasetReader<T> {
    private static final Logger logger = Logger.getLogger(CsvDatasetReader.class.getName());

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

    public Collection<T> read() throws Exception {
        Scanner scanner = new Scanner(this.path.toFile());
        String[] headers = this.getHeaders(scanner);
        return this.getRecords(scanner, headers);
    }

    private String[] getHeaders(Scanner scanner) throws MissingCsvHeaderException {
        if (!scanner.hasNextLine()) {
            throw new MissingCsvHeaderException("Csv file contains no headers");
        }
        String line = scanner.nextLine();
        return line.split(this.delimiter);
    }

    private Collection<T> getRecords(Scanner scanner, String[] headers) throws MalformedCsvRowException {
        Collection<T> records = new ArrayList<T>();

        int lineNumber = 0;
        while (scanner.hasNextLine()) {
            lineNumber += 1;

            try {
                T record = getRecordFromLine(scanner.nextLine(), headers);
                records.add(record);
            } catch(MalformedCsvRowException e) {
                String errMsg = String.format("Malformed row in file %s, line %d: %s",
                                                this.path.toString(), lineNumber, e.getMessage());
                if (this.skipOnMalformedRow) {
                    this.logger.warning(errMsg);
                } else {
                    throw new MalformedCsvRowException(errMsg);
                }
            }
        }

        return records;
    }

    private T getRecordFromLine(String line, String[] headers) throws MalformedCsvRowException {
        String[] splitLine = line.split(this.delimiter);
        if (splitLine.length != headers.length) {
            String errMsg = String.format("Row length does not match header length: %d != %d", splitLine.length, headers.length);
            throw new MalformedCsvRowException(errMsg);
        }

        Map<String, String> rowMap = new HashMap<String, String>();
        for (int i = 0; i < splitLine.length; i++) {
            rowMap.put(headers[i], splitLine[i]);
        }

        try {
            return this.rowFactory.build(rowMap);
        } catch (Exception e) {
            String errMsg = String.format("Error creating object: %s", e.getMessage());
            throw new MalformedCsvRowException(errMsg);
        }
    }
}
