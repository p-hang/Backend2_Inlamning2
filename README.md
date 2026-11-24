# Car Repair System – Spring Data JPA

## 1. Beskrivning

Detta projekt är ett REST-baserat system för en bilverkstad. Systemet används för att hantera kunder och deras fordon.

Projektet är utvecklat med Spring Boot och Spring Data JPA. Det använder en H2-databas för att lagra information om kunder och fordon.

### Funktioner

Systemet kan:

- Registrera nya kunder.
- Registrera nya fordon.
- Koppla fordon till kunder.
- Hämta alla kunder tillsammans med deras fordon.
- Hämta alla registrerade fordon.
- Söka efter fordon baserat på bilmärke.
- Hämta en kunds ID baserat på kundens namn.
- Uppdatera bilmärket för ett fordon.
- Hämta registreringsnummer och bilmärke med hjälp av Projection.

## 2. Tekniker

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven

## 3. Projektstruktur

Projektet är organiserat i följande paket:

- `entity` – Innehåller entiteterna Customer och Vehicle.
- `repository` – Innehåller CustomerRepository och VehicleRepository.
- `service` – Innehåller affärslogiken.
- `rest`  – Innehåller REST-controllern.
- `dto` – Innehåller DTO-klasser för REST-svar.
- `projection` – Innehåller VehicleBrandProjection.

## 4. Starta projektet

### Förutsättningar

- Java 21 eller senare.
- Maven Wrapper som ingår i projektet.

### Starta med skript

På Windows:

```bash
start.bat
```

### Starta manuellt med Maven Wrapper

Windows:

```bash
mvnw.cmd spring-boot:run
```

Linux/macOS:

```bash
./mvnw spring-boot:run
```

När applikationen har startat är REST API\:et tillgängligt på:

`http://localhost:8080`

## 5. REST API – Endpoints

Samtliga endpoints kan testas med exempelvis Bruno, Postman eller curl.

### 1. Skapa en kund

**Method:** POST

```http
http://localhost:8080/customers?name=Anna&phone=123456789
```

Skapar en ny kund med namn och telefonnummer.

### 2. Hämta kund-ID via namn

**Method:** GET

```http
http://localhost:8080/customer-id?name=Anna
```

Hämtar ID för kunden med det angivna namnet.

### 3. Hämta alla kunder med deras fordon

**Method:** GET

```http
http://localhost:8080/customers
```

Returnerar alla kunder tillsammans med deras fordon. Kunder utan fordon inkluderas också.

### 4. Skapa ett fordon

**Method:** POST

```http
http://localhost:8080/vehicles?registrationNumber=ABC123&brand=Volvo&model=V60&productionYear=2022
```

Registrerar ett nytt fordon.

### 5. Hämta alla fordon

**Method:** GET

```http
http://localhost:8080/vehicles
```

Returnerar alla registrerade fordon.

### 6. Hämta fordon baserat på bilmärke

**Method:** GET

```http
http://localhost:8080/vehicles-by-brand?brand=Volvo
```

Returnerar alla fordon som tillhör det angivna bilmärket.

### 7. Koppla ett fordon till en kund

**Method:** POST

```http
http://localhost:8080/addvehicletocustomer?customerId=1&vehicleId=1
```

Kopplar ett befintligt fordon till en befintlig kund.

### 8. Uppdatera bilmärket för ett fordon

**Method:** PUT

```http
http://localhost:8080/vehicles/1/brand?brand=Saab
```

Uppdaterar bilmärket för fordonet med ID 1.

Uppdateringen utförs med en anpassad JPQL-fråga med `@Modifying`.

### 9. Hämta registreringsnummer och bilmärke

**Method:** GET

```http
http://localhost:8080/vehicle-brand-info
```

Returnerar endast registreringsnummer och bilmärke för alla fordon.

Denna endpoint använder `VehicleBrandProjection` och omvandlar resultatet till `VehicleBrandDTO`.

## 6. Databas

Projektet använder H2 Database.

Databaskonfigurationen finns i `application.properties`.

Om databasen är konfigurerad som en in-memory-databas skapas den när applikationen startar. Data försvinner när applikationen stängs av.

## 7. Testning

Alla REST-endpoints har testats med Bruno.

För att testa hela flödet rekommenderas följande ordning:

1. Skapa en kund.
2. Skapa ett fordon.
3. Koppla fordonet till kunden.
4. Hämta alla kunder med deras fordon.
5. Hämta alla fordon.
6. Söka efter fordon baserat på bilmärke.
7. Hämta kundens ID via namn.
8. Uppdatera bilmärket för ett fordon.
9. Hämta registreringsnummer och bilmärke med Projection.

---

**Projekt:** Inlämningsuppgift 2 – Spring Data JPA
