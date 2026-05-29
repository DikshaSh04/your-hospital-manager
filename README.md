# YourHospitalManager

A RESTful backend application that allows patients to register, browse doctors, and book appointments — built with Spring Boot and MySQL.

---

## Problem Statement

Small clinics and hospital OPDs in India still manage appointments through phone calls and paper registers. Patients have no way to check doctor availability or book a slot without physically visiting or calling — leading to long queues, double bookings, and missed appointments. This project provides a backend API to digitize and streamline that process.

---

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Core language |
| Spring Boot 3.x | Application framework |
| Spring Data JPA | Database abstraction layer |
| Hibernate | ORM — maps Java classes to MySQL tables |
| MySQL | Relational database |
| Lombok | Reduces boilerplate code |
| Swagger UI | Interactive API documentation and testing |

---

## Features

- Register and retrieve patients
- Add and retrieve doctors with specialization and availability hours
- Book an appointment for a patient with a doctor
- Automatic conflict detection — prevents double booking a doctor at the same time slot
- Cancel an existing appointment
- View all appointments for a specific patient

---

## Project Structure

```
src/main/java/com/
├── YourHospitalManager.java
├── model/
│   ├── Patient.java
│   ├── Doctor.java
│   ├── Appointment.java
│   └── Status.java (enum: BOOKED, CANCELLED)
├── repository/
│   ├── PatientRepository.java
│   ├── DoctorRepository.java
│   └── AppointmentRepository.java
├── service/
│   ├── PatientService.java
│   ├── DoctorService.java
│   └── AppointmentService.java
├── controller/
│   ├── PatientController.java
│   ├── DoctorController.java
│   └── AppointmentController.java
└── dto/
    └── AppointmentRequestDto.java
```

---

## How to Run Locally

### Prerequisites
- Java 21
- MySQL
- Maven

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/DikshaSh04/YourHospitalManager.git
cd YourHospitalManager
```

2. **Create the database**
```sql
CREATE DATABASE hospital;
```

3. **Configure `application.properties` in SpringBoot 'resources' package**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/hospital
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jackson.serialization.write-dates-as-timestamps=false
```

4. **Run the application**
```bash
mvn spring-boot:run
```

5. **Open Swagger UI**
```
http://localhost:8080/swagger-ui/index.html
```

All endpoints are documented and testable directly from the browser.

---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/patients` | Register a new patient |
| GET | `/api/patients` | Retrieve all patients |
| GET | `/api/patients/{id}` | Retrieve a patient by ID |
| POST | `/api/doctors` | Add a new doctor |
| GET | `/api/doctors` | Retrieve all doctors |
| POST | `/api/appointments` | Book an appointment |
| PUT | `/api/appointments/{id}/cancel` | Cancel an appointment |
| GET | `/api/appointments/patient/{patientId}` | Get all appointments for a patient |

### Sample Request — Book an Appointment

```json
POST /api/appointments
{
  "patientId": 1,
  "doctorId": 1,
  "slotDateTime": "2025-06-10T10:00:00"
}
```

### Responses
- `201 Created` — appointment booked successfully
- `409 Conflict` — doctor is already booked for the requested slot

---

## What I Learned

Building this project gave me hands-on understanding of how Spring's IoC container manages dependencies, rather than manually creating service and repository objects, Spring injects them automatically through `@Autowired`, which keeps each layer loosely coupled and independently testable.

The tricky part was preventing double bookings. I solved this by adding a conflict check in the service layer using a custom Spring Data query (`existsByDoctorAndSlotDateTime`) before persisting.

I also introduced a DTO for the appointment booking endpoint to avoid exposing unnecessary nested object fields in the API contract, which taught me the importance of separating internal data models from what the API surface actually needs.

---

## Author

**Diksha Sharma**   
[GitHub](https://github.com/DikshaSh04) • [LinkedIn](https://www.linkedin.com/in/diksha-sharma25/)
