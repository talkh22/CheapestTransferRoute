package ge.zerobyte.cheapesttransferroute.api.responseHandlers;

import ge.zerobyte.cheapesttransferroute.model.Transfer;

import java.util.List;

/**
 * A class representing the response data returned by the REST API for the cheapest transfer route.
 * This class is used to structure the response containing the selected transfers, their total cost and weight.
 * It will be serialized to JSON format and sent as the HTTP response.
 */

public class ResponseData {

    private List<Transfer> selectedTransfers;
    private int totalCost;
    private int totalWeight;

    public ResponseData() {}

    public ResponseData(List<Transfer> selectedTransfers, int totalCost) {
        this.selectedTransfers = selectedTransfers;
        this.totalCost = totalCost;
        this.totalWeight = 0;
    }

    public ResponseData(List<Transfer> selectedTransfers, int totalCost, int totalWeight) {
        this.selectedTransfers = selectedTransfers;
        this.totalCost = totalCost;
        this.totalWeight = totalWeight;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalWeight() {
        return totalWeight;
    }

    public void setTotalWeight(int totalWeight) {
        this.totalWeight = totalWeight;
    }

    public List<Transfer> getSelectedTransfers() {
        return selectedTransfers;
    }

    public void setSelectedTransfers(List<Transfer> selectedTransfers) {
        this.selectedTransfers = selectedTransfers;
    }

}


