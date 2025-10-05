# 🏥 Hospital Management System

A modern, full-stack hospital management system built with **Spring Boot**, **Angular 18**, and **Supabase**.

![Hospital Management System](https://img.shields.io/badge/Spring%20Boot-3.2.1-brightgreen)
![Angular](https://img.shields.io/badge/Angular-18.2-red)
![Supabase](https://img.shields.io/badge/Supabase-PostgreSQL-blue)
![Java](https://img.shields.io/badge/Java-17+-orange)

## ✨ Features

### 🔐 Authentication & Security
- **Supabase Authentication** with email verification
- **JWT-based** secure sessions
- **Role-based access control** (Admin, Doctor, Nurse, Receptionist)
- **Password reset** functionality

### 🏥 Hospital Management
- **Patient Management** - Add, view, and manage patient records
- **Doctor Management** - Manage doctor profiles and specializations
- **Appointment Scheduling** - Book and manage appointments
- **Dashboard Analytics** - Real-time statistics and insights

### 🎨 Modern UI/UX
- **Responsive Design** - Works on desktop, tablet, and mobile
- **Modern Animations** - Smooth transitions and loading states
- **Professional Theme** - Clean, medical-focused design
- **Dark/Light Mode Ready** - Easy to extend

## 🚀 Tech Stack

### Backend
- **Spring Boot 3.2.1** - Java framework
- **Spring Data JPA** - Database abstraction
- **PostgreSQL** - Database (via Supabase)
- **Maven** - Dependency management

### Frontend
- **Angular 18** - Frontend framework
- **TypeScript** - Type-safe JavaScript
- **RxJS** - Reactive programming
- **CSS3** - Modern styling with animations

### Database & Auth
- **Supabase** - Backend-as-a-Service
- **PostgreSQL** - Relational database
- **Supabase Auth** - Authentication service

## 📋 Prerequisites

- **Java 17+** (JDK 24 recommended)
- **Node.js 18+** and npm
- **Angular CLI 18+**
- **Supabase Account**

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone <your-repo-url>
cd Hospital-Management-System
```

### 2. Backend Setup

#### Configure Supabase Database
1. Create a new project at [Supabase](https://supabase.com)
2. Copy your database credentials
3. Create `src/main/resources/application-supabase.properties`:

```properties
spring.application.name=Hospital-Management-System
server.port=8082

spring.autoconfigure.exclude=

spring.datasource.url=jdbc:postgresql://db.YOUR_PROJECT_REF.supabase.co:5432/postgres?sslmode=require
spring.datasource.username=postgres
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=false

spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.connection-timeout=20000
```

#### Run Backend
```bash
# Using Maven Wrapper (recommended)
./mvnw spring-boot:run

# Or using Maven (if installed)
mvn spring-boot:run
```

### 3. Frontend Setup

#### Configure Supabase Auth
1. Get your Supabase URL and Anon Key from your project settings
2. Create `hospital-frontend/src/environments/environment.ts`:

```typescript
export const environment = {
  production: false,
  apiBase: 'http://localhost:8082/api',
  supabase: {
    url: 'YOUR_SUPABASE_URL',
    anonKey: 'YOUR_SUPABASE_ANON_KEY'
  }
};
```

#### Install Dependencies & Run
```bash
cd hospital-frontend
npm install
ng serve
```

### 4. Access the Application
- **Frontend**: http://localhost:4200
- **Backend API**: http://localhost:8082/api

## 🔧 Configuration

### Environment Variables
Create these files with your actual credentials:

**Backend**: `src/main/resources/application-supabase.properties`
**Frontend**: `src/environments/environment.ts`

### Supabase Setup
1. **Enable Email Auth** in Authentication settings
2. **Configure Email Templates** (optional)
3. **Set up Row Level Security** (recommended for production)

## 📱 Usage

### First Time Setup
1. **Register** a new account with your email
2. **Verify** your email address
3. **Login** to access the dashboard

### Demo Accounts
For testing purposes, you can create demo accounts with different roles:
- **Admin**: Full system access
- **Doctor**: Patient and appointment management
- **Nurse**: Patient care and basic management
- **Receptionist**: Appointment scheduling and patient check-in

## 🏗️ Project Structure

```
Hospital-Management-System/
├── src/main/java/                 # Backend Java code
│   └── com/HMSApp/Hospital/Management/System/
│       ├── controller/            # REST controllers
│       ├── model/                 # JPA entities
│       ├── repository/            # Data repositories
│       └── service/               # Business logic
├── hospital-frontend/             # Angular frontend
│   ├── src/app/
│   │   ├── components/            # Angular components
│   │   ├── services/              # Angular services
│   │   └── guards/                # Route guards
│   └── src/environments/          # Environment configs
└── src/main/resources/            # Backend resources
    └── application*.properties    # Spring Boot configs
```

## 🚀 Deployment

### Backend (Spring Boot)
```bash
# Build JAR
./mvnw clean package

# Run JAR
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### Frontend (Angular)
```bash
# Build for production
ng build --configuration production

# Deploy dist/ folder to your hosting service
```

### Recommended Hosting
- **Backend**: Railway, Heroku, or AWS
- **Frontend**: Vercel, Netlify, or AWS S3
- **Database**: Supabase (already configured)

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- **Spring Boot** team for the excellent framework
- **Angular** team for the powerful frontend framework
- **Supabase** for the amazing backend-as-a-service platform
- **Hospital management** professionals for domain insights

## 📞 Support

If you have any questions or need help with setup, please:
1. Check the [Issues](../../issues) page
2. Create a new issue with detailed information
3. Contact the maintainers

---

**Built with ❤️ for better healthcare management**