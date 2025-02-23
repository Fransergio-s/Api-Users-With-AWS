﻿# Api-Users-With-AWS

## Challenge 03 - Compass UOL - SPRINGBOOT_AWS_AGO24

Repository created for Challenge 03 - Week 13, part of the **SPRING BOOT AWS** scholarship program by Compass UOL.

Following the description of the challenge, the user registration system was developed using Java, Kafka Spring Boot, MongoDB and PostgresSQL. The system covers user registration, password update, login, saving the log of all operations in a MongoDB database.

Below is a description of the application components.

---

## Dependencies

Dependency management is done using **Maven**, so to run the application locally, the `pom.xml` file must include the following dependencies:

1. **spring-boot-starter-data-jpa**  
   Used to integrate Spring Data JPA, facilitating interaction with databases using the JPA (Java Persistence API) specification.

2. **spring-boot-starter-web**  
   Includes everything needed to create web applications, such as support for RESTful APIs, including Spring MVC and Tomcat as the embedded server.

3. **spring-boot-devtools**  
   Provides features for development, such as automatic reloading and support for real-time configuration changes. It is optional and used only at runtime.

4. **org.postgresql**  
   Relational database used to save the entities of the apiUser microservice.

5. **org.projectlombok**  
   Used to utilize the Slf4j annotation. To generate logs for the application.
   
6. **org.springframework.cloud**  
   OpenFeign driver for connection to connect to an external API. Used to connect to the VIACEP API.

7. **org.springframework.kafka**  
   Used to establish messaging by Kafka in the application.

8. **io.jsonwebtoken**  
   Allows you to create bearer tokens for the application.

9. **spring-boot-starter-security**  
   Allows you to apply encryption security and tokens to the application.

10. **org.springdoc**  
   Allows you to create personalized documentation for the application.

11. **spring-boot-starter-data-mongodb**
    Allows the application to connect to a MongoDB database.

## Package main/java/resources

Package used to store resource files necessary for the application. Here, we have the following subfolders and files:

### application.yml for apiUser Microservice

This configuration file defines the settings for the `apiUser` microservice.

- **springdoc**: Configures Swagger for API documentation.
  - **swagger-ui.path**: The path to access the Swagger UI is set to `/user-docs.html`.
  - **api-docs.path**: The API documentation can be accessed at `/user-docs`.
  - **packages-to-scan**: Specifies the package to scan for controllers: `compasso.com.br.apiuser.controller`.

- **security**: Contains JWT security configurations.
  - **jwt.token.secret-key**: The secret key used for signing the JWT tokens.
  - **jwt.token.expire-length**: The expiration time for the tokens (1 hour).

- **spring.datasource**: Database configuration for PostgreSQL.
  - **url**: The JDBC URL for the database: `jdbc:postgresql://localhost:5432/user_db`.
  - **username**: Database username: `root`.
  - **password**: Database password: `root`.
  - **driver-class-name**: The class name of the PostgreSQL driver: `org.postgresql.Driver`.

- **spring.jpa**: JPA configuration settings.
  - **hibernate.ddl-auto**: The strategy for schema management is set to `update`.
  - **show-sql**: SQL statements will be logged if set to `true`.
  - **properties.hibernate.dialect**: Specifies the Hibernate dialect for PostgreSQL.

- **spring.kafka**: Kafka configuration for communication.
  - **bootstrap-servers**: The Kafka server is configured to connect to `kafka:9093`.
  - **producer.key-serializer**: Serializer for keys: `org.apache.kafka.common.serialization.StringSerializer`.
  - **producer.value-serializer**: Serializer for values: `org.apache.kafka.common.serialization.StringSerializer`.

- **kafka.topics**: Defines the topics used in the Kafka messaging system.
  - **notify-topic**: Topic for notifications.

- **server.port**: The server port for the microservice is set to `8090`.

---

### application.yml for notifyApi Microservice

This configuration file defines the settings for the `notifyApi` microservice.

- **springdoc**: Configures Swagger for API documentation.
  - **swagger-ui.path**: The path to access the Swagger UI is set to `/notify-docs.html`.
  - **api-docs.path**: The API documentation can be accessed at `/notify-docs`.
  - **packages-to-scan**: Specifies the package to scan for controllers: `br.com.compasso.notifyapi.controller`.

