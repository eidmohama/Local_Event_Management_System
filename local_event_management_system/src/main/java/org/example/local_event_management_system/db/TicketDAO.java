package org.example.local_event_management_system.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {


    public static List<Ticket> getTicketsByEvent(int eventId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT * FROM tickets WHERE event_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tickets.add(new Ticket(
                        rs.getInt("id"),
                        rs.getInt("event_id"),
                        rs.getDouble("price")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public static void addTicket(Ticket ticket) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "INSERT INTO tickets (event_id, price) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ticket.getEventId());
            ps.setDouble(2, ticket.getPrice());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteTicket(int id) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM tickets WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteTicketsByEvent(int eventId) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "DELETE FROM tickets WHERE event_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, eventId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void updateTicket(Ticket ticket) {
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "UPDATE tickets SET price=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, ticket.getPrice());
            ps.setInt(2, ticket.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
