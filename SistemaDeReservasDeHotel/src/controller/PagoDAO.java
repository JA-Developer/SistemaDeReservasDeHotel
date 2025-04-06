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
import model.Pago;

/**
 *
 * @author louis
 */
public class PagoDAO implements BDOperations {

    @Override
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagos ORDER BY fecha DESC;";
        try (Connection con = ConnectionDB.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                lista.add(new Pago(rs.getInt("id"), rs.getInt("reserva_id"), rs.getString("no_factura"), rs.getString("rtn"), rs.getDate("fecha"), rs.getFloat("monto")));
            }           
        }catch(SQLException e){
            System.err.println("Error al listar pagos: " + e.getMessage());
        }     
        return lista;
    }

    @Override
    public boolean insert(Object object) {
        Pago pago = (Pago) object;
        String sql = "INSERT INTO pagos (reserva_id, no_factura, rtn, fecha, monto) VALUES (?, ?, ?, ?, ?)";

        // Abrimos la conexión con la base de datos
        try (Connection con = ConnectionDB.getConnection()) {
            // Deshabilitamos el auto-commit para comenzar una transacción
            con.setAutoCommit(false);

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                // Asignamos los parámetros a la consulta
                pst.setLong(1, pago.getReservaId());
                pst.setString(2, pago.getNoFactura());
                pst.setString(3, pago.getRtn());
                pst.setDate(4, pago.getFecha());
                pst.setFloat(5, pago.getMonto());

                // Ejecutamos la inserción
                int rowsAffected = pst.executeUpdate();

                // Si la inserción fue exitosa, confirmamos la transacción
                if (rowsAffected > 0) {
                    // Confirmamos la transacción
                    con.commit();
                    return true;
                } else {
                    // Si no se afectaron filas, revertimos la transacción
                    con.rollback();
                    return false;
                }
            } catch (SQLException e) {
                // Si ocurre un error, hacemos rollback
                con.rollback();
                System.err.println("Error al insertar pago: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            // Si ocurre un error al abrir la conexión o al trabajar con ella
            System.err.println("Error al establecer la conexión: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        Pago pago = (Pago) object;
        String sql = "UPDATE pagos SET reserva_id=?, no_factura=?, rtn=?, fecha=?, monto=? WHERE id=?" ;

        try (Connection con = ConnectionDB.getConnection()) {
            // Deshabilitar el auto-commit para comenzar la transacción
            con.setAutoCommit(false);

            try (PreparedStatement pst = con.prepareStatement(sql)) {
                // Asignamos los parámetros de la consulta
                pst.setLong(1, pago.getReservaId());
                pst.setString(2, pago.getNoFactura());
                pst.setString(3, pago.getRtn());
                pst.setDate(4, pago.getFecha());
                pst.setFloat(5, pago.getMonto());
                pst.setLong(6, pago.getId());

                // Ejecutamos la actualización
                int rowsAffected = pst.executeUpdate();

                if (rowsAffected > 0) {
                    // Si la actualización fue exitosa, confirmamos la transacción
                    con.commit();
                    return true;
                } else {
                    // Si no se afectaron filas, revertimos la transacción
                    con.rollback();
                    return false;
                }
            } catch (SQLException e) {
                // Si ocurre un error en la ejecución, hacemos rollback
                con.rollback();
                System.err.println("Error al actualizar pago: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            // Si ocurre un error al abrir la conexión o al trabajar con ella
            System.err.println("Error al establecer la conexión: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM pagos WHERE id=?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);            
            return pst.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("Error al eliminar pago: " + e.getMessage());
            return false;
        }     
    }

    @Override
    public Object getById(long id) {
        Pago pago = new Pago();
        String sql = "SELECT * FROM pagos Where id = ?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {                
                pago.setId(rs.getInt("id"));
                pago.setReservaId(rs.getInt("reserva_id"));
                pago.setNoFactura(rs.getString("no_factura"));
                pago.setRtn(rs.getString("rtn"));
                pago.setFecha(rs.getDate("fecha"));
                pago.setMonto(rs.getFloat("monto"));
            }
        } catch(SQLException e) {
            System.err.println("Error al listar pagos: " + e.getMessage());
        }
        return pago;
    }
}
