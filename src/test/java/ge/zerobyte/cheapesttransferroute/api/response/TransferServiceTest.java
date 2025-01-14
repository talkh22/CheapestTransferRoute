package ge.zerobyte.cheapesttransferroute.api.response;

import ge.zerobyte.cheapesttransferroute.api.responseHandlers.service.TransferService;
import ge.zerobyte.cheapesttransferroute.model.Transfer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransferServiceTest {
    @Test
    void itShouldExceedTheWeightLimit() {
        // Given
        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(1, 10));
        transfers.add(new Transfer(2, 20));
        transfers.add(new Transfer(3, 5));
        transfers.add(new Transfer(4, 15));

        int max_weight = 0;

        // When
        TransferService ts = new TransferService();

        List<Transfer> serviceAnswer = ts.getCheapestTransferRoute(transfers, max_weight);
        List<Transfer> correctAnswer = new ArrayList<>();

        // Then
        assertEquals(serviceAnswer, correctAnswer);
    }

    @Test
    void itShouldFindTheCheapestTransferRoute_Easy() {
        // Given
        List<Transfer> transfers = new ArrayList<>();
        transfers.add(new Transfer(5, 10));
        transfers.add(new Transfer(10, 20));
        transfers.add(new Transfer(3, 5));
        transfers.add(new Transfer(8, 15));

        int max_weight = 15;

        // When
        TransferService ts = new TransferService();

        List<Transfer> serviceAnswer = ts.getCheapestTransferRoute(transfers, max_weight);
        List<Transfer> correctAnswer = new ArrayList<>();

        correctAnswer.add(new Transfer(5, 10));
        correctAnswer.add(new Transfer(10, 20));

        // Then
        assertEquals(serviceAnswer, correctAnswer);

    }

    @Test
    void itShouldFindTheCheapestTransferRoute_Medium() {
        // Given
        List<Transfer> transfers = Arrays.asList(
                new Transfer(2, 3),
                new Transfer(3, 4),
                new Transfer(4, 5),
                new Transfer(5, 8)
        );

        int max_weight = 5;

        // When
        TransferService ts = new TransferService();

        List<Transfer> serviceAnswer = ts.getCheapestTransferRoute(transfers, max_weight);

        // Then
        List<Transfer> correctAnswer = List.of(
                new Transfer(5, 8)
        );

        assertEquals(serviceAnswer,correctAnswer);

    }

    @Test
    void itShouldFindTheCheapestTransferRoute_Hard() {
        // Given
        List<Transfer> transfers = Arrays.asList(
                new Transfer(1, 1),
                new Transfer(2, 6),
                new Transfer(3, 10),
                new Transfer(5, 15),
                new Transfer(4, 8),
                new Transfer(6, 18),
                new Transfer(7, 25)
        );
        int max_weight = 10;

        // When
        TransferService ts = new TransferService();

        List<Transfer> serviceAnswer = ts.getCheapestTransferRoute(transfers, max_weight);

        // Then
        List<Transfer> correctAnswer = Arrays.asList(
                new Transfer(3, 10),
                new Transfer(7, 25)
        );

        assertEquals(serviceAnswer,correctAnswer);

    }

    @Test
    void itShouldFindTheCheapestTransferRoute_WithFractionWeights() {
        // Given
        List<Transfer> transfers = Arrays.asList(
                new Transfer(5.22, 1),
                new Transfer(7.44, 6),
                new Transfer(8.53, 10)
        );
        double max_weight = 14.5;

        // When
        TransferService ts = new TransferService();

        List<Transfer> serviceAnswer = ts.getCheapestTransferRoute(transfers, max_weight);

        // Then
        List<Transfer> correctAnswer = Arrays.asList(
                new Transfer(5.22, 1),
                new Transfer(8.53, 10)
        );

        assertEquals(serviceAnswer,correctAnswer);

    }

}