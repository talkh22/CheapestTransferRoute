package ge.zerobyte.cheapesttransferroute.api.requestHandlers;

import ge.zerobyte.cheapesttransferroute.model.Transfer;

import java.util.List;

/**
 * A class representing the request data sent to the REST API for the "Cheapest Transfer Route" service.
 * This class captures the input data from the JSON request body, which includes:
 * - The maximum weight (maxWeight)
 * - The list of available transfers (availableTransfers)
 *
 * The data in this class is deserialized from the incoming JSON and passed to the controller
 * and service layer for processing.
 */


public class RequestData {
    private double maxWeight;
    private List<Transfer> availableTransfers;


    public RequestData(){}

    public RequestData(double maxWeight, List<Transfer> availableTransfers) {
        this.maxWeight = maxWeight;
        this.availableTransfers = availableTransfers;
    }

    // Getters and setters
    public List<Transfer> getAvailableTransfers() {
        return availableTransfers;
    }

    public void setAvailableTransfers(List<Transfer> availableTransfers) {
        this.availableTransfers = availableTransfers;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        this.maxWeight = maxWeight;
    }
}