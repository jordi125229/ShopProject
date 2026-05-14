
import dataValidation.Validator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class ValidationTest {

    private final Validator validator = new Validator();

    @ParameterizedTest
    @MethodSource("providePasswordsToValidate")
    void shouldCheckIfValidationOfNameWorks(String name, boolean expectedResult) {
        assertEquals(expectedResult, validator.ifNameIsValid(name));
    }

    static Stream<Arguments> providePasswordsToValidate() {
        return Stream.of(
                Arguments.of("testName", true),
                Arguments.of("testName1", false),
                Arguments.of("testName!", false),
                Arguments.of("1234", false)
        );
    }

    @Test
    void shouldValidateNameToAppropriateForm() {
        // Arrange
        String name = "testName";
        String expectedFormOfName = "Testname";

        // Act
        String result = validator.firstLetterUpperCase(name);

        // Assert
        assertEquals(expectedFormOfName, result);
    }
}
