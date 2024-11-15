# Device Matching Service

This project is a Device Matching Service that uses Aerospike as the database to store and retrieve device information based on User-Agent strings.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [License](#license)

## Prerequisites

- Java 21 or later
- Maven 3.6 or later
- Docker (for running Aerospike)
- Aerospike 8.1.4 or later
- An IDE (e.g., IntelliJ IDEA, Eclipse)

## Installation

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/device-matching-service.git
   ```
2. **Go to the project folder**:
   ```bash
   cd device-matching-service
   ```
3. **Compile the project**:
   ```bash
   mvn clean install -U
   ```

## Configuration

1. **Aerospike Configuration**:
    - Ensure you have Aerospike installed and running. You can either set it up locally or use a Docker container.
    - If you want to run Aerospike in a Docker container:
      - Download the Aerospike Community Edition (CE) docker image:
        ```bash
         docker pull aerospike/aerospike-server
         ```
      - Start the container:
        ```bash
        docker run -d --name aerospike -p 3000-3002:3000-3002 aerospike/aerospike-server
        ```

## Running the Application

1. **Start the Application: Run the application using Maven:**
   ```bash
    mvn spring-boot:run
    ```
2. **Access the Application:**
   - The application will be available at http://localhost:8080.

## API Endpoints

### Device Matching
- **Match Device**
  - **POST:** /api/devices/match
  - **Request Body:** User-Agent string (e.g., "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/95.0.4638.69 Safari/537.36")
  - **Response:** Returns the matched or created device information.


### Device Management
- **Get Device by ID**
  - **GET:** /api/devices/{id}
  - **Path Variable:** ID (device ID unique identifier)
  - **Response:** Returns the device details for the specified ID.


- **Get Devices by OS Name**
  - **GET:** /api/devices/os/{osName}
  - **Path Variable:** osName (Operational System name)
  - **Response:** Returns a list of devices matching the specified OS name.


- **Delete Devices by IDs**
  - **DELETE:** /api/devices/delete
  - **Request Body:** List of device IDs to be deleted
  - **Response:** No content response.

## Testing

To run the unit tests for the project, use the following command:
```bash
mvn test
```

## License

This project is licensed under the MIT License. See the LICENSE file for details.