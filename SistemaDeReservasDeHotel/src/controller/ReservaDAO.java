/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bd.ConnectionDB;
import dto.ReservaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Reserva;

/**
 *
 * @author louis
 */
public class ReservaDAO implements BDOperations {

    @Override
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM reservas ORDER BY fecha_de_inicio ASC;";
        try (Connection con = ConnectionDB.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Reserva(
                    rs.getLong("id"),
                    rs.getTimestamp("fecha_de_inicio"),
                    rs.getInt("horas"),
                    rs.getLong("habitacion_id"),
                    rs.getLong("huesped_id")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar reservas: " + e.getMessage());
        }
        return lista;
    }

    public List<Object> getAllDTO() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT reservas.id, reservas.fecha_de_inicio, reservas.horas, reservas.habitacion_id, habitaciones.precio_por_hora, reservas.huesped_id, huespedes.nombre FROM reservas INNER JOIN habitaciones ON habitaciones.id = reservas.habitacion_id INNER JOIN huespedes ON huespedes.id = reservas.huesped_id;";
        try (Connection con = ConnectionDB.getConnection()) {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new ReservaDTO(
                    rs.getLong("id"),
                    rs.getTimestamp("fecha_de_inicio"),
                    rs.getInt("horas"),
                    rs.getLong("habitacion_id"),
                    rs.getFloat("precio_por_hora"),
                    rs.getLong("huesped_id"),
                    rs.getString("nombre")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar reservas: " + e.getMessage());
        }
        return lista;
    }
    
    @Override
    public boolean insert(Object object) {
        Reserva reserva = (Reserva) object;
        String sql = "INSERT INTO reservas (fecha_de_inicio, horas, habitacion_id, huesped_id) VALUES (?, ?, ?, ?)";
        try (Connection con = ConnectionDB.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, reserva.getFechaDeInicio());
            pst.setInt(2, reserva.getHoras());
            pst.setLong(3, reserva.getHabitacionId());
            pst.setLong(4, reserva.getHuespedId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar reserva: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        Reserva reserva = (Reserva) object;
        String sql = "UPDATE reservas SET fecha_de_inicio=?, horas=?, habitacion_id=?, huesped_id=? WHERE id=?";
        try (Connection con = ConnectionDB.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, reserva.getFechaDeInicio());
            pst.setInt(2, reserva.getHoras());
            pst.setLong(3, reserva.getHabitacionId());
            pst.setLong(4, reserva.getHuespedId());
            pst.setLong(5, reserva.getId());
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM reservas WHERE id=?";
        try (Connection con = ConnectionDB.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar reserva: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Object getById(long id) {
        Reserva reserva = new Reserva(0, null, 0, 0, 0);
        String sql = "SELECT * FROM reservas WHERE id = ?";
        try (Connection con = ConnectionDB.getConnection()) {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reserva.setId(rs.getLong("id"));
                reserva.setFechaDeInicio(rs.getTimestamp("fecha_de_inicio"));
                reserva.setHoras(rs.getInt("horas"));
                reserva.setHabitacionId(rs.getLong("habitacion_id"));
                reserva.setHuespedId(rs.getLong("huesped_id"));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener reserva: " + e.getMessage());
        }
        return reserva;
    }
}
