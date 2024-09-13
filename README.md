
# Hospital-Management

### Project Overview
Hospital-Management is a comprehensive system designed to streamline the day-to-day operations of hospitals. It handles various functionalities, including managing patient records, doctor appointments, billing, and inventory. This system aims to improve hospital efficiency and provide better healthcare services by integrating multiple tasks into one easy-to-manage platform.

### Key Features
- **Patient Management**: Handle patient records efficiently, including demographics, medical history, and appointments.
- **Doctor Management**: Scheduling and managing doctor information.
- **Billing System**: Simplifies the billing process for patients.
- **Inventory Management**: Tracks hospital resources like medicines and equipment.

### Tech Stack

The project uses the following technologies:


- **Spring Boot** - Backend framework.
- **Hibernate** - ORM for database interaction.
- **MySQL** - Database for storing patient, doctor, and hospital data.
- **HTML/CSS/JS** - For the front-end of the application.

### Database Structure
For the database structure, you can refer to the **Hospital-SQL** repository, which provides the SQL scripts to set up the required tables and relationships for this project. You can find the SQL scripts and instructions for setting up the database [here](https://github.com/xreedev/Hospital-SQL).

### How to Run the Project
1. Clone the repository:  
   `git clone https://github.com/xreedev/Hospital-Management.git`
2. Install dependencies and configure your MySQL database.
3. Import the SQL scripts from the **Hospital-SQL** repository to set up the database.
4. Run the Spring Boot application:  
   `mvn spring-boot:run`
5. Access the application at:  
   `http://localhost:8080`
