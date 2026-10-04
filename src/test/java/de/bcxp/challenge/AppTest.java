package de.bcxp.challenge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * Example JUnit 5 test case.
 */
class AppTest {
    private static final Path resourceDir = Paths.get("src", "main", "resources", "de", "bcxp", "challenge");

    @Test
    void givenCorrectFiles_ThenSucceed() {
        assertDoesNotThrow((Executable) App::main);
    }
}