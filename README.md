
# House Room Renting System 🏠

A full-stack web application for managing room rentals, built with **React** 🚀 and **Spring Boot** 🌱. Users can register, log in, manage profiles, upload documents, request rooms, and view bills, while admins handle customers, rooms, and bill uploads. Secured with **JWT** 🔐, powered by **PostgreSQL** 🗄️, and stores files locally 📂. Streamlines renting with a sleek UI! 😊

## ✨ Features

- **User Authentication**: Register and log in with JWT-based security 🔒
- **Customer Features**:
  - Update profile information ✏️
  - Upload documents (e.g., ID proofs) 📄
  - Submit room rental requests 🏠
  - View and download bills 💸
- **Admin Features**:
  - Manage customers and rooms 👥🏡
  - Upload bills for customers 📑
- **File Storage**: Local storage for uploaded documents and bills 📂
- **Responsive UI**: Built with Tailwind CSS for a modern look 📱
**
- register 
- login
- updateProfile
- uploadDocument
- uploadRoomRequest
- getBills
- getAllBills
- updateRequestStatus
- uploadBill
- storeFile
**
## 🛠️ Tech Stack

- **Frontend**: React, React Router, Axios, Tailwind CSS
- **Backend**: Spring Boot, Spring Security, Spring Data JPA
- **Database**: PostgreSQL
- **Authentication**: JSON Web Tokens (JWT)
- **File Storage**: Local filesystem (`/uploads`)
- **Build Tools**: npm (frontend), Maven (backend)

## 📋 Prerequisites

- **Node.js** (v16 or later) 🌐
- **Java** (17) ☕
- **Maven** (3.8 or later) 🛠️
- **PostgreSQL** (13 or later) 🗄️
- **Git** (for cloning the repo) 🗂️

## 🚀 Getting Started

### 1. Clone the Repository
git clone https://github.com/your-username/house-room-renting-system.git
cd house-room-renting-system

2. Set Up the Backend
Navigate to the backend directory:cd backend

Configure PostgreSQL:
Create a database named rentingsystem:psql -U your_username -c "CREATE DATABASE rentingsystem;"

Update src/main/resources/application.properties:spring.datasource.url=jdbc:postgresql://localhost:5432/rentingsystem
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
file.upload-dir=./src/main/resources/uploads
server.port=8080

Create the uploads folder:mkdir src/main/resources/uploads

Build and run:mvn clean install
mvn spring-boot:run

Backend runs at http://localhost:8080.

3. Set Up the Frontend
Navigate to the frontend directory:cd ../frontend

Install dependencies:npm install

Verify src/axios.js points to the backend:import axios from 'axios';
const instance = axios.create({
  baseURL: 'http://localhost:8080',
});
export default instance;

Run the frontend:npm start

Frontend runs at http://localhost:3000.


4. Seed Admin User (Optional)
To access admin features, add an admin user to the database:
INSERT INTO customers (name, email, phone, password, address, role)
VALUES ('Admin', 'admin@example.com', '9999999999', '$2a$10$0z7z7z7z7z7z7z7z7z7z7u', 'Admin Address', 'ADMIN');

🖥️ Usage
Register: Visit http://localhost:3000/register and sign up (e.g., john3@gmail.com, Pass@123).
Log In: Go to http://localhost:3000, log in as a customer or admin (admin@example.com, admin123).
Customer Features:
Update profile at /customer/profile.
Upload documents at /customer/document.
Request rooms at /customer/request.
View bills at /customer/bills.


Admin Features:
Manage customers at /admin/customers.
Manage rooms at /admin/rooms.
Upload bills at /admin/bill-upload.


File Access: Bills and documents are stored in backend/src/main/resources/uploads.

📂 Folder Structure
house-room-renting-system/
├── backend/                    # Spring Boot backend
│   ├── src/main/java/...       # Java source code
│   ├── src/main/resources/     # Configuration and uploads
│   └── pom.xml                 # Maven dependencies
├── frontend/                   # React frontend
│   ├── src/                    # React components and logic
│   ├── public/                 # Static assets
│   └── package.json            # npm dependencies
└── README.md                   # Project documentation

🐛 Troubleshooting

CORS Issues: Ensure SecurityConfig.java allows http://localhost:3000.
JWT Errors: Verify JwtUtil.java and JwtAuthenticationFilter.java use JJWT 0.11.5.
Database Errors: Check PostgreSQL is running and credentials match application.properties.
Frontend Issues: Clear node_modules and reinstall (npm install).
Port Conflicts: Change server.port in application.properties if 8080 is in use.

🤝 Contributing
Contributions are welcome! 🙌

Fork the repository.
Create a feature branch (git checkout -b feature/your-feature).
Commit changes (git commit -m 'Add your feature').
Push to the branch (git push origin feature/your-feature).
Open a pull request.

📜 License
This project is licensed under the MIT License.
📞 Contact
For questions or feedback, reach out to your-email@example.com.
Happy renting! 🏡```
