package org.example.local_event_management_system.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EventDAO {

    //
    public static List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM events";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                events.add(new Event(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getDate("date").toString()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }


    public static void addEvent(Event event) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO events (name, location, date) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, event.getName());
            ps.setString(2, event.getLocation());
            ps.setString(3, event.getDate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteEvent(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM events WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updateEvent(Event event) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE events SET name=?, location=?, date=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, event.getName());
            ps.setString(2, event.getLocation());
            ps.setString(3, event.getDate());
            ps.setInt(4, event.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