- **kafka**: Kafka configuration for messaging.
  - **bootstrap-servers**: The Kafka server is set to `localhost:9092`.
  - **consumer.group-id**: Consumer group ID for the notifications: `notify_group`.
  - **key-deserializer**: Deserializer for keys: `org.apache.kafka.common.serialization.StringDeserializer`.
  - **value-deserializer**: Deserializer for values: `org.apache.kafka.common.serialization.StringDeserializer`.

- **data.mongodb**: MongoDB configuration for storing notifications.
  - **host**: The MongoDB host is set to `mongo`.
  - **port**: The MongoDB port is `27017`.
  - **database**: The name of the MongoDB database is `notify`.

- **codec.max-in-memory-size**: Sets the maximum in-memory size for the codec to `2MB`.

## API Endpoints

### 1. Login

- **Endpoint**: `/login`
- **Method**: `POST`
- **Description**: Log in to the application using a valid username and password.
- **Request Body**: 
  - `LoginRequest`: Contains the username and password.
  
- **Responses**:
  - **200 OK**: Token created successfully.
    - **Response Body**: `LoginResponse` (contains the token).
  - **404 Not Found**: No user found based on the provided username.
    - **Response Body**: `ErrorResponse`.
  - **500 Internal Server Error**: An error occurred while creating the token.
    - **Response Body**: `LoginResponse`.

## 2. User Registration

- **Endpoint**: `/api/users/register`
- **Method**: `POST`
- **Description**: Create a user with a username, password, email, and zip code.
- **Request Body**: 
  - `UserRequestDto`: Contains user details.
  
- **Responses**:
  - **200 OK**: User created successfully.
    - **Response Body**: `UserResponseDto`.
  - **409 Conflict**: User already exists.
    - **Response Body**: `ErrorResponse`.
  - **500 Internal Server Error**: An error occurred while creating the user.
    - **Response Body**: `LoginResponse`.

## 3. Update User Password

- **Endpoint**: `/api/users/update-password`
- **Method**: `PUT`
- **Description**: Update a user's password by validating the old password.
- **Request Body**: 
  - `UserUpdatePasswordDto`: Contains the username and old/new password.
  
- **Responses**:
  - **204 No Content**: User password updated successfully.
  - **404 Not Found**: User not found.
    - **Response Body**: `ErrorResponse`.
  - **500 Internal Server Error**: An unexpected error occurred.
    - **Response Body**: `ErrorResponse`.
   
# API Endpoints for apiNotify Microservice

## 1. Get All Notifications

- **Endpoint**: `/api/notification`
- **Method**: `GET`
- **Description**: Retrieve a list of all notifications.
  
- **Responses**:
  - **200 OK**: Successfully retrieved notifications.
    - **Response Body**: A list of `Notification` objects.
   
## Dockerization

All services of the application are containerized and can be managed using Docker. The following `docker-compose.yml` file defines the services required to run the application:

