package ge.zerobyte.cheapesttransferroute.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransferTest {

    @Test
    void itShouldCompareTwoDifferentTransferObjects() {
        Transfer t1 = new Transfer(5,10);
        Transfer t2 = new Transfer(10, 20);

        assertNotEquals(t1, t2);
    }

    @Test
    void itShouldCompareTwoSameTransferObjects() {
        Transfer t1 = new Transfer(0,100);
        Transfer t2 = new Transfer(0,100);

        assertEquals(t1, t2);
    }
}