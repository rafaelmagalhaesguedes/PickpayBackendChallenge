# Simplified PickPay - Back-end Challenge

<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" />
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white" />

Simplified PickPay is a Spring Boot application that provides RESTful APIs for user and transaction management. Users can create accounts, retrieve user details, and perform transactions.

## Table of Contents
- [Project Description](#project-description)
- [Setup Instructions](#setup-instructions)
- [Usage](#usage)
- [Testing](#testing)
- [Technologies Used](#technologies-used)

## Setup Instructions

### 1. Clone the Repository
```
git clone https://github.com/yourusername/pickpaysimplificado.git
cd pickpaysimplificado
```

### 2. Install dependencies with Maven
```
mvn install
```

### 3. Start the application with Maven

The application will start on http://localhost:8080.

## Usage

### User Endpoints

- Create User: POST /users

  - Request Body:
    ```
      {
        "firstName": "Jhon",
        "lastName": "Doe",
        "document": "06067296071",
        "email": "jhon@email.com",
        "password": "password",
        "balance": 10,
        "userType": "COMMON"
      }
    ```
    
  - Response:
    ```
    {
      "id": "4810a470-067b-40c8-9e3e-520128c6ed48",
      "firstName": "Jhon",
      "lastName": "Doe",
      "document": "06067296071",
      "balance": 10,
      "email": "jhon@email.com",
      "userType": "COMMON"
    }
    ```


- Create Transaction: POST /transaction

  - Request Body:
    ```
      {
        "value": 10,
        "senderId": "4810a470-067b-40c8-9e3e-520128c6ed48",
        "receiverId": "b6f66166-9962-45b3-8714-e3ea5de5becf"
      }
    ```
      
  - Response:
    ```
    {
      "id": "9d9372c9-ee5c-4a68-bca5-6296923cc457",
      "value": 10.00,
      "sender": {
        "id": "4810a470-067b-40c8-9e3e-520128c6ed48",
        "firstName": "John",
        "lastName": "Doe",
        "document": "06067296071",
        "email": "john.doe@example.com",
        "balance": 0,
        "userType": "COMMON"
      },
      "receiver": {
        "id": "b6f66166-9962-45b3-8714-e3ea5de5becf",
        "firstName": "Jane",
        "lastName": "Smith",
        "document": "987654321",
        "email": "jane.smith@example.com",
        "balance": 20.00,
        "userType": "MERCHANT"
      },
      "timestamp": "2024-08-06T12:50:49.434348234"
    }
  ```