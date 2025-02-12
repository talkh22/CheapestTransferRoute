package ge.zerobyte.cheapesttransferroute.api.controller;

import ge.zerobyte.cheapesttransferroute.api.requestHandlers.RequestData;
import ge.zerobyte.cheapesttransferroute.api.responseHandlers.ResponseData;
import ge.zerobyte.cheapesttransferroute.api.responseHandlers.TransferCommand;
import ge.zerobyte.cheapesttransferroute.api.requestHandlers.validator.TransferRequestValidator;
import ge.zerobyte.cheapesttransferroute.api.requestHandlers.validator.ValidationResult;
import ge.zerobyte.cheapesttransferroute.api.responseHandlers.service.TransferService;
import ge.zerobyte.cheapesttransferroute.model.Transfer;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransferController {

    private TransferService transferService;
    private TransferCommand transferCommand;
    private TransferRequestValidator transferRequestValidator;


    public TransferController(TransferService transferService, TransferCommand transferCommand, TransferRequestValidator transferRequestValidator) {
        // Dependency injection of Service classes.
        this.transferService = transferService;
        this.transferCommand = transferCommand;
        this.transferRequestValidator = transferRequestValidator;
    }


    @GetMapping()
    public String welcomePage() {
        return "Welcome to the route optimization platform! " +
                "Every package has its own journey. Let’s make sure it travels the best path!";
    }


    @PostMapping("/cheapest-transfer-route")
    public ResponseEntity<?> cheapestTransferRoute(@RequestBody RequestData requestData) {
        // First step: Validation
        ValidationResult validationResult = transferRequestValidator.validate(requestData);

        if (!validationResult.isValid()) {
            return ResponseEntity.badRequest().body(validationResult.getErrors());
        }

        // Extract the necessary information from the request.
        List<Transfer> availableTransfers = requestData.getAvailableTransfers();
        double maxWeight = requestData.getMaxWeight();

        // Pass it to the service and get a result.
        List<Transfer> selectedTransfers = transferService.getCheapestTransferRoute(availableTransfers, maxWeight);

        double totalWeight = transferCommand.getTotalWeight(selectedTransfers);

        if(totalWeight < requestData.getMaxWeight()) {
            List<Transfer> left = transferService.getWhatsLeft();
            List<Transfer> selectedTransfers2 = transferService.getCheapestTransferRoute(left, maxWeight-totalWeight);
            left.removeAll(selectedTransfers2);
            selectedTransfers.addAll(selectedTransfers2);
        }

        // Construct and return the response.
        ResponseData responseData = transferCommand.getResponseData(selectedTransfers);
        return ResponseEntity.ok(responseData);
    }
}
