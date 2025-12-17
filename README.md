**1. Introduction**
Local Event Management System is an event management application developed in Java with a focus on data management. The Local Event Management System will showcase: how to create an event, view all events created, update individual events and manage event tickets through a GUI created using Java technologies.
________________________
2. System Architecture
The Local Event Management System follows a multi-layered architecture including:
   •	Presentation Layer: JavaFX GUI will be used for users to interact with the system
   •	Business Logic Layer: Java classes host all business logic for the Application
   •	Data Access Layer: Data Access Object (DAO) classes are used for all database interactions
   •	Database Layer: The Local Event Management System will use a MySQL relational database
A layered approach to software architecture provides advantages of improved maintainability, scalability and codebase clarity.
________________________
3. Database Design
The Local Event Management System's Database contains 2 tables:
Event Table
  •	id (Primary Key)
  •	event_name
  •	event_location
  •	event_date
Ticket Table
  •	id (Primary Key)
  •	event_id (Foreign Key)
  •	ticket_price
There will be multiple tickets associated with each event, therefore, the event and ticket tables will have a one-to-many relationship.
