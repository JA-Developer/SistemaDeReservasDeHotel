/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import bd.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Habitacion;

/**
 *
 * @author louis
 */
public class HabitacionDAO implements BDOperations {

    @Override
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM habitaciones ORDER BY id ASC;";
        try (Connection con = ConnectionDB.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                lista.add(new Habitacion(rs.getInt("id"), rs.getInt("cantidad_de_camas"), rs.getFloat("precio_por_hora")));
            }           
        }catch(SQLException e){
            System.err.println("Error al listar habitaciones: " + e.getMessage());
        }     
        return lista;
    }

    @Override
    public boolean insert(Object object) {
        Habitacion habitacion = (Habitacion) object;
        String sql = "INSERT INTO habitaciones (cantidad_de_camas, precio_por_hora) VALUES (?, ?)" ;
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, habitacion.getCantidadDeCamas());
            pst.setFloat(2, habitacion.getPrecioPorHora());
            return pst.executeUpdate() > 0;
        } catch(SQLException e){
            System.err.println("Error al insertar habitacion: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        Habitacion habitacion = (Habitacion) object;
        String sql = "UPDATE habitaciones SET cantidad_de_camas=?, precio_por_hora=? WHERE id=?" ;
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, habitacion.getCantidadDeCamas());
            pst.setFloat(2, habitacion.getPrecioPorHora());
            pst.setLong(3, habitacion.getId());
            return pst.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("Error al actualizar habitacion: " + e.getMessage());
            return false;
        }     
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM habitaciones WHERE id=?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);            
            return pst.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("Error al eliminar habitacion: " + e.getMessage());
            return false;
        }     
    }

    @Override
    public Object getById(long id) {
        Habitacion habitacion = new Habitacion();
        String sql = "SELECT * FROM habitaciones Where id = ?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {                
                habitacion.setId(rs.getInt("id"));
                habitacion.setCantidadDeCamas(rs.getInt("cantidad_de_camas"));
                habitacion.setPrecioPorHora(rs.getFloat("precio_por_hora"));
            }
        } catch(SQLException e) {
            System.err.println("Error al listar habitaciones: " + e.getMessage());
        }
        return habitacion;
    }
}
