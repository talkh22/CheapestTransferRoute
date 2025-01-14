package ge.zerobyte.cheapesttransferroute.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.zerobyte.cheapesttransferroute.api.requestHandlers.RequestData;
import ge.zerobyte.cheapesttransferroute.model.Transfer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransferControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void welcomePage_ShouldReturnWelcomeMessage() throws Exception {
        mockMvc.perform(get(""))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        "Welcome to the route optimization platform! Every package has its own journey. Let’s make sure it travels the best path!"
                ));
    }

    @Test
    public void cheapestTransferRoute_ShouldReturnOptimalRoute() throws Exception {
        // Prepare test data
        RequestData requestData = new RequestData();
        requestData.setMaxWeight(15);
        requestData.setAvailableTransfers(Arrays.asList(
                new Transfer(5, 10),
                new Transfer(10, 20),
                new Transfer(3, 5),
                new Transfer(8, 15)
        ));

        // Test the endpoint with actual computation
        mockMvc.perform(post("/cheapest-transfer-route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.selectedTransfers[0].weight").value(5))
                .andExpect(jsonPath("$.selectedTransfers[0].cost").value(10))
                .andExpect(jsonPath("$.selectedTransfers[1].weight").value(10))
                .andExpect(jsonPath("$.selectedTransfers[1].cost").value(20))
                .andExpect(jsonPath("$.totalCost").value(30))
                .andExpect(jsonPath("$.totalWeight").value(15));
    }


    @Test
    public void cheapestTransferRoute_ShouldReturnEmptyResult_WhenNoValidTransfers() throws Exception {
        // Prepare test data
        RequestData requestData = new RequestData();
        requestData.setMaxWeight(10);
        requestData.setAvailableTransfers(Arrays.asList(
                new Transfer(15, 30),
                new Transfer(20, 40)
        ));

        // Test the endpoint with no valid transfers
        mockMvc.perform(post("/cheapest-transfer-route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestData)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.selectedTransfers").isEmpty())
                .andExpect(jsonPath("$.totalCost").value(0));
    }


    @Test
    public void cheapestTransferRoute_WithInvalidInput_ShouldReturnBadRequest() throws Exception {
        RequestData requestData = new RequestData();
        requestData.setMaxWeight(-1);
        requestData.setAvailableTransfers(List.of());

        mockMvc.perform(post("/cheapest-transfer-route")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestData)))
                .andExpect(status().isBadRequest());
    }
}