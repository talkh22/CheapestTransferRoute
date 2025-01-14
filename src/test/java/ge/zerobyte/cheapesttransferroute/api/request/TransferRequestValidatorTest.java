package ge.zerobyte.cheapesttransferroute.api.request;

import ge.zerobyte.cheapesttransferroute.api.requestHandlers.RequestData;
import ge.zerobyte.cheapesttransferroute.api.requestHandlers.validator.TransferRequestValidator;
import ge.zerobyte.cheapesttransferroute.api.requestHandlers.validator.ValidationResult;
import ge.zerobyte.cheapesttransferroute.model.Transfer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class TransferRequestValidatorTest {

    private final TransferRequestValidator validator = new TransferRequestValidator();

    @Test
    public void testValidRequest() {
        // Given
        RequestData validRequest = new RequestData();
        validRequest.setMaxWeight(20);
        validRequest.setAvailableTransfers(List.of(
                new Transfer(5, 10),
                new Transfer(10, 20)
        ));

        // When
        ValidationResult result = validator.validate(validRequest);

        // Then
        assertTrue(result.isValid());
        assertTrue(result.getErrors().isEmpty());
    }

    @Test
    public void testRequestWithNegativeMaxWeight() {
        // Given
        RequestData invalidRequest = new RequestData();
        invalidRequest.setMaxWeight(-10);
        invalidRequest.setAvailableTransfers(List.of(
                new Transfer(5, 10),
                new Transfer(10, 20)
        ));

        // When
        ValidationResult result = validator.validate(invalidRequest);

        // Then
        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().contains("Maximum weight must be positive"));
    }

    @Test
    public void testRequestWithEmptyTransferList() {
        // Given
        RequestData invalidRequest = new RequestData();
        invalidRequest.setMaxWeight(20);
        invalidRequest.setAvailableTransfers(List.of());

        // When
        ValidationResult result = validator.validate(invalidRequest);

        // Then
        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().contains("Available transfers list cannot be empty"));
    }

    @Test
    public void testRequestWithNegativeTransferWeight() {
        // Given
        RequestData invalidRequest = new RequestData();
        invalidRequest.setMaxWeight(20);
        invalidRequest.setAvailableTransfers(List.of(
                new Transfer(-5, 10),
                new Transfer(10, 20)
        ));

        // When
        ValidationResult result = validator.validate(invalidRequest);

        // Then
        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().contains("All transfers' weights must be positive"));
    }

    @Test
    public void testRequestWithNegativeTransferCost() {
        // Given
        RequestData invalidRequest = new RequestData();
        invalidRequest.setMaxWeight(20);
        invalidRequest.setAvailableTransfers(List.of(
                new Transfer(5, -10),
                new Transfer(10, 20)
        ));

        // When
        ValidationResult result = validator.validate(invalidRequest);

        // Then
        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().contains("All transfers' costs must be positive"));
    }

    @Test
    public void testRequestWithMultipleErrors() {
        // Given
        RequestData invalidRequest = new RequestData();
        invalidRequest.setMaxWeight(-10);
        invalidRequest.setAvailableTransfers(List.of(
                new Transfer(-5, -10),
                new Transfer(10, 20)
        ));

        // When
        ValidationResult result = validator.validate(invalidRequest);

        // Then
        assertFalse(result.isValid());
        assertEquals(3, result.getErrors().size());
        assertTrue(result.getErrors().contains("Maximum weight must be positive"));
        assertTrue(result.getErrors().contains("All transfers' weights must be positive"));
        assertTrue(result.getErrors().contains("All transfers' costs must be positive"));
    }

    @Test
    public void testRequestWithNullTransferList() {
        // Given
        RequestData invalidRequest = new RequestData();
        invalidRequest.setMaxWeight(20);
        invalidRequest.setAvailableTransfers(null);

        // When
        ValidationResult result = validator.validate(invalidRequest);

        // Then
        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
        assertTrue(result.getErrors().contains("Available transfers list cannot be empty"));
    }
}
