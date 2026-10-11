# Spring Boot Enterprise Billing & Invoicing Engine (Module de Facturation)

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Data JPA](https://img.shields.io/badge/ORM-Hibernate_%2F_JPA-59666C?style=for-the-badge&logo=hibernate&logoColor=white)](https://hibernate.org/)
[![Database](https://img.shields.io/badge/Database-PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Security](https://img.shields.io/badge/Security-Spring_Security_6-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![API Testing](https://img.shields.io/badge/API_Testing-Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com/)
[![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)](LICENSE)

**Module de Facturation** is a robust B2B billing and invoice management backend engineered in **Spring Boot 3.5**, **Java 17**, and **PostgreSQL**. It models the end-to-end lifecycle of enterprise commercial invoicing: corporate client governance with **SIRET** fiscal validation, automated multi-item invoice generation, dynamic Value Added Tax (**TVA**) computation, and cascade-persisted itemized line entries (**Lignes de Facture**).

---

## Architectural Highlights

| Dimension | Technical Implementation |
| :--- | :--- |
| **Framework & Runtime** | Spring Boot 3.5.3 on Java 17 LTS |
| **ORM & Persistence** | Spring Data JPA with Hibernate, utilizing `@OneToMany(cascade = ALL)` and bidirectional `@JsonManagedReference` / `@JsonBackReference` serialization controls |
| **Relational Database** | PostgreSQL with automated schema updates and relational foreign key constraints |
| **Validation Layer** | Jakarta Validation (`@NotNull`, `@Size`, `@Email`) enforcing corporate SIRET formats and required invoice lines |
| **Data Transfer Pattern** | Strongly-typed Java Records & DTOs (`ClientRequestDTO`, `FactureRequestDto`, `FactureRespenseDto`) isolating JPA entities from REST transport contracts |
| **Security Layer** | Spring Security 6 filter chain configuring stateless API access |
| **API Contract Suite** | Comprehensive Postman collection included for rapid endpoint testing |

---

## Domain Entity Model

```
┌───────────────────────────────────────┐
│                Client                 │
├───────────────────────────────────────┤
│ • id: Long (PK)                       │
│ • nom: String                         │
│ • email: String                       │
│ • siret: String (14-digit fiscal ID)  │
│ • dateDeCreation: LocalDate           │
└──────────────────┬────────────────────┘
                   │ 1
                   │
                   │ *
┌──────────────────▼────────────────────┐
│               Facture                 │
├───────────────────────────────────────┤
│ • id: Long (PK)                       │
│ • date: LocalDate                     │
│ • client: Client (FK)                 │
└──────────────────┬────────────────────┘
                   │ 1
                   │ (Cascade ALL)
                   │ *
┌──────────────────▼────────────────────┐
│            LigneFacture               │
├───────────────────────────────────────┤
│ • id: Long (PK)                       │
│ • description: String                 │
│ • quantite: Integer                   │
│ • prixUnitaireHT: Double              │
│ • tauxDeTVA: Double (e.g., 20.0%, 5.5%)│
└───────────────────────────────────────┘
```

---

## REST API Specification

Base URL: `http://localhost:8080/api`

### 1. Client Management

| Method | Endpoint | Description | Request Payload | Response |
| :--- | :--- | :--- | :--- | :--- |
| `POST` | `/api/client` | Register new B2B client | `{ "nom": "...", "email": "...", "siret": "...", "dateDeCreation": "..." }` | `200 OK` (Saved Client) |
| `GET` | `/api/clients` | Retrieve all clients | None | `200 OK` (Array of Clients) |
| `GET` | `/api/client/{id}` | Get client details by ID | None | `200 OK` (Client Object) |

### 2. Invoicing & Line Items

| Method | Endpoint | Description | Request Payload | Response |
| :--- | :--- | :--- | :--- | :--- |
| `POST` | `/api/facture` | Generate new invoice with cascaded lines | `{ "clientId": 1, "date": "...", "ligneFactures": [...] }` | `200 OK` (`FactureRespenseDto`) |
| `GET` | `/api/factures` | List all invoices with calculated totals | None | `200 OK` (Array of Invoices) |

#### Example: Invoice Generation Payload (`POST /api/facture`)

```json
{
  "clientId": 1,
  "date": "2025-07-06",
  "ligneFactures": [
    {
      "description": "Développement Backend Spring Boot",
      "quantite": 3,
      "prixUnitaireHT": 100.0,
      "tauxDeTVA": 20.0
    },
    {
      "description": "Conception Base de Données PostgreSQL",
      "quantite": 2,
      "prixUnitaireHT": 80.0,
      "tauxDeTVA": 5.5
    }
  ]
}
```

---

## Project Structure

```
Module-de-Facturation-Java-Spring-Boot-/
├── Postman/
│   └── New Collection.postman_collection.json # Exported Postman test suite
├── Module-de-Facturation-Java-Spring-Boot/     # Maven Project Root
│   ├── pom.xml                                # Project dependencies & plugins
│   ├── mvnw / mvnw.cmd                        # Maven wrapper scripts
│   └── src/
│       ├── main/
│       │   ├── java/com/IliassWakkar/Module/de/Facturation/Java/Spring/Boot/
│       │   │   ├── configs/      # Spring Security configuration
│       │   │   ├── controllers/  # REST controllers (ClientController, FactureController)
│       │   │   ├── Dtos/         # Request & Response Data Transfer Objects
│       │   │   ├── modeles/      # JPA Entities (Client, Facture, LigneFacture, Utilisateur)
│       │   │   ├── repo/         # Spring Data JPA repositories
│       │   │   ├── services/     # Business logic & transactional services
│       │   │   └── Application.java # Main application bootstrap
│       │   └── resources/
│       │       └── application.properties # PostgreSQL & Hibernate configs
│       └── test/                 # Integration & unit test suites
└── README.md
```

---

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: JDK 17 installed.
- **PostgreSQL**: Version 14+ installed and running.
- **Postman**: (Optional) For automated API testing.

### 1. Database Configuration

Create the PostgreSQL database:

```sql
CREATE DATABASE "Facturation";
```

Update `src/main/resources/application.properties` with your credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/Facturation
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.hibernate.ddl-auto=update
```

### 2. Build & Run

From the project root:

```bash
cd Module-de-Facturation-Java-Spring-Boot

# Using Maven Wrapper
./mvnw clean spring-boot:run

# On Windows
.\mvnw.cmd clean spring-boot:run
```

The application starts on `http://localhost:8080`.

### 3. API Testing with Postman

1. Open **Postman**.
2. Click **Import** and select `Postman/New Collection.postman_collection.json`.
3. Set the environment variable `urlBase` to `http://localhost:8080/api/`.
4. Execute the requests in sequence:
   - `Client` $\to$ `Creer Client`
   - `Client` $\to$ `Get Clients`
   - `Facture` $\to$ `creer facture`
   - `Facture` $\to$ `lister Factures`

---

## Author & Engineering Credits

- **Iliass Wakkar** — Full-Stack & Systems Engineer • [Portfolio](https://www.wakkar.net) • [GitHub](https://github.com/iliass-wakkar) • [LinkedIn](https://www.linkedin.com/in/iliass-wakkar-940233335/)

---

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
