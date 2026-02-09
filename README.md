# 📇 Smart Contact Manager

<div align="center">

![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Javalin](https://img.shields.io/badge/Javalin-5.6.3-00C853?style=for-the-badge)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)

**A full-stack contact management system with RESTful API and modern web interface**

</div>

---

## 🎯 Features

- ✅ **CRUD Operations** - Create, Read, Update, and Delete contacts
- 🔍 **Search Functionality** - Search contacts by name in real-time
- 📊 **Statistics Dashboard** - View total contacts at a glance
- 🎨 **Modern UI** - Clean, responsive web interface
- 🔌 **RESTful API** - Well-structured API endpoints
- 🗄️ **PostgreSQL Database** - Reliable data persistence with JDBC
- 🐳 **Docker Support** - Containerized deployment
- 📝 **Data Validation** - Form validation for contact information
- 🚀 **Auto-initialization** - Database schema and sample data setup
- 💾 **Indexed Search** - Optimized database queries with indexes

---

## 🛠️ Tech Stack

### Backend
<table>
<tr>
<td align="center" width="96">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="48" height="48" alt="Java" />
<br>Java 17
</td>
<td align="center" width="96">
<img src="https://avatars.githubusercontent.com/u/28214161?s=200&v=4" width="48" height="48" alt="Javalin" />
<br>Javalin
</td>
<td align="center" width="96">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg" width="48" height="48" alt="PostgreSQL" />
<br>PostgreSQL
</td>
<td align="center" width="96">
<img src="https://www.vectorlogo.zone/logos/apache_maven/apache_maven-icon.svg" width="48" height="48" alt="Maven" />
<br>Maven
</td>
</tr>
</table>

**Key Dependencies:**
- **Javalin 5.6.3** - Lightweight web framework for REST API
- **PostgreSQL JDBC 42.7.3** - Database connectivity
- **Gson 2.10.1** - JSON serialization/deserialization
- **Jackson Databind 2.15.0** - JSON processing
- **SLF4J 2.0.9** - Logging framework

### Frontend
<table>
<tr>
<td align="center" width="96">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg" width="48" height="48" alt="HTML5" />
<br>HTML5
</td>
<td align="center" width="96">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg" width="48" height="48" alt="CSS3" />
<br>CSS3
</td>
<td align="center" width="96">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg" width="48" height="48" alt="JavaScript" />
<br>JavaScript
</td>
</tr>
</table>

### DevOps
<table>
<tr>
<td align="center" width="96">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg" width="48" height="48" alt="Docker" />
<br>Docker
</td>
<td align="center" width="96">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg" width="48" height="48" alt="Git" />
<br>Git
</td>
</tr>
</table>

---

## 📁 Codebase Structure

```
contact_manager/
│
├── src/main/
│   ├── java/com/contactmanager/
│   │   ├── api/
│   │   │   ├── ApiServer.java          # Javalin server configuration
│   │   │   └── ContactController.java  # REST API endpoints
│   │   │
│   │   ├── dao/
│   │   │   ├── ContactDAO.java         # Data Access Object interface
│   │   │   └── ContactDAOImpl.java     # JDBC implementation
│   │   │
│   │   ├── model/
│   │   │   └── Contact.java            # POJO entity class
│   │   │
│   │   ├── service/
│   │   │   └── ContactService.java     # Business logic layer
│   │   │
│   │   ├── ui/
│   │   │   └── ConsoleUI.java          # Console interface (legacy)
│   │   │
│   │   ├── util/
│   │   │   └── DatabaseConnection.java # Database connection manager
│   │   │
│   │   └── Main.java                   # Application entry point
│   │
│   └── resources/
│       ├── public/                      # Static web assets
│       │   ├── index.html              # Main UI
│       │   ├── app.js                  # Frontend JavaScript
│       │   └── styles.css              # UI styling
│       │
│       ├── database.properties         # DB configuration
│       └── schema.sql                  # Database schema
│
├── Dockerfile                          # Container configuration
├── pom.xml                             # Maven dependencies
└── README.md                           # This file
```

### Architecture Layers

```
┌─────────────────────────────────────┐
│     Presentation Layer (UI)         │
│  • HTML/CSS/JavaScript              │
│  • Responsive Web Interface         │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│     API Layer (REST)                │
│  • ContactController.java           │
│  • Javalin Routes & Handlers        │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│     Service Layer (Business Logic)  │
│  • ContactService.java              │
│  • Data Validation & Processing     │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│     DAO Layer (Data Access)         │
│  • ContactDAOImpl.java              │
│  • JDBC Operations                  │
└──────────────┬──────────────────────┘
               │
┌──────────────▼──────────────────────┐
│     Database Layer                  │
│  • PostgreSQL                       │
│  • Contacts Table                   │
└─────────────────────────────────────┘
```
---

## 🚀 Getting Started

### Prerequisites
- **Java 17** or higher
- **Maven 3.6+**
- **PostgreSQL 12+**
- **Docker** (optional, for containerized deployment)

### Local Development Setup

1. **Clone the repository**
   ```bash
   git clone <your-repository-url>
   cd Contact_Manager/contact_manager
   ```

2. **Configure Database**
   
   Update `src/main/resources/database.properties`:
   ```properties
   db.url=jdbc:postgresql://localhost:5432/contact_manager
   db.username=your_username
   db.password=your_password
   ```

3. **Build the project**
   ```bash
   mvn clean package
   ```

4. **Run the application**
   ```bash
   java -jar target/contact-manager-1.0.0.jar
   ```

5. **Access the application**
   - Web UI: `http://localhost:7000`
   - API: `http://localhost:7000/api`

### Docker Deployment

1. **Build Docker image**
   ```bash
   cd contact_manager
   docker build -t contact-manager .
   ```

2. **Run container**
   ```bash
   docker run -p 7000:7000 \
     -e DATABASE_URL=<your-postgres-url> \
     contact-manager
   ```

---

## 🏗️ Design Patterns Used

- **DAO Pattern** - Separation of data persistence logic
- **MVC Architecture** - Model-View-Controller separation
- **Singleton Pattern** - Database connection management
- **Dependency Injection** - Service and DAO layer coupling
- **RESTful Design** - Standard HTTP methods for CRUD operations

---

## 🔧 Key Technologies Explained

### 1. **Java 17**
Modern LTS version with enhanced features, pattern matching, and performance improvements.

### 2. **Javalin Framework**
Lightweight, simple web framework for building REST APIs with minimal boilerplate.

### 3. **PostgreSQL + JDBC**
Robust relational database with direct JDBC connectivity for efficient data operations.

### 4. **Maven**
Build automation and dependency management tool for Java projects.

### 5. **Docker**
Containerization for consistent deployment across environments.

---

## 📝 Development Notes

- **Auto-initialization**: Database tables are created automatically on startup
- **Sample Data**: Three sample contacts are inserted on first run
- **CORS Enabled**: API accessible from any origin
- **Static Files**: Web UI served from `/resources/public`
- **Port Configuration**: Configurable via `PORT` environment variable (default: 7000)

---

<div align="center">

**Crafted by @Yash Patil **

</div>
