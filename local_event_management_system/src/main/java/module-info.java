module local.event.management.system {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;

               exports org.example.local_event_management_system;
        opens org.example.local_event_management_system to javafx.fxml; 
        opens org.example.local_event_management_system.db to javafx.base; 
        }
