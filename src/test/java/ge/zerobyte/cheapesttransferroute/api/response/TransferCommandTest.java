package ge.zerobyte.cheapesttransferroute.api.response;

import ge.zerobyte.cheapesttransferroute.api.responseHandlers.TransferCommand;
import ge.zerobyte.cheapesttransferroute.model.Transfer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferCommandTest {

    @Test
    public void itShouldCalculateTotalCostOfTransfers() {
        // Given
        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(1,5));
        transfers.add(new Transfer(3,6));
        transfers.add(new Transfer(7,1));
        transfers.add(new Transfer(5,2));
        transfers.add(new Transfer(12,10));

        // When

        TransferCommand tc = new TransferCommand();
        double totalCost = tc.getTotalCost(transfers);

        // Then
        assertEquals(totalCost, 24);

    }


    @Test
    public void itShouldCalculateTotalWeightOfTransfers() {
        // Given
        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(1,5));
        transfers.add(new Transfer(3,6));
        transfers.add(new Transfer(7,1));
        transfers.add(new Transfer(5,2));
        transfers.add(new Transfer(12,10));

        // When

        TransferCommand tc = new TransferCommand();
        double totalWeight = tc.getTotalWeight(transfers);

        // Then
        assertEquals(totalWeight, 28);

    }


}