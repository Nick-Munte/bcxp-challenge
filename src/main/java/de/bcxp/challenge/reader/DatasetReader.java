package de.bcxp.challenge.reader;

import java.util.Collection;

public interface DatasetReader<T> {
    Collection<T> read() throws Exception;
}
