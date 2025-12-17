package org.example.local_event_management_system;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.example.local_event_management_system.db.*;

public class HelloApplication extends Application {

    // ===== Event =====
    private TableView<Event> eventTable = new TableView<>();
    private ObservableList<Event> eventList = FXCollections.observableArrayList();

    // ===== Ticket =====
    private TableView<Ticket> ticketTable = new TableView<>();
    private ObservableList<Ticket> ticketList = FXCollections.observableArrayList();

    @Override
    public void start(Stage stage) {

        /* ================= EVENT TABLE ================= */
        TableColumn<Event, Integer> eId = new TableColumn<>("ID");
        eId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());

        TableColumn<Event, String> eName = new TableColumn<>("Name");
        eName.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getName()));

        TableColumn<Event, String> eLoc = new TableColumn<>("Location");
        eLoc.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getLocation()));

        TableColumn<Event, String> eDate = new TableColumn<>("Date");
        eDate.setCellValueFactory(d -> new SimpleStringProperty(d.getValue().getDate()));

        eventTable.getColumns().addAll(eId, eName, eLoc, eDate);
        eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        eventTable.setPrefHeight(400);
        eventTable.setPlaceholder(new Label("No events"));

        loadEvents();

        /* ================= EVENT FORM ================= */
        TextField nameField = new TextField();
        TextField locationField = new TextField();
        TextField dateField = new TextField();

        Button addEventBtn = new Button("Add");
        Button updateEventBtn = new Button("Update");
        Button deleteEventBtn = new Button("Delete");

        addEventBtn.setOnAction(e -> {
            if (nameField.getText().isEmpty() || locationField.getText().isEmpty() || dateField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "All fields are required!");
                alert.showAndWait();
                return;
            }
            EventDAO.addEvent(new Event(
                    nameField.getText(),
                    locationField.getText(),
                    dateField.getText()
            ));
            loadEvents();
            nameField.clear();
            locationField.clear();
            dateField.clear();
        });

        updateEventBtn.setOnAction(e -> {
            Event ev = eventTable.getSelectionModel().getSelectedItem();
            if (ev != null) {
                ev.setName(nameField.getText());
                ev.setLocation(locationField.getText());
                ev.setDate(dateField.getText());
                EventDAO.updateEvent(ev);
                loadEvents();
            }
        });

        deleteEventBtn.setOnAction(e -> {
            Event ev = eventTable.getSelectionModel().getSelectedItem();
            if (ev != null) {
                TicketDAO.deleteTicketsByEvent(ev.getId());
                EventDAO.deleteEvent(ev.getId());
                loadEvents();
                ticketList.clear();
            }
        });

        eventTable.setOnMouseClicked(e -> {
            Event ev = eventTable.getSelectionModel().getSelectedItem();
            if (ev != null) {
                nameField.setText(ev.getName());
                locationField.setText(ev.getLocation());
                dateField.setText(ev.getDate());
                loadTickets(ev.getId());
            }
        });

        GridPane eventForm = new GridPane();
        eventForm.setHgap(10);
        eventForm.setVgap(10);
        eventForm.addRow(0, new Label("Name"), nameField);
        eventForm.addRow(1, new Label("Location"), locationField);
        eventForm.addRow(2, new Label("Date"), dateField);
        eventForm.addRow(3, addEventBtn, updateEventBtn, deleteEventBtn);

        /* ================= TICKET TABLE ================= */
        TableColumn<Ticket, Integer> tId = new TableColumn<>("ID");
        tId.setCellValueFactory(d -> new SimpleIntegerProperty(d.getValue().getId()).asObject());

        TableColumn<Ticket, Double> tPrice = new TableColumn<>("Price");
        tPrice.setCellValueFactory(d -> new SimpleDoubleProperty(d.getValue().getPrice()).asObject());

        ticketTable.getColumns().addAll(tId, tPrice);
        ticketTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ticketTable.setPrefHeight(300);
        ticketTable.setPlaceholder(new Label("Select an event"));

        /* ================= TICKET FORM ================= */
        SpinnerValueFactory.DoubleSpinnerValueFactory vf =
                new SpinnerValueFactory.DoubleSpinnerValueFactory(0, 10000, 0, 1);

        vf.setConverter(new StringConverter<>() {
            @Override public String toString(Double v) { return v.toString(); }
            @Override public Double fromString(String s) {
                try { return Double.parseDouble(s); }
                catch (Exception e) { return 0.0; }
            }
        });

        Spinner<Double> priceSpinner = new Spinner<>(vf);
        priceSpinner.setEditable(true);

        Button addTicketBtn = new Button("Add Ticket");
        Button updateTicketBtn = new Button("Update Ticket");
        Button deleteTicketBtn = new Button("Delete Ticket");

        addTicketBtn.setOnAction(e -> {
            Event ev = eventTable.getSelectionModel().getSelectedItem();
            if (ev != null) {
                TicketDAO.addTicket(new Ticket(ev.getId(), priceSpinner.getValue()));
                loadTickets(ev.getId());
                priceSpinner.getValueFactory().setValue(0.0); // پاک کردن پس از افزودن
            }
        });

        updateTicketBtn.setOnAction(e -> {
            Ticket t = ticketTable.getSelectionModel().getSelectedItem();
            if (t != null) {
                t.setPrice(priceSpinner.getValue());
                TicketDAO.updateTicket(t);
                loadTickets(t.getEventId());
            }
        });

        deleteTicketBtn.setOnAction(e -> {
            Ticket t = ticketTable.getSelectionModel().getSelectedItem();
            if (t != null) {
                TicketDAO.deleteTicket(t.getId());
                loadTickets(t.getEventId());
            }
        });

        ticketTable.setOnMouseClicked(e -> {
            Ticket t = ticketTable.getSelectionModel().getSelectedItem();
            if (t != null) priceSpinner.getValueFactory().setValue(t.getPrice());
        });

        HBox ticketForm = new HBox(10, new Label("Price"), priceSpinner,
                addTicketBtn, updateTicketBtn, deleteTicketBtn);
        ticketForm.setPadding(new Insets(10));

        /* ================= LAYOUT ================= */
        VBox left = new VBox(15, new Label("Events"), eventTable, eventForm);
        VBox right = new VBox(15, new Label("Tickets"), ticketTable, ticketForm);

        HBox root = new HBox(20, left, right);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 1200, 600));
        stage.setTitle("Local Event Management System");
        stage.show();
    }

    private void loadEvents() {
        eventList.setAll(EventDAO.getAllEvents());
        eventTable.setItems(eventList);
    }

    private void loadTickets(int eventId) {
        ticketList.setAll(TicketDAO.getTicketsByEvent(eventId));
        ticketTable.setItems(ticketList);
    }

    public static void main(String[] args) {
        launch();
    }
}