```yaml
version: '3.8'

services:
  postgres:
    image: postgres:latest
    container_name: postgress_apiuser  # Container name
    environment:
      POSTGRES_USER: root
      POSTGRES_PASSWORD: root
      POSTGRES_DB: user_db
    ports:
      - "5432:5432"
    networks:
      - api-net
    volumes:
      - postgres_data:/var/lib/postgresql/data

  zookeeper:
    image: wurstmeister/zookeeper:latest
    container_name: zookeeper_apiuser  # Container name
    ports:
      - "2181:2181"
    networks:
      - api-net

  kafka:
    image: wurstmeister/kafka:latest
    container_name: kafka_apiuser  # Container name
    ports:
      - "9092:9092"
    environment:
      KAFKA_ADVERTISED_LISTENERS: INSIDE://kafka:9093,OUTSIDE://localhost:9092
      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: INSIDE:PLAINTEXT,OUTSIDE:PLAINTEXT
      KAFKA_LISTENERS: INSIDE://0.0.0.0:9093,OUTSIDE://0.0.0.0:9092
      KAFKA_INTER_BROKER_LISTENER_NAME: INSIDE
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
      KAFKA_CREATE_TOPICS: "notify-topic:3:1"  # Topic for notifications
    networks:
      - api-net
    depends_on:
      - zookeeper

  mongodb:
    image: mongo:latest
    container_name: "mongodb"
    environment:
      MONGO_INITDB_DATABASE: notify
    ports:
      - "27017:27017"
    networks:
      - api-net

  apiuser:
    image: fransmorato/apiuser
    container_name: user_app
    build:
      context: ./apiUser
      dockerfile: Dockerfile
    ports:
      - '8090:8090'
    networks:
      - api-net
    environment:
      - SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/user_db
      - SPRING_DATASOURCE_USERNAME=root
      - SPRING_DATASOURCE_PASSWORD=root
      - SECURITY_JWT_TOKEN_SECRET_KEY=h0cRA4Zv4hw7v4Q0kMDX730Nhr0cSDcxYqUmiogpZcrGw2wQKcuWALFrXE7jPWBP
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9093
      - SPRING_KAFKA_PRODUCER_KEY-SERIALIZER=org.apache.kafka.common.serialization.StringSerializer
      - SPRING_KAFKA_PRODUCER_VALUE_SERIALIZER=org.apache.kafka.common.serialization.StringSerializer
      - KAFKA_TOPICS=notify-topic
    depends_on:
      - postgres

  notifyapi:
    image: fransmorato/notifyapi
    container_name: notify_app
    build:
      context: ./notifyApi
      dockerfile: Dockerfile
    ports:
      - '8080:8080'
    networks:
      - api-net
    environment:
      - SPRING_DATA_MONGODB_URI=mongodb://mongodb:27017/notify
      - SPRING_KAFKA_BOOTSTRAP_SERVERS=kafka:9093
      - SPRING_KAFKA_CONSUMER_GROUP-ID=notify_group
      - SPRING_KAFKA_CONSUMER_KEY-DESERIALIZER=org.apache.kafka.common.serialization.StringDeserializer
      - SPRING_KAFKA_CONSUMER_VALUE_DESERIALIZER=org.apache.kafka.common.serialization.StringDeserializer
    depends_on:
      - mongodb

networks:
  api-net:
    driver: bridge

volumes:
  postgres_data:
    driver: local
  mongo_data:
    driver: local
```
### Running the Application

To start the application, navigate to the directory containing the `docker-compose.yml` file and run the following command:

```bash
docker-compose up
```

## Kafka Messaging Implementation

### Overview

This application uses Apache Kafka as a messaging service to facilitate communication between the `apiUser` and `notifyApi` microservices. The `apiUser` service produces notifications when certain user operations occur, while the `notifyApi` service consumes these notifications to save them into a database.

### Notification Producer in `apiUser`

The `NotificationProducer` class is responsible for sending messages to the Kafka topic. It uses the `KafkaTemplate` to send formatted messages that contain the username and the operation performed by the user.

```java
package compasso.com.br.apiuser.producer;

import compasso.com.br.apiuser.exceptions.SendMessageError;
import org.springframework.stereotype.Service;
import org.springframework.kafka.core.KafkaTemplate;

@Service
public class NotificationProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public NotificationProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendNotification(String username, String operation) {
        try {
            String message = String.format("User: %s, Operation: %s", username, operation);
            kafkaTemplate.send("notify-topic", message);
        } catch (SendMessageError e) {
            throw new SendMessageError();
        }
    }
}
```
When a user logs in or registers, the corresponding method in the `NotificationProducer` is called to send a notification to the `notify-topic`.

### Notification Consumer in `notifyApi`

The `NotificationConsumer` class listens for messages from the Kafka topic and processes them. It saves the received notifications into the database using the `NotificationRepository`.

```java
package br.com.compasso.notifyapi.consumer;

import br.com.compasso.notifyapi.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import br.com.compasso.notifyapi.entity.Notification;

@Slf4j
@Service
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;

    @KafkaListener(topics = "notify-topic", groupId = "notify_group")
    public void listen(String message) {
        log.warn("Received message: " + message);
        Notification notification = new Notification(message);

        notificationRepository.save(notification);
    }
}
```
## Acknowledgments

I would like to express my gratitude for the opportunity to work on this project. The experience of developing this application has been incredibly rewarding and has significantly contributed to my growth as a professional. I appreciate the learning journey and the skills I've gained throughout the process.


