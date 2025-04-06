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
import model.Usuario;

/**
 *
 * @author louis
 */
public class UsuarioDAO implements BDOperations {

    @Override
    public List<Object> getAll() {
        List<Object> lista = new ArrayList<>();
        String sql = "WITH usuarios_cte AS (SELECT * FROM usuarios) SELECT * FROM usuarios_cte ORDER BY username ASC;";
        try (Connection con = ConnectionDB.getConnection()){
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                lista.add(new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password_hash"), rs.getString("rol")));
            }           
        }catch(SQLException e){
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }     
        return lista;
    }

    @Override
    public boolean insert(Object object) {
        Usuario usuario = (Usuario) object;
        String sql = "INSERT INTO usuarios (username, password_hash, rol) VALUES (?, ?, ?)" ;
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, usuario.getUsername());
            pst.setString(2, usuario.getPasswordHash());
            pst.setString(3, usuario.getRol());
            return pst.executeUpdate() > 0;
        } catch(SQLException e){
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Object object) {
        Usuario usuario = (Usuario) object;
        
        if(usuario.getPasswordHash().equals(null) || usuario.getPasswordHash().equals("")){
            String sql = "UPDATE usuarios SET username=?, rol=? WHERE id=?" ;
            try (Connection con = ConnectionDB.getConnection()){
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, usuario.getUsername());
                pst.setString(2, usuario.getRol());
                pst.setLong(3, usuario.getId());
                return pst.executeUpdate() > 0;
            }catch(SQLException e){
                System.err.println("Error al actualizar usuario: " + e.getMessage());
                return false;
            }
        }
        else
        {
            String sql = "UPDATE usuarios SET username=?, password_hash=?, rol=? WHERE id=?" ;
            try (Connection con = ConnectionDB.getConnection()){
                PreparedStatement pst = con.prepareStatement(sql);
                pst.setString(1, usuario.getUsername());
                pst.setString(2, usuario.getPasswordHash());
                pst.setString(3, usuario.getRol());
                pst.setLong(4, usuario.getId());
                return pst.executeUpdate() > 0;
            }catch(SQLException e){
                System.err.println("Error al actualizar usuario: " + e.getMessage());
                return false;
            }            
        }
    }

    @Override
    public boolean delete(long id) {
        String sql = "DELETE FROM usuarios WHERE id=?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);            
            return pst.executeUpdate() > 0;
        }catch(SQLException e){
            System.err.println("Error al eliminar usuario: " + e.getMessage());
            return false;
        }     
    }

    @Override
    public Object getById(long id) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuarios Where id = ?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {                
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPasswordHash(rs.getString("password_hash"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch(SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuario;
    }
    
    public Object getByUserName(String username) {
        Usuario usuario = new Usuario();
        String sql = "SELECT id, username, password_hash, rol FROM usuarios GROUP BY id, username, password_hash, rol HAVING username = ?;";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {                
                usuario.setId(rs.getInt("id"));
                usuario.setUsername(rs.getString("username"));
                usuario.setPasswordHash(rs.getString("password_hash"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch(SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuario;
    }
    
    public boolean validateCredentials(String user_name, String password_hash)
    {
        String sql = "SELECT * FROM usuarios WHERE UPPER(username) = UPPER(?) AND password_hash = ?";
        try (Connection con = ConnectionDB.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, user_name);
            stmt.setString(2, password_hash);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                return true;
            }
        } catch(SQLException e) {
            System.err.println("Error al listar usuarios: " + e.getMessage());
        }
        
        return false;
    }
}
