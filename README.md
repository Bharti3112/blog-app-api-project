# blog-app-api-project
This project is a RESTful API for managing a blog application. It allows users to create, read, update, and delete blog posts, manage users, and handle comments. The API is built using Spring Boot and follows best practices for secure and scalable applications.

**Features**
● User Authentication and Authorization (Spring Security)
● CRUD Operations for Blog Posts 
● Comment Management
● Role-Based Access Control (Admin/User)
● Pagination and Sorting for Blog Posts
● Input Validation and Error Handling
● MySQL Database Integration

**Tech Stack**
● Backend: Java, Spring Boot
● Database: MySQL
● Security: Spring Security (BCrypt for password encryption)
● Documentation: Swagger (Springfox)
● Build Tool: Maven

**Prerequisites**
● Java 17 or later
● Maven
● MySQL
● Postman (optional, for testing API)                                                      

**Installation**
1. Clone the repository:
   git clone https://github.com/your-username/blog-app-api.git
   cd blog-app-api
   
2.Set up the MySQL database:
● Create a database named blog_app.
● Update the database configuration in application.properties:
                                                        spring.datasource.url=jdbc:mysql://localhost:8080/blog_app(your url)
                                                        spring.datasource.username=your_mysql_username
                                                        spring.datasource.password=your_mysql_password

3. Build and run the application:
