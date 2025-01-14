package ge.zerobyte.cheapesttransferroute.api.responseHandlers.service;

import ge.zerobyte.cheapesttransferroute.model.Transfer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This is the service class responsible for the algorithmic logic
 * to find the best group of Transfers.
 * It is used by the TransferController class.
 */

@Service
public class TransferService {

    private static final int SCALING_FACTOR = 100;

    /**
     * This method performs backtracking on the dynamic programming table to find the selected
     * transfers that make up the optimal solution.
     * It starts from the last item and works its way back, determining which items (transfers)
     * were included in the optimal solution by comparing the current tableOtCosts value with the previous one.
     *
     * @param tableOfCosts The dynamic programming table that stores the maximum cost at each weight.
     * @param transfers The list of available transfers.
     * @return A list of selected transfers that form the optimal solution.
     */
    private List<Transfer> getTransfersFromTable(double[][] tableOfCosts, List<Transfer> transfers) {
        ArrayList<Transfer> bestTransfers = new ArrayList<>();
        int r = tableOfCosts.length - 1;
        int c = tableOfCosts[0].length - 1;

        while(r>0 && c>0) {
            if(tableOfCosts[r][c] == tableOfCosts[r-1][c]) {
                r--;
            } else {
                bestTransfers.add(transfers.get(r-1));
                c -= (int) transfers.get(r-1).getWeight() * SCALING_FACTOR;
                r--;
            }
        }

        Collections.reverse(bestTransfers);
        return bestTransfers;
    }


    /**
     * Calculates the optimal combination of transfers that maximizes the total cost while
     * ensuring the total weight is within the max weight limit using the 0/1 Knapsack algorithm.
     * The method is also considering that the weight values can be specified with up to two decimal places,
     * because it uses SCALING_FACTOR to scale weights.
     *
     * @param transfers List of available transfers.
     * @param maxWeight The maximum weight limit that can be transferred.
     * @return A list of selected transfers that maximize the total cost within the weight limit.
     */

    public List<Transfer> getCheapestTransferRoute(List<Transfer> transfers, double maxWeight) {
        // dimensions for the dp table.
        int row = transfers.size() + 1;
        int column = (int) (maxWeight + 1) * SCALING_FACTOR;

        double[][] dp = new double[row][column];

        for (int r = 0; r < row; r++) {
            for (int c = 0; c < column; c++) {
                //Knapsack logic
                if (r == 0 || c == 0) {
                    dp[r][c] = 0;
                } else if (transfers.get(r - 1).getWeight() * SCALING_FACTOR <= c) {
                    dp[r][c] = Math.max(
                            transfers.get(r - 1).getCost() + dp[r - 1][(int) (c - transfers.get(r-1).getWeight() * SCALING_FACTOR)],
                            dp[r - 1][c]
                    );
                } else {
                    dp[r][c] = dp[r - 1][c];
                }

            }
        }

        List<Transfer> bestTransfers = getTransfersFromTable(dp, transfers);

        return bestTransfers;
    }
}
