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
import model.Huesped;

/**
 *
 * @author louis
 */
public class HuespedDAO implements BDOperations {

    @Override
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM (SELECT * FROM huespedes) AS subquery ORDER BY nombre ASC;";
        try (Connection con = ConnectionDB.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                lista.add(new Huesped(rs.getInt("id"), rs.getString("nombre"), rs.getString("dni")));
            }           
        }catch(SQLException e){
            System.err.println("Error al listar huespedes: " + e.getMessage());
        }     
        return lista;
    }

    @Override
    public boolean insert(Object object) {
        Huesped huesped = (Huesped) object;
        String sql = "INSERT INTO huespedes (nombre, dni) VALUES (?, ?)" ;
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, huesped.getNombre());
            pst.setString(2, huesped.getDni());
            return pst.executeUpdate() > 0;
        } catch(SQLException e){
            System.err.println("Error al insertar huesped: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        Huesped huesped = (Huesped) object;
        String sql = "UPDATE huespedes SET nombre=?, dni=? WHERE id=?" ;
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, huesped.getNombre());
            pst.setString(2, huesped.getDni());
            pst.setLong(3, huesped.getId());
            return pst.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("Error al actualizar huesped: " + e.getMessage());
            return false;
        }     
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM huespedes WHERE id=?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);            
            return pst.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("Error al eliminar huesped: " + e.getMessage());
            return false;
        }     
    }

    @Override
    public Object getById(long id) {
        Huesped huesped = new Huesped();
        String sql = "SELECT * FROM huespedes Where id = ?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {                
                huesped.setId(rs.getInt("id"));
                huesped.setNombre(rs.getString("nombre"));
                huesped.setDni(rs.getString("dni"));
            }
        } catch(SQLException e) {
            System.err.println("Error al listar huespedes: " + e.getMessage());
        }
        return huesped;
    }
}
