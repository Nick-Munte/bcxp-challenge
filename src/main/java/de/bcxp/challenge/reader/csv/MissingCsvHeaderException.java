package de.bcxp.challenge.reader.csv;

public class MissingCsvHeaderException extends RuntimeException {
    public MissingCsvHeaderException(String message) {
        super(message);
    }
}
