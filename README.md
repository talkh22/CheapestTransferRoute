# CheapestTransferRoute

## Project Overview

A Java-based application that calculates the best combination of transfers based on given constraints like maximum weight and a list of available transfer options. 
Built with Spring Boot, this project serves as a backend service for transfer optimization. 

## Features 
- Accepts a request with a list of transfers and a maximum weight limit.
- Calculates the optimal combination of transfers that results in the highest total cost.
- Returns the selected transfers along with their total weight and cost.

## Technologies Used 
- **Spring Boot**: The main framework used for building the application.
- **MockMvc**: Used for testing the controller layer (web layer) in isolation.
- **JUnit**: Used for unit and integration testing.
- **Maven**: For dependency management and building the project.
 
## Endpoints 

### POST `/cheapest-transfer-route` 
- **Description**: Calculates the cheapest transfer route based on the provided transfers and maximum weight.
- **Request Body**: The request expects a JSON object with the following structure:
    ```json
    {
      "maxWeight": 15,
      "availableTransfers": [
        { "weight": 5, "cost": 10 },
        { "weight": 10, "cost": 20 },
        { "weight": 3, "cost": 5 },
        { "weight": 8, "cost": 15 }
      ]
    }
    ```
- **Response**: Returns the selected transfers and the total weight and cost:
    ```json
    {
      "selectedTransfers": [
        { "weight": 5, "cost": 10 },
        { "weight": 10, "cost": 20 }
      ],
      "totalCost": 30,
      "totalWeight": 15
    }
    ```
- **Status Codes**:
    - `200 OK`: If the request is processed successfully.
    - `400 Bad Request`: If the input is invalid (e.g., negative or zero values for weight and cost).

## Running the Application 

To run the project locally, you can follow these steps: 
  1. Clone the repository to your local machine: https://github.com/talkh22/CheapestTransferRoute.git 
  2. Navigate to the project directory: /CheapestTransferRoute
  3. Run the application through the command line:
      ``` mvn spring-boot:run ```

   Once the application is running, it will be accessible at: http://localhost:8080.
