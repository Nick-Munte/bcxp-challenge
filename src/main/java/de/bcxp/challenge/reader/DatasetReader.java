package de.bcxp.challenge.reader;

import java.util.Collection;

public interface DatasetReader<T> {
    /**
     * Reads the dataset and returns it as a Collection of the given type
     * @return Collection[T]
     * @throws Exception Any Exceptions that occur during dataset reading
     */
    Collection<T> read() throws Exception;
}
