# Realtime Event Service

A Spring Boot microservice demonstrating **real-time event processing** using **Kafka** and **Redis**.  
Built with **Java 21**, **Spring Boot 3.4.9**, and **Maven**.

---

## 🚀 Features
- Produce and consume messages with **Apache Kafka**
- Store and retrieve data with **Redis**
- Dockerized setup for Kafka, Zookeeper, and Redis
- REST endpoints for testing event flow

---

## 🛠️ Tech Stack
- **Java 21**
- **Spring Boot 3.4.9**
- **Apache Kafka** (event streaming)
- **Redis** (fast caching)
- **Docker + Docker Compose**
- **Maven**

---


| Action                  | Command                                                                                                          |
| ----------------------- | ---------------------------------------------------------------------------------------------------------------- |
| List topics             | `kafka-topics --list --bootstrap-server localhost:9092`                                                          |
| Create topic            | `kafka-topics --create --topic my-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1` |
| Describe topic          | `kafka-topics --describe --topic my-topic --bootstrap-server localhost:9092`                                     |
| Delete topic            | `kafka-topics --delete --topic my-topic --bootstrap-server localhost:9092`                                       |
| Produce message         | `kafka-console-producer --topic my-topic --bootstrap-server localhost:9092`                                      |
| Consume message         | `kafka-console-consumer --topic my-topic --from-beginning --bootstrap-server localhost:9092`                     |
| List consumer groups    | `kafka-consumer-groups --list --bootstrap-server localhost:9092`                                                 |
| Describe consumer group | `kafka-consumer-groups --describe --group my-group --bootstrap-server localhost:9092`                            |
