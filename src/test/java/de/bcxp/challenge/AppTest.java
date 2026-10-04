package de.bcxp.challenge;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class AppTest {
    @Test
    void givenCorrectFiles_ThenSucceed() {
        assertDoesNotThrow((Executable) App::main);
    }
}