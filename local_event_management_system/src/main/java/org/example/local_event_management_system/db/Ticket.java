package org.example.local_event_management_system.db;

public class Ticket {
    private int id;
    private int eventId;
    private double price;


    public Ticket(int id, int eventId, double price) {
        this.id = id;
        this.eventId = eventId;
        this.price = price;
    }


    public Ticket(int eventId, double price) {
        this.eventId = eventId;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEventId() { return eventId; }
    public void setEventId(int eventId) { this.eventId = eventId; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
