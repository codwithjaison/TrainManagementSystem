# TrainManagementSystem

This Java-based console application is a simple Train Management System that performs CRUD (Create, Read, Update, Delete) operations on a train database using JDBC and Oracle Database.

# Features:
          -Insert new train records into the database.
          -View all existing train records.
          -Update a train's name by its number.
          -Delete train records.
          -Interactive CLI menu-driven interface.

# Technologies Used:
                   -Java (JDK 8 or above)
                   -JDBC (Oracle)
                   -Oracle Database XE


# How to Run:
Ensure Oracle Database is running and accessible at( "jdbc:oracle:thin:@localhost:1521:xe" )

Update the JDBC credentials ("USERNAME" and "PASSWORD") in the code if needed.

Compile and run the file:
    javac TrainManagementSystem.java
    java TrainManagementSystem

# SQL Command:
             create table(train_number number(10),train_name varchar(20),train_source varchar(20),train_destination_route varchar(20),train_ticket_number number(10),train_number_of_couches number(4),train_journey_time number(10));
