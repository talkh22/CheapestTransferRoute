package ge.zerobyte.cheapesttransferroute.api.requestHandlers.validator;

import java.util.List;

/**
 * The ValidationResult class is used by the TransferValidator class, which returns the result
 * in this form after validating the request. This result is then used by the controller.
 */


public class ValidationResult {
    private final boolean valid;
    private final List<String> errors;

    public ValidationResult(boolean valid, List<String> errors) {
        this.valid = valid;
        this.errors = errors;
    }

    public boolean isValid() {
        return valid;
    }

    public List<String> getErrors() {
        return errors;
    }
}