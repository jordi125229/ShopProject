package dataValidation;

import org.apache.commons.validator.routines.RegexValidator;

public class Validator {
    private static final RegexValidator NAME_VALIDATOR = new RegexValidator("^[a-ząćęłńóśźżA-ZĄĆĘŁŃÓŚŹŻ]{2,30}$");

    public boolean ifNameIsValid(String name) {
        return NAME_VALIDATOR.isValid(name);
    }

    public String firstLetterUpperCase(String name) {
        if (name == null || name.isBlank()) {
            return name;
        }
        name = name.toLowerCase();
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
