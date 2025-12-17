**1. Introduction**

Local Event Management System is an event management application developed in Java with a focus on data management. The Local Event Management System will showcase: how to create an event, view all events created, update individual events and manage event tickets through a GUI created using Java technologies.

**2. System Architecture**

**The Local Event Management System follows a multi-layered architecture including:**

   1.	Presentation Layer: JavaFX GUI will be used for users to interact with the system
   2.	Business Logic Layer: Java classes host all business logic for the Application
   3.	Data Access Layer: Data Access Object (DAO) classes are used for all database interactions
   4.	Database Layer: The Local Event Management System will use a MySQL relational database
   5.	A layered approach to software architecture provides advantages of improved maintainability, scalability and codebase clarity.


**3. Database Design****

**The Local Event Management System's Database contains 2 tables:**

**Event Table**

    1.id (Primary Key)
    2. event_name
    3. event_location
    4. event_date
    
**Ticket Table**

    1. id (Primary Key)
    2. event_id (Foreign Key)
    
**ticket_price**
There will be multiple tickets associated with each event, therefore, the event and ticket tables will have a one-to-many relationship.

**4. Implementation**
  Back-end Development
    The back-end portion of the application was developed using the Java programming language. Java Data Access Objects (DAOs) are used to represent the various data entities in the application via SQL statements      to perform Create, Read, Update and Delete (CRUD) operations.

**Front-end Development**
      User-Friendly Graphical User Interfaces (GUIs) are created using the JavaFX Application Platform. The GUI provides an interface that includes tables (for displaying events and tickets)and forms (for               adding/updating/deleting records).

**CRUD Operations**

	1.**Create:** create new events and tickets
	2.**Read**: Load application data stored in the database to be displayed in tables
    3.**Update**: Update existing records in the database
    4.**Delete**: Delete events and related tickets from the database

____________
5. **Error Handling and Validation**
   
 Basic validation ensures that fields marked as required (not null) and ticket prices are valid numbers.  In addition to basic validation, exception handling was implemented in the application to help prevent errors from crashing the application and to gracefully handle errors associated with the database.


6.**Results**

  The application was successful in providing users with easy access to their events and tickets,as well as being able to perform CRUD operations against the database; all CRUD operations performed against the  database are also immediately reflected on the application’s GUI; Screenshots of the application and database queries show success in this area.

7. **Challenges and Solutions**

     The main challenge faced by the development team was keeping the GUI and the database in sync; the team was able to solve
     this by re-loading the data after each CRUD operation was performed.

9. **Conclusion and Future Enhancements**
 
    The project provides an effective implementation of the MySQL, Java and JavaFX software products.
    Future enhancements could include increased validation capabilities,
    user authentication and reporting  functionalities.

