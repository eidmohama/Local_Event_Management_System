module local.event.management.system {
        requires javafx.controls;
        requires javafx.fxml;
        requires java.sql;

        // پکیج اصلی پروژه باید برای JavaFX export شود
        exports org.example.local_event_management_system;
        opens org.example.local_event_management_system to javafx.fxml; // برای FXML
        opens org.example.local_event_management_system.db to javafx.base; // برای دسترسی db (اختیاری)
        }
