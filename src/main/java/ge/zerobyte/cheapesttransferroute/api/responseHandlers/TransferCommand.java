package ge.zerobyte.cheapesttransferroute.api.responseHandlers;

import ge.zerobyte.cheapesttransferroute.model.Transfer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TransferCommand is part of the Command design pattern and is used to process a list of transfers.
 * It is responsible for calculating additional information like the total cost and total weight
 * of the selected transfers, which are then used in the response to the client.
 *
 * This class works by accepting a list of transfers and computing the aggregated total values.
 * These values are added to the final response by Controller so that the client can receive both the list of transfers
 * and the corresponding total cost and total weight.
 */


@Service
public class TransferCommand {

    public double getTotalCost(List<Transfer> transfers) {
        double sum = 0;
        for (Transfer transfer : transfers) {
            sum += transfer.getCost();
        }
        return sum;
    }

    public double getTotalWeight(List<Transfer> transfers) {
        double sum = 0;
        for (Transfer transfer : transfers) {
            sum += transfer.getWeight();
        }
        return sum;
    }

    public ResponseData getResponseData(List<Transfer> transfers) {
        if(transfers.isEmpty()) {
            return new ResponseData(transfers, 0);
        }

        double totalCost = this.getTotalCost(transfers);
        double totalWeight = this.getTotalWeight(transfers);
        return new ResponseData(transfers, totalCost, totalWeight);
    }

}
