package ge.zerobyte.cheapesttransferroute.api.requestHandlers.validator;

import ge.zerobyte.cheapesttransferroute.api.requestHandlers.RequestData;
import ge.zerobyte.cheapesttransferroute.model.Transfer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class validates the provided request and returns the result
 * as a ValidationResult object. It is used by the controller to ensure
 * that the incoming request data is valid before processing.
 */

@Component
public class TransferRequestValidator {

    public ValidationResult validate(RequestData requestData) {
        List<String> errors = new ArrayList<>();

        // Validate maxWeight
        if (requestData.getMaxWeight() <= 0) {
            errors.add("Maximum weight must be positive");
        }

        // Validate transfers list
        if (requestData.getAvailableTransfers() == null || requestData.getAvailableTransfers().isEmpty()) {
            errors.add("Available transfers list cannot be empty");
            return new ValidationResult (false, errors);
        }

        // Validate individual transfers
        for (Transfer transfer : requestData.getAvailableTransfers()) {
            if (transfer.getWeight() <= 0) {
                errors.add("All transfers' weights must be positive");
            }
            if (transfer.getCost() <= 0) {
                errors.add("All transfers' costs must be positive");
            }
        }

        return new ValidationResult(errors.isEmpty(), errors);
    }
}
