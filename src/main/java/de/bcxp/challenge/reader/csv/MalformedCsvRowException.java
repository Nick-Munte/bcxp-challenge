package de.bcxp.challenge.reader.csv;

public class MalformedCsvRowException extends RuntimeException {
    public MalformedCsvRowException(String message) {
        super(message);
    }
}
