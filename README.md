# 🏦 Virtual Banking System

A full-featured RESTful banking application built with **Spring Boot** that simulates essential banking operations including user management, transactions, and administrative controls.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.9-green?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

---

## 📋 Table of Contents

- [Features](#-features)
- [Tech Stack](#-tech-stack)
- [Project Structure](#-project-structure)
- [Getting Started](#-getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Database Setup](#database-setup)
- [API Endpoints](#-api-endpoints)
- [Usage Examples](#-usage-examples)
- [Contributing](#-contributing)
- [License](#-license)

---

## ✨ Features

### 👤 User Management
- **User Registration** - Create new customer accounts
- **User Login** - Secure authentication with role-based access
- **Profile Management** - Update user details (name, email, password)
- **User Search** - Search users by username

### 💰 Banking Operations
- **Deposit** - Add funds to user accounts
- **Withdraw** - Withdraw funds with balance validation
- **Transfer** - Transfer money between users
- **Passbook** - View complete transaction history

### 🔐 Admin Features
- **Add Users** - Admins can create new user accounts
- **Delete Users** - Remove users (only if balance is zero)
- **View All Users** - List all customers with sorting options
- **Activity Logging** - Track admin actions in history

---

## 🛠 Tech Stack

| Technology | Purpose |
|------------|---------|
| **Java 17** | Core programming language |
| **Spring Boot 3.5.9** | Application framework |
| **Spring Data JPA** | Database ORM and repository pattern |
| **MySQL** | Relational database |
| **Lombok** | Reduce boilerplate code |
| **Maven** | Build and dependency management |

---

## 📁 Project Structure

```
VirtualBankingSystem1/
├── src/main/java/com/vbs/demo/
│   ├── Controller/
│   │   ├── UserController.java        # User & auth endpoints
│   │   ├── TransactionController.java # Banking operations
│   │   └── HistoryController.java     # Activity history
│   ├── dto/
│   │   ├── DisplayDto.java           # User display data
│   │   ├── LoginDto.java             # Login request
│   │   ├── TransactionDto.java       # Deposit/withdraw request
│   │   ├── TransferDto.java          # Transfer request
│   │   ├── UpdateDto.java            # Profile update request
│   │   └── WithdrawDto.java          # Withdraw request
│   ├── models/
│   │   ├── User.java                 # User entity
│   │   ├── Transaction.java          # Transaction entity
│   │   └── History.java              # Admin history entity
│   ├── repositories/
│   │   ├── UserRepo.java             # User data access
│   │   ├── TransactionRepo.java      # Transaction data access
│   │   └── HistoryRepo.java          # History data access
│   └── VirtualBankingSystem1Application.java
├── src/main/resources/
│   └── application.properties        # Configuration
├── pom.xml                           # Maven dependencies
└── README.md
```

---

## 🚀 Getting Started

### Prerequisites

Ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.8+**
- **MySQL 8.0+**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/AVENGERSTHE2102/VirtualBankingSystem.git
   cd VirtualBankingSystem
   ```

2. **Configure the database**
   
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Build the project**
   ```bash
   ./mvnw clean install
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```

   The server will start at `http://localhost:8081`

### Database Setup

Create a MySQL database:

```sql
CREATE DATABASE virtual_banking;
```

> **Note:** Spring JPA will auto-create tables on first run with `ddl-auto=update`.

---

## 📡 API Endpoints

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/register` | Register a new user |
| `POST` | `/login` | Login and get user ID |

### User Operations

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/get-details/{id}` | Get user details |
| `GET` | `/users` | Get all customers (with sorting) |
| `GET` | `/users/{keyword}` | Search users by username |
| `POST` | `/update` | Update user profile |

### Transactions

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/deposit` | Deposit money |
| `POST` | `/withdraw` | Withdraw money |
| `POST` | `/transfer` | Transfer to another user |
| `GET` | `/passbook/{id}` | Get transaction history |

### Admin

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/add/{adminId}` | Add new user (admin only) |
| `DELETE` | `/delete-user/{userId}/admin/{adminId}` | Delete user |

---

## 💡 Usage Examples

### Register a New User

```bash
curl -X POST http://localhost:8081/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "securepass123",
    "email": "john@example.com",
    "name": "John Doe",
    "role": "Customer",
    "balance": 1000.00
  }'
```

### Login

```bash
curl -X POST http://localhost:8081/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "john_doe",
    "password": "securepass123",
    "role": "Customer"
  }'
```

### Deposit Money

```bash
curl -X POST http://localhost:8081/deposit \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "amount": 500.00
  }'
```

### Transfer Money

```bash
curl -X POST http://localhost:8081/transfer \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "username": "receiver_username",
    "amount": 200.00
  }'
```

---

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/amazing-feature`)
3. **Commit** your changes (`git commit -m 'Add amazing feature'`)
4. **Push** to the branch (`git push origin feature/amazing-feature`)
5. **Open** a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Aditya**

- GitHub: [@AVENGERSTHE2102](https://github.com/AVENGERSTHE2102)

---

<p align="center">
  Made with ❤️ using Spring Boot
</p>
